package com.miu.flightmanagement.authorizationservice.service;

import com.miu.flightmanagement.authorizationservice.persistence.model.User;
import com.miu.flightmanagement.authorizationservice.persistence.repo.RoleRepository;
import com.miu.flightmanagement.authorizationservice.persistence.repo.UserRepository;
import com.miu.flightmanagement.authorizationservice.util.UserUtils;
import com.miu.flightmanagement.authorizationservice.web.dto.UserDto;
import com.miu.flightmanagement.authorizationservice.web.error.UserAlreadyExistException;
import com.miu.flightmanagement.authorizationservice.web.error.UserNotExistException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;


    @Override
    public User registerNewUserAccount(UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail());
        }
        User user = UserUtils.toUser(accountDto);
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void saveRegisteredUser(UserDto userDto) {
        final User user = this.findUserByEmail(userDto.getEmail());
        if (user == null) {
            throw new UserNotExistException(("This account is not present!"));
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        this.userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByID(long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public UserDto findUserByPrincipal(String principalClaimValue) {
        return UserUtils.toUserDTO(this.userRepository.findByEmail(principalClaimValue));
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }
}
