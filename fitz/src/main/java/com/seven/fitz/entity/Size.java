package com.seven.fitz.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Size")
public class Size {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  @Column(nullable = false, unique = true)
	  private String name;

	  @CreationTimestamp
	  private LocalDateTime createdAt;

	  @UpdateTimestamp
	  private LocalDateTime updatedAt;

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

	  public LocalDateTime getCreatedAt() {
		  return createdAt;
	  }

	  public void setCreatedAt(LocalDateTime createdAt) {
		  this.createdAt = createdAt;
	  }

	  public LocalDateTime getUpdatedAt() {
		  return updatedAt;
	  }

	  public void setUpdatedAt(LocalDateTime updatedAt) {
		  this.updatedAt = updatedAt;
	  }
	  
	  

}
