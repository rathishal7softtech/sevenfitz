package com.seven.fitz.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// Color based size,stock,image and MRP selection


@Entity
@Table(
    name = "product_variants"
)
public class ProductVariant {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Color color;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private List<ProductSize> sizes;
    
    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private List<ProductVariantImage> images;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public List<ProductSize> getSizes() {
		return sizes;
	}

	public void setSizes(List<ProductSize> sizes) {
		this.sizes = sizes;
	}

	public List<ProductVariantImage> getImages() {
		return images;
	}

	public void setImages(List<ProductVariantImage> images) {
		this.images = images; 
	}
	
	
	
    
    
}
