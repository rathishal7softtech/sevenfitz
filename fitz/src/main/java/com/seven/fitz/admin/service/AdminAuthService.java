package com.seven.fitz.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seven.fitz.admin.DTO.LoginRequest;
import com.seven.fitz.entity.AdminUser;
import com.seven.fitz.repository.AdminUserRepository;

@Service
public class AdminAuthService {

    @Autowired
    private AdminUserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    public AdminUser login(LoginRequest request){

        AdminUser admin = repo.findByUsername(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if(!encoder.matches(request.getPassword(), admin.getPassword())){
            throw new RuntimeException("Invalid password");
        }

        return admin;
    }
}