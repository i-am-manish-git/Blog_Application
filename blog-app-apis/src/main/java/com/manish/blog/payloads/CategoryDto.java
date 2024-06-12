package com.manish.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min=4, message="Category title should be minimumof 4 characters.")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=20, message="Category description should be minimumof 20 characters.")
	private String categoryDescription;
	
}
