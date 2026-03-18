package com.seven.fitz.admin.DTO;

import java.util.List;

public class ProductCreateDTO {
	
    // Product Information
    private String name;
    private String description;

    // General Information
    private Long categoryId;
    private Long subCategoryId;

    // Product Details 
    private ProductDetailsDTO details;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public ProductDetailsDTO getDetails() {
		return details;
	}

	public void setDetails(ProductDetailsDTO details) {
		this.details = details;
	}
    
    
}
