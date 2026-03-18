package com.seven.fitz.admin.DTO;

public class EditSubCategoryResponse {

    private Long id;
    private String title;
    private String categoryTitle;
    private String imageUrl;

    public EditSubCategoryResponse(Long id, String title,
                                   String categoryTitle, String imageUrl) {
        this.id = id;
        this.title = title;
        this.categoryTitle = categoryTitle;
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

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
    
}
