package com.seven.fitz.admin.DTO;

public class ProductListResponse {
	
	private Long id;
    private String name;
    private String image;
    private String category;
    private String subCategory;
    private String brand;
    
    
    
    
    
	public ProductListResponse() {
//		super();
	}


	public ProductListResponse(Long id, String name, String image, String category, String subCategory,String Brand) {
		super();
		this.id = id;
		this.name = name;
		
		this.category = category;
		this.subCategory = subCategory;
		this.brand=Brand;		
	}
	
	
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


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
	
	
    
    

}
