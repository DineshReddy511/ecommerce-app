package com.mysnap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysnap.entities.UserEntity;
import com.mysnap.pojos.User;
import com.mysnap.services.IUserService;

@RestController
@RequestMapping("/public/users/")
public class PublicUserController {

	@Autowired
	private IUserService userService;

	@PostMapping("/")
	public UserEntity saveUser(@RequestBody User user) throws Exception {
		return userService.saveUser(user);
	}

	@GetMapping("/all")
	public Iterable<UserEntity> getAllUser() {
		return userService.getAllUsers();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		return userService.getUser(id);
	}
	@PutMapping
	public void updateUser(@RequestBody User user) throws Exception
	{
		userService.updateUser(user);
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteUser(@PathVariable("id") int id)
	{
		userService.deleteUser(id);
	}
}
