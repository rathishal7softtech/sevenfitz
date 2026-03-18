package com.seven.fitz.customer.DTO;

import java.util.List;

public class ProductResponse {

	private Long id;
    private String name;
    private String description;
    private String brand;

    private String category;
    private String subCategory;

    private List<ProductVariantResponse> variants;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public List<ProductVariantResponse> getVariants() {
		return variants;
	}

	public void setVariants(List<ProductVariantResponse> variants) {
		this.variants = variants;
	}
    
    
}
