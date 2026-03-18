package com.seven.fitz.admin.DTO;

public class AddSubCategoryResponse {
	
	private Long id;
    private String title;
    private String categoryName;
    private String imageUrl;
    
    
	public AddSubCategoryResponse(Long id, String title, String categoryName, String imageUrl) {
		super();
		this.id = id;
		this.title = title;
		this.categoryName = categoryName;
		this.imageUrl = imageUrl;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
    
    

}

