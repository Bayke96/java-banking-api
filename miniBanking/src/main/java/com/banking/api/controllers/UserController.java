package com.banking.api.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.api.models.User;
import com.banking.api.services.UserService.UserServices;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/users")
public class UserController {
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
    	
        User foundUser = UserServices.getUser(id);
        
        // User wasn't found.
        if(foundUser == null) {
        	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        // User has been found.
        else 
        {
        	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    	    response.setHeader("Pragma","no-cache");
    	    response.setDateHeader("Expires", 0);
    		
    		return new ResponseEntity<>(foundUser, HttpStatus.OK);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> postUser(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
    	
        User createdUser = UserServices.createUser(user);
        
        response.addHeader("URL", request.getScheme() + "://" + request.getServerName() + ":" + 
        		request.getServerPort() + "/users/" + createdUser.getID());
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
	    response.setHeader("Pragma","no-cache");
	    response.setDateHeader("Expires", 0);
		
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    
}
