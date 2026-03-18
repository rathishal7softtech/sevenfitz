package com.seven.fitz.customer.DTO;

public class ProductSizeResponse {
	
	private String size;
	 private Integer mrp;
	 private Integer stock;
	 public String getSize() {
		 return size;
	 }
	 public void setSize(String size) {
		 this.size = size;
	 }
	 public Integer getMrp() {
		 return mrp;
	 }
	 public void setMrp(Integer mrp) {
		 this.mrp = mrp;
	 }
	 public Integer getStock() {
		 return stock;
	 }
	 public void setStock(Integer stock) {
		 this.stock = stock;
	 }

}
