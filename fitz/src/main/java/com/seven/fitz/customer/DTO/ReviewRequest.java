package com.seven.fitz.customer.DTO;

import org.springframework.web.multipart.MultipartFile;

public class ReviewRequest {
	
	private Long productId;
    private Integer rating;
    private String comment;

    private MultipartFile[] images;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public MultipartFile[] getImages() {
		return images;
	}

	public void setImages(MultipartFile[] images) {
		this.images = images;
	}
    
    

}
