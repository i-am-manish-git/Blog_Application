package com.manish.blog.payloads;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;

import com.manish.blog.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;

	private  UserDto user;
	
	//getting comments through post
	private Set<CommentDto> comments = new HashSet<>();
	
	

}
