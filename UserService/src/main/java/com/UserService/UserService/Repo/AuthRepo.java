package com.UserService.UserService.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UserService.UserService.Entity.Auth;

public interface AuthRepo extends JpaRepository<Auth, Long> {

}
