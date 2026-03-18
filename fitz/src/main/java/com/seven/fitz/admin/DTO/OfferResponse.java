package com.seven.fitz.admin.DTO;

import java.time.LocalDate;

public class OfferResponse {
	
	 private Long id;
	 private String title;
	 private String subTitle;
	 private Integer offerPercentage;
	 private String description;
	 private LocalDate offerClosedDate;
	 private Boolean expired;
	 
	 
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
	 public String getSubTitle() {
		 return subTitle;
	 }
	 public void setSubTitle(String subTitle) {
		 this.subTitle = subTitle;
	 }
	 public Integer getOfferPercentage() {
		 return offerPercentage;
	 }
	 public void setOfferPercentage(Integer offerPercentage) {
		 this.offerPercentage = offerPercentage;
	 }
	 public String getDescription() {
		 return description;
	 }
	 public void setDescription(String description) {
		 this.description = description;
	 }
	 public LocalDate getOfferClosedDate() {
		 return offerClosedDate;
	 }
	 public void setOfferClosedDate(LocalDate offerClosedDate) {
		 this.offerClosedDate = offerClosedDate;
	 }
	 public Boolean getExpired() {
		 return expired;
	 }
	 public void setExpired(Boolean expired) {
		 this.expired = expired;
	 }
	 
	 
	 


}
