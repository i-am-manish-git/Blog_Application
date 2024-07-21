package com.manish.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min = 3, message = "Username must be minimum of 3 characters.")
	private String name;
	
	@Email(message = "Email address is not valid!!")
	private String email;
	
	@NotEmpty
	@Size(min = 6, max = 15, message ="Password must be minimum of 6 and maximum of 15 characters!!")
	private String password;
	
	@NotEmpty
	private String about;
	

}
