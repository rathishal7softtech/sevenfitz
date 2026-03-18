package com.seven.fitz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class OfferProduct {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @ManyToOne
	 private Offer offer;

	 @ManyToOne
	 private Product product;

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public Offer getOffer() {
		 return offer;
	 }

	 public void setOffer(Offer offer) {
		 this.offer = offer;
	 }

	 public Product getProduct() {
		 return product;
	 }

	 public void setProduct(Product product) {
		 this.product = product;
	 }
	 
	 

}
