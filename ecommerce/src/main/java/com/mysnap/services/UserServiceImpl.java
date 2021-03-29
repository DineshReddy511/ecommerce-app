package com.mysnap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysnap.entities.UserEntity;
import com.mysnap.pojos.User;
import com.mysnap.repositories.UserInfoRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserInfoRepository userInfoRepo;
	@Override
	public User saveUser(User user) {
		UserEntity userEntity = new  UserEntity();
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userEntity.setEmail(user.getEmail());
		userEntity.setGender(user.getGender());
		userEntity.setPhone(user.getPhone());
		userInfoRepo.save(userEntity);
		return user;
	}

}
