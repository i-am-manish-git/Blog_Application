package com.manish.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manish.blog.entities.Comment;

public interface CommentRepo extends  JpaRepository<Comment, Integer>{

}
