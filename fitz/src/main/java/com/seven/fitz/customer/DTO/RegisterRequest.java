package com.seven.fitz.customer.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email required")
    private String email;

    @Size(min = 8,message="Password must be at least 8 characters")
    private String password;

    @NotBlank(message = "Confirm password required")
    private String confirmPassword;

    private Boolean agreeTerms;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Boolean getAgreeTerms() {
		return agreeTerms;
	}

	public void setAgreeTerms(Boolean agreeTerms) {
		this.agreeTerms = agreeTerms;
	}
    
    

}