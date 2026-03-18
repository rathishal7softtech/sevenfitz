package com.seven.fitz.admin.DTO;

public class ViewCategoryResponse {
	
	private Long id;
	private String Title;
	private String ImageUrl;
	private String Description;
	
	
	
	public ViewCategoryResponse(Long id, String title, String imageUrl, String description) {
		super();
		this.id = id;
		Title = title;
		ImageUrl = imageUrl;
		Description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	

}
