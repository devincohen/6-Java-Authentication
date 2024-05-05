package com.codingdojo.authentication.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.authentication.models.LoginUser;
import com.codingdojo.authentication.models.User;
import com.codingdojo.authentication.repositories.UserRepository;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;
    
    // TO-DO: Write register and login methods!
    public User register(User newUser, BindingResult result) {
        // TO-DO: Additional validations!
    		Optional<User> potentialUser = userRepository.findByEmail(newUser.getEmail());
    		if(potentialUser.isPresent()) {
    			result.rejectValue("email", "Matched", "Email already in use!");
    			return null;
    		}

    		if (!newUser.getPassword().equals(newUser.getConfirm())) {
    			result.rejectValue("password", "Matches", "Password and confirmation must match!");
    			return null;    				
    		}
    		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    		newUser.setPassword(hashed);
    		return userRepository.save(newUser);
    }
    public User login(LoginUser newLoginObject, BindingResult result) {
        // TO-DO: Additional validations!
    		Optional<User> potentialUser = userRepository.findByEmail(newLoginObject.getEmail());
    		if(!potentialUser.isPresent()) {
    			result.rejectValue("email", "Exists", "Invalid email or password");
    			return null;
    		}
    		if(!BCrypt.checkpw(newLoginObject.getPassword(), potentialUser.get().getPassword())) {
    			result.rejectValue("password", "Non-match", "Invalid email or password");
    			return null;
    		}
    		return potentialUser.get();
    }
    
    //find user by id
    public User findById(Long id) {
    	Optional<User> potentialUser = userRepository.findById(id);
    	if(potentialUser.isPresent()) {
    		return potentialUser.get();
    	}
    	return null;
    }
    
    //updates user and returns said user
    public User updateUser(User u) {
    	return userRepository.save(u);
    }
}
