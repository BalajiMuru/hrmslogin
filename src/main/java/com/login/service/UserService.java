package com.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.login.model.Employee;
import com.login.model.User;
import com.login.repo.UserRespository;

@Service
public class UserService {
	
	@Autowired
	private UserRespository userRespository;
	
	 
    public void registerEmployee(User user) {
        // Encrypt password before saving
        user.setPassword(user.getPassword());
        userRespository.save(user);
    }
	
	  public boolean verifyPassword(String rawPassword, String storedPassword) {
	        return rawPassword.equals(storedPassword); // Replace with encryption check if using encrypted passwords
	    }

}
