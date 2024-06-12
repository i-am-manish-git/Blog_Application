package com.manish.blog.services;

import java.util.List;

import com.manish.blog.payloads.CategoryDto;

public interface CategoryService {

	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
 
	//delete
	void deleteCategory(Integer categoryId);
	
	//getById
	CategoryDto getCategory(Integer categoryId);
	
	//getAll
	List<CategoryDto> getCategories();
}
