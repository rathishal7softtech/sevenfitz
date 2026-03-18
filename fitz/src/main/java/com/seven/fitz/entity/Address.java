package com.seven.fitz.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="addresses")
public class Address {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private String fullName;

	 private String phone;

	 private String city;

	 private String street;

	 private String state;

	 private String pincode;

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public String getFullName() {
		 return fullName;
	 }

	 public void setFullName(String fullName) {
		 this.fullName = fullName;
	 }

	 public String getPhone() {
		 return phone;
	 }

	 public void setPhone(String phone) {
		 this.phone = phone;
	 }

	 public String getCity() {
		 return city;
	 }

	 public void setCity(String city) {
		 this.city = city;
	 }

	 public String getStreet() {
		 return street;
	 }

	 public void setStreet(String street) {
		 this.street = street;
	 }

	 public String getState() {
		 return state;
	 }

	 public void setState(String state) {
		 this.state = state;
	 }

	 public String getPincode() {
		 return pincode;
	 }

	 public void setPincode(String pincode) {
		 this.pincode = pincode;
	 }
	 
	 
}
