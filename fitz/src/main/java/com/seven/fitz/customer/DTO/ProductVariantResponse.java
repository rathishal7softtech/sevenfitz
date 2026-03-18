package com.seven.fitz.customer.DTO;

import java.util.List;

public class ProductVariantResponse {
	
	private String color;

    private List<String> images;

    private List<ProductSizeResponse> sizes;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public List<ProductSizeResponse> getSizes() {
		return sizes;
	}

	public void setSizes(List<ProductSizeResponse> sizes) {
		this.sizes = sizes;
	}
    
    

}
