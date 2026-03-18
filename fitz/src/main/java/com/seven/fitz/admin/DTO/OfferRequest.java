package com.seven.fitz.admin.DTO;

import java.time.LocalDate;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;


public class OfferRequest {
	
    private String title;
    private String subTitle;
    private Integer offerPercentage;
    private String description;
    private LocalDate offerClosedDate;
    
    @Schema(type = "string", format = "binary")
    private MultipartFile image;
    
    
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
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
    
    

}
