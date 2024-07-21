package com.manish.blog.services;

import com.manish.blog.payloads.CommentDto;

public interface CommentService {

	//method to create comment
	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	//method to delete comment
	void deleteComment(Integer commentId);
}
