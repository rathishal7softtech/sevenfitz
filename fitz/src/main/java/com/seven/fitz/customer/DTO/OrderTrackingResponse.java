package com.seven.fitz.customer.DTO;

import java.time.LocalDateTime;

public class OrderTrackingResponse {
	
	 private Long orderId;
	 private String status;
	 private Integer totalAmount;
	 private LocalDateTime createdAt;
	 
	 public Long getOrderId() {
		 return orderId;
	 }
	 public void setOrderId(Long orderId) {
		 this.orderId = orderId;
	 }
	 public String getStatus() {
		 return status;
	 }
	 public void setStatus(String status) {
		 this.status = status;
	 }
	 public Integer getTotalAmount() {
		 return totalAmount;
	 }
	 public void setTotalAmount(Integer totalAmount) {
		 this.totalAmount = totalAmount;
	 }
	 public LocalDateTime getCreatedAt() {
		 return createdAt;
	 }
	 public void setCreatedAt(LocalDateTime createdAt) {
		 this.createdAt = createdAt;
	 }
	 
	 
}
