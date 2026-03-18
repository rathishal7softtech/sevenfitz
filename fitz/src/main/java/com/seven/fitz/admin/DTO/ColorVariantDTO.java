package com.seven.fitz.admin.DTO;

import java.util.List;

public class ColorVariantDTO {
	
	 private Long colorVariantId;   
	 private List<SizeDTO> sizes;
	 
	 public Long getColorVariantId() {
		 return colorVariantId;
	 }
	 public void setColorVariantId(Long colorVariantId) {
		 this.colorVariantId = colorVariantId;
	 }
	 public List<SizeDTO> getSizes() {
		 return sizes;
	 }
	 public void setSizes(List<SizeDTO> sizes) {
		 this.sizes = sizes;
	 }
	 
	 

}
