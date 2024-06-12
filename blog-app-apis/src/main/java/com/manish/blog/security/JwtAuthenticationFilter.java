package com.manish.blog.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//get token from request
		String requestToken = request.getHeader("Authorization");
		
		//Bearer 2352523sdgsg(token format)
		
		System.out.println(requestToken); //only purpose to see token
		
		String email = null;
		String token = null;
		
		if(requestToken != null && requestToken.startsWith("Bearer")) 
		{
			token =requestToken.substring(7);
			
			try {
			email = this.jwtTokenHelper.getUsernameFromToken(token);
			}
			catch(IllegalArgumentException e)
			{
				System.out.println("Unable to get Jwt token!");
			}
			catch(ExpiredJwtException e)
			{
				System.out.println("Jwt token has expired!");
			}
			catch (MalformedJwtException e) 
			{
				System.out.println("Invalid Jwt!");
			}
			
		} else {
			System.out.println("Jwt token does not begin with Bearer");
		}
		
		//once we get the token, now we validate
		
		if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				//Set authentication
				
				//create authentication object
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}else {
				System.out.println("Invalid Jwt Token!");
			}
			
		}else {
			System.out.println("username is null or context is null!");
			
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}
