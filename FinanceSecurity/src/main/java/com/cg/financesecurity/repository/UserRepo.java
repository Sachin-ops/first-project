package com.cg.financesecurity.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.financesecurity.Entity.User;
@Repository
public interface UserRepo extends CrudRepository<User, Long> {

	

	Optional<User> findByUsername(String username); // Fetch by username
	Optional<User> findByEmail(String username);
	}

	


