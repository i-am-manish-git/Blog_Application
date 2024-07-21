package com.manish.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manish.blog.entities.User;
import com.manish.blog.exceptions.ResourceNotFoundException;
import com.manish.blog.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from database by email(userName) 
		User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email: " +username, 0));
		return user;
	}

}
