package com.seven.fitz.admin.DTO;

public class SubcategoryDropdownResponse {
	
	private Long id;
	private String Title;
	
	
	public SubcategoryDropdownResponse(Long id, String title) {
		super();
		this.id = id;
		Title = title;
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
	
	

}
