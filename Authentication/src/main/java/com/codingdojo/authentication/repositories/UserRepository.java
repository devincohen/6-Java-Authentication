package com.codingdojo.authentication.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.authentication.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	//find user by email for login
	Optional<User> findByEmail(String email);
}
