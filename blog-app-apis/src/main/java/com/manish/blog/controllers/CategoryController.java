package com.manish.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.manish.blog.payloads.ApiResponse;
import com.manish.blog.payloads.CategoryDto;
import com.manish.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
	private CategoryService categoryService;
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory( @Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory =this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
		CategoryDto updatedCategory =this.categoryService.updateCategory(categoryDto,catId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is  deleted Successfully!", true), HttpStatus.OK);
	}
	
	//get category by id
		@GetMapping("/{catId}")
		public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
			CategoryDto categoryDto = this.categoryService.getCategory(catId);
			return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
		}
	
	//get all category
		@GetMapping("/")
		public ResponseEntity<List<CategoryDto>> getCategories(){
			
			List<CategoryDto> categories = this.categoryService.getCategories();
			return ResponseEntity.ok(categories);
		}
}
