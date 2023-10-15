package com.org.service;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.org.dto.UserDTO;
import com.org.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.org.dao.UserDao;
import com.org.exceptions.RecordAlreadyPresentException;
import com.org.exceptions.RecordNotFoundException;
import com.org.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public ResponseEntity<?> createUser(UserDTO newUserDTO) {
		if (newUserDTO == null) return ResponseEntity.badRequest().build();
		final User newUser = UserUtils.toUser(newUserDTO);
		Optional<User> findUserById = userDao.findByUsername(newUser.getUsername());
		try {
			if (!findUserById.isPresent()) {
				userDao.save(newUser);
				return new ResponseEntity<>(newUserDTO, HttpStatus.OK);
			} else
				throw new RecordAlreadyPresentException(
						"User with username: " + newUserDTO.getUsername() + " already exists!!");
		} catch (RecordAlreadyPresentException e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public UserDTO updateUser(UserDTO updateUser) {
		Optional<User> findUserById = userDao.findById(updateUser.getId());
		if (findUserById.isPresent()) {
			userDao.save(UserUtils.toUser(updateUser));
		} else
			throw new RecordNotFoundException(
					"User with Id: " + updateUser.getId() + " not exists!!");
		return updateUser;
	}

	@Override
	public String deleteUser(Long UserId) {
		// TODO Auto-generated method stub
		Optional<User> findBookingById = userDao.findById(UserId);
		if (findBookingById.isPresent()) {
			userDao.deleteById(UserId);
			return "User Deleted!!";
		} else
			throw new RecordNotFoundException("User not found for the entered UserID");
	}

	@Override
	public Collection<UserDTO> displayAllUser() {
		return userDao.findAll().stream().map(UserUtils::toUserDTO).collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<?> findUserById(Long userId) {
		// TODO Auto-generated method stub
		Optional<User> findById = userDao.findById(userId);
		try {
			if (findById.isPresent()) {
				User findUser = findById.get();
				return new ResponseEntity<>(UserUtils.toUserDTO(findUser), HttpStatus.OK);
			} else
				throw new RecordNotFoundException("No record found with ID " + userId);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}