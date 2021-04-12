package com.mysnap.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mysnap.entities.UserEntity;
import com.mysnap.pojos.User;
import com.mysnap.repositories.UserInfoRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserInfoRepository userInfoRepo;

	@Override
	public UserEntity saveUser(User user) throws Exception {
		System.out.println(user.getEmail());
		System.out.println(user.getPhone());
		if (user.getEmail() == null || user.getPhone() == 0) {
			System.out.println("Email or Phone is missing in the request");
			throw new Exception("Please provide proper request format");

		} else {
			UserEntity userEntity = new UserEntity();
			userEntity.setFirstName(user.getFirstName());
			userEntity.setLastName(user.getLastName());
			userEntity.setEmail(user.getEmail());
			userEntity.setGender(user.getGender());
			userEntity.setPhone(user.getPhone());
			userInfoRepo.save(userEntity);
			return userEntity;
		}
	}

	@Override
	public Iterable<UserEntity> getAllUsers() {
		Iterable<UserEntity> allUsers = userInfoRepo.findAll();
		return allUsers;
	}

	@Override
	public ResponseEntity<User> getUser(int id) {

		User user = null;
		Optional<UserEntity> idResponse = userInfoRepo.findById(id);
		if (idResponse.isPresent()) {
			UserEntity userEntity = idResponse.get();
			user = new User();
			user.setEmail(userEntity.getEmail());
			user.setFirstName(userEntity.getFirstName());
			user.setGender(userEntity.getGender());
			user.setId(userEntity.getId());
			user.setLastName(userEntity.getLastName());
			user.setPhone(userEntity.getPhone());
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			System.out.println("user not found with ID : " + id);
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void updateUser(User user) throws Exception {
		if (user.getId() == 0) {
			saveUser(user);
			System.out.println("saving new user as we do not have exiting user with same userID : "+user.getId());
		} else {
			Optional<UserEntity> idResponse = userInfoRepo.findById(user.getId());
			if (idResponse.isPresent()) {
				UserEntity userEntity = idResponse.get();
				userEntity.setFirstName(user.getFirstName());
				userEntity.setLastName(user.getLastName());
				userEntity.setEmail(user.getEmail());
				userEntity.setGender(user.getGender());
				userEntity.setPhone(user.getPhone());
				userInfoRepo.save(userEntity);
				System.out.println("Updating the userId : "+user.getId());
			} else {
				saveUser(user);
				System.out.println("saving new user as we do not have exiting user with same userID : "+user.getId());
			}
		}
	}

	@Override
	public void deleteUser(int id) {
			userInfoRepo.deleteById(id);
			System.out.println("User Deleted with ID : "+id);
	}

}
