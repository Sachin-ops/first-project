package com.UserService.UserService.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.UserService.UserService.Entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByEmail(String email);
	
	UserEntity findByUsername(String username);
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);
	
	   
	
}
