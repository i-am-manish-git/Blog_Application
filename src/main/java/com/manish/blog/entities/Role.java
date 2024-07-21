package com.manish.blog.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Role {

	@Id
	private int id;
	
	 private String name;
	 
	 @ManyToMany(mappedBy = "roles")
	    private Set<User> users = new HashSet<>();

}
