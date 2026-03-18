package com.seven.fitz.admin.DTO;

import java.time.LocalDateTime;

public class ListSubCategoryResponse {
	
	private Long id;
	private String subCategoryTitle;
	private String categoryTitle;
	
	
	
	public ListSubCategoryResponse(Long id, String subCategoryTitle, String categoryTitle) {
		super();
		this.id = id;
		this.subCategoryTitle = subCategoryTitle;
		this.categoryTitle = categoryTitle;
		
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSubCategoryTitle() {
		return subCategoryTitle;
	}
	public void setSubCategoryTitle(String subCategoryTitle) {
		this.subCategoryTitle = subCategoryTitle;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
}
