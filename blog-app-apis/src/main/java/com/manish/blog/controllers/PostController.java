package com.manish.blog.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

import org.antlr.v4.runtime.misc.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.manish.blog.payloads.ApiResponse;
import com.manish.blog.payloads.PostDto;
import com.manish.blog.payloads.PostResponse;
import com.manish.blog.services.FileService;
import com.manish.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService; //for image 
	
	@Value("${project.image}") //path of the folder that we created in application.properties
	private String path;
	
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(
		    @RequestBody PostDto postDto,
		    @PathVariable("userId") Integer userId,
	        @PathVariable("categoryId") Integer categoryId) {
	    
		
		PostDto createPost =this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId){
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	   //get by category
		@GetMapping("/category/{categoryId}/posts")
		public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Integer categoryId){
			List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
			return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
		}
		
		
        //get post by id
		@GetMapping("/posts/{postId}")
		public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId){
			PostDto postDto = this.postService.getPostById(postId);
			return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
		}
	
        //get all post
		@GetMapping("/posts")
		public ResponseEntity<PostResponse> getAllPost(
				@RequestParam(value = "pageNumber", defaultValue = "0", required =false)Integer pageNumber,
				@RequestParam(value = "pageSize", defaultValue = "2", required =false)Integer pageSize,
				@RequestParam(value = "sortBy", defaultValue = "postId", required =false)String sortBy,
				@RequestParam(value = "sortDir", defaultValue = "asc", required =false)String sortDir){
			
			PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
			return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
		}
				
				
		//update post
		@PutMapping("/posts/{postId}")
		public ResponseEntity<PostDto> updatePost( @RequestBody PostDto postDto, @PathVariable("postId") Integer postId){
			PostDto updatedPost =this.postService.updatePost(postDto,postId);
			return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
		}
		
		//delete post
		@DeleteMapping("/posts/{postId}")
		public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId){
			this.postService.deletePost(postId);
			return new ResponseEntity<ApiResponse>(new ApiResponse("Post is  deleted Successfully!", true), HttpStatus.OK);
		}
		
		//get by search( post title)
		@GetMapping("/posts/search/{keywords}")
		public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
			List<PostDto> result =this.postService.searchPosts(keywords);
			return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
		}
		
		//method to upload image
		@PostMapping("/post/image/upload/{postId}")
		public ResponseEntity<PostDto> uploadPostImage(
				@RequestParam("image") MultipartFile image,
				@PathVariable("postId") Integer postId) throws IOException{
			
			PostDto postDto = this.postService.getPostById(postId);
			String fileName = this.fileService.uploadImage(path, image);
			postDto.setImageName(fileName);
			PostDto updatePost = this.postService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
			
		}
		
		//method to serve image
		@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
		public void downloadImage(
				@PathVariable("imageName") String imageName,
				HttpServletResponse response) throws IOException {
			
			InputStream resource = this.fileService.getResource(path, imageName);
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(resource,response.getOutputStream());
			
		}
						
				
}
