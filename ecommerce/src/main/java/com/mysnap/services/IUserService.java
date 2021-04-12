package com.mysnap.services;

import org.springframework.http.ResponseEntity;

import com.mysnap.entities.UserEntity;
import com.mysnap.pojos.User;

public interface IUserService {
	public UserEntity saveUser(User user) throws Exception;
	public Iterable<UserEntity>  getAllUsers();
	public ResponseEntity<User> getUser(int id);
	public void updateUser(User user) throws Exception;
	public void deleteUser(int id);
}
