package com.manish.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manish.blog.entities.Category;
import com.manish.blog.entities.Post;
import com.manish.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	//Method to get all the users' post and category
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	//search by title
	List<Post> findByTitleContaining(String title);
}
