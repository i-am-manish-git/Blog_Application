package com.manish.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manish.blog.exceptions.ApiException;
import com.manish.blog.payloads.JwtAuthRequest;
import com.manish.blog.payloads.JwtAuthResponse;
import com.manish.blog.payloads.UserDto;
import com.manish.blog.security.JwtTokenHelper;
import com.manish.blog.services.UserService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;

	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			@RequestBody JwtAuthRequest request) throws Exception
	{
		this.authenticate(request.getEmail(),request.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
		
	    String token =this.jwtTokenHelper.generateToken(userDetails);
	    
	    JwtAuthResponse response = new JwtAuthResponse();
	    response.setToken(token);
	    return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	    		
	}


	private void authenticate(String email, String password) throws Exception {

		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
		try {
		this.authenticationManager.authenticate(authenticationToken);
		}
		catch (BadCredentialsException e) {
			System.out.println("Invalid Details!");
			throw new ApiException("Invalid email or password!!");
		}
	}
	
	//register new user api
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(
			@RequestBody UserDto userDto){
		UserDto registeredUser = this.userService.registerNewUser(userDto);
		
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
		
	}
	
}
