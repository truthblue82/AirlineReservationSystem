package com.org.service;

import java.math.BigInteger;
import java.util.Collection;

import com.org.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import com.org.model.User;

public interface UserService {

	public ResponseEntity<?> createUser(UserDTO newUser);

	public UserDTO updateUser(UserDTO newUser);

	public String deleteUser(Long UserId);

	public Collection<UserDTO> displayAllUser();

	public ResponseEntity<?> findUserById(Long userId);
}