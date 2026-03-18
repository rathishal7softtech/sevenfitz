package com.seven.fitz.admin.DTO;

import java.util.List;

public class ProductVariantDTO {
	
	 private Long productId;
	 private List<ColorVariantDTO> variants;
	 
	 public Long getProductId() {
		 return productId;
	 }
	 public void setProductId(Long productId) {
		 this.productId = productId;
	 }
	 public List<ColorVariantDTO> getVariants() {
		 return variants;
	 }
	 public void setVariants(List<ColorVariantDTO> variants) {
		 this.variants = variants;
	 }
	 
	 
}
