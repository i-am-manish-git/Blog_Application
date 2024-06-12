package com.manish.blog.services;

import java.util.List;

import com.manish.blog.entities.Post;
import com.manish.blog.payloads.PostDto;
import com.manish.blog.payloads.PostResponse;

public interface PostService {

	//create
	PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all Posts
	PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy, String sortDir);
	
	//get single post 
	PostDto getPostById(Integer postId);
	
	//get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);
	
}
