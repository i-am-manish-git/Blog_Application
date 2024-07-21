package com.manish.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manish.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
