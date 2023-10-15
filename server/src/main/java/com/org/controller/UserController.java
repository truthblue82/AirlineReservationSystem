package com.org.controller;

import com.org.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.org.exceptions.RecordAlreadyPresentException;
import com.org.exceptions.RecordNotFoundException;
import com.org.model.User;
import com.org.service.UserService;

import java.util.Collection;

@CrossOrigin
@ComponentScan(basePackages = "com")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/createUser")
	@ExceptionHandler(RecordAlreadyPresentException.class)
	public void addUser(@RequestBody UserDTO newUser) {

		userService.createUser(newUser);
	}

	@GetMapping("/readAllUsers")
	public Collection<UserDTO> readAllUsers() {

		return userService.displayAllUser();
	}

	@PutMapping("/updateUser")
	@ExceptionHandler(RecordNotFoundException.class)
	public void updateUser(@RequestBody UserDTO updateUser) {

		userService.updateUser(updateUser);
	}

	@GetMapping("/searchUser/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> searchUserByID(@PathVariable("id") Long userId) {

		return userService.findUserById(userId);
	}

	@DeleteMapping("/deleteUser/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public void deleteBookingByID(@PathVariable("id") Long userId) {

		userService.deleteUser(userId);
	}
}