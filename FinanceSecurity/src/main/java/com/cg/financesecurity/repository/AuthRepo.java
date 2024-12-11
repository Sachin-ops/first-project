package com.cg.financesecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.financesecurity.Entity.Auth;
import com.cg.financesecurity.Entity.User;
@Repository
public interface AuthRepo  extends CrudRepository<Auth, Long>{
 
}
