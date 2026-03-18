package com.seven.fitz.entity;

import jakarta.persistence.*;

@Entity
@Table(name="review_images")
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name="review_id")
    private ProductReview review;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ProductReview getReview() {
		return review;
	}

	public void setReview(ProductReview review) {
		this.review = review;
	}

    
}