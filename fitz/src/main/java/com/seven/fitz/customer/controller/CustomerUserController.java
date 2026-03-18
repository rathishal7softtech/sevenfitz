package com.seven.fitz.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.seven.fitz.customer.DTO.UpdateProfileRequest;
import com.seven.fitz.customer.service.CustomerUserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
public class CustomerUserController {

    @Autowired
    private CustomerUserService userService;

    // GET PROFILE
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpSession session){

        Long userId = (Long) session.getAttribute("USER_ID");

        return ResponseEntity.ok(userService.getProfile(userId));
    }

    // UPDATE PROFILE
    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(
            HttpSession session,
            @RequestBody UpdateProfileRequest request){

        Long userId = (Long) session.getAttribute("USER_ID");

        return ResponseEntity.ok(userService.updateProfile(userId, request));
    }

}