package com.mysnap.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mysnap.entities.UserEntity;

@Repository
public interface UserInfoRepository extends CrudRepository<UserEntity, Integer>{

	
}
