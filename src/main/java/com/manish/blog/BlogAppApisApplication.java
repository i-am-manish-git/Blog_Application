package com.manish.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.manish.blog.config.AppConstants;
import com.manish.blog.entities.Role;
import com.manish.blog.repositories.RoleRepo;

import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}
	
	@Bean
	 ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Override
	public void run(String... args) throws Exception{
		
		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");
			
			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");
			
			List<Role> roles = List.of(role,role1);
			
			List<Role> result = this.roleRepo.saveAll(roles);
			
			result.forEach(r ->{
				System.out.println(r.getName());
			});
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
