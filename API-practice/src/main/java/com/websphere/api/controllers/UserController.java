package com.websphere.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websphere.api.models.User;
import com.websphere.api.security.JwtResponse;
import com.websphere.api.security.JwtTokenUtil;
import com.websphere.api.services.UserService.UserServices;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
    @PostMapping()
    public void signUp(@RequestBody User user) {
        UserServices.createUser(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {
    	
    authenticate(user.getUserName(), user.getPassword());
    final UserDetails userDetails = (UserDetails) UserServices.getUser(user.getUserName());
    final String token = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok(new JwtResponse(token));
    }
    
    private void authenticate(String username, String password) throws Exception {
    	try {
    		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    	} 
    	catch (DisabledException e) {
    	throw new Exception("USER_DISABLED", e);
    	} 
    	catch (BadCredentialsException e) {
    	throw new Exception("INVALID_CREDENTIALS", e);
    	}
    }
    	

}
