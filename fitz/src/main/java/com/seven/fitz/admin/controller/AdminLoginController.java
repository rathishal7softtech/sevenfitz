package com.seven.fitz.admin.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seven.fitz.admin.DTO.LoginRequest;
import com.seven.fitz.admin.service.AdminAuthService;
import com.seven.fitz.entity.AdminUser;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin")
public class AdminLoginController {
	
	@Autowired
    private AdminAuthService service;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request,
                        HttpSession session){

        AdminUser admin = service.login(request);
        session.setAttribute("ADMIN_ID", admin.getId());

        return "Admin login success";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){

        session.invalidate();

        return "Admin logout success";
    }
}
