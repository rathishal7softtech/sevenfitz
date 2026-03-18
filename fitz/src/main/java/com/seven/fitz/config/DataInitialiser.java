package com.seven.fitz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.seven.fitz.entity.AdminUser;
import com.seven.fitz.repository.AdminUserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitialiser {
	
	@Autowired
	private AdminUserRepository repo;
	
	private PasswordEncoder encoder;
	
	public DataInitialiser(AdminUserRepository repo,PasswordEncoder encoder) {
		this.repo=repo;
		this.encoder=encoder;
		
		
	}
	
	@PostConstruct
	public void createAdmin() {
		 
		if(repo.findByUsername("anjulijo").isPresent()) {
			return;
		}
		AdminUser user = new AdminUser();
		user.setUsername("anjulijo");
		user.setPassword(encoder.encode("anjulijo2002"));
		user.setRole("ADMIN");
		
		repo.save(user);
		
	}
	

}
