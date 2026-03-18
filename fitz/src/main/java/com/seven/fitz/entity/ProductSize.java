package com.seven.fitz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_sizes")
public class ProductSize {
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @ManyToOne
	 @JoinColumn(name = "size_id", nullable = false)
	 private Size size; 
	 
	 private Integer stock;
	 private Integer mrp;

	 @ManyToOne
	 @JoinColumn(name = "variant_id", nullable = false)
	 private ProductVariant variant;

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	

	 public Size getSize() {
		return size;
	}

	 public void setSize(Size size) {
		 this.size = size;
	 }

	 public Integer getStock() {
		 return stock;
	 }

	 public void setStock(Integer stock) {
		 this.stock = stock;
	 }

	 public Integer getMrp() {
		 return mrp;
	 }

	 public void setMrp(Integer mrp) {
		 this.mrp = mrp;
	 }

	 public ProductVariant getVariant() {
		 return variant;
	 }

	 public void setVariant(ProductVariant variant) {
		 this.variant = variant;
	 }
	 
	 
}
