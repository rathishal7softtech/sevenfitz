package com.seven.fitz.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.seven.fitz.customer.DTO.ReviewRequest;
import com.seven.fitz.customer.service.CustomerReviewService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/customer/review")
public class CustomerReviewController {

    @Autowired
    private CustomerReviewService reviewService;

    @PostMapping("/add")
    public String addReview(
            @ModelAttribute ReviewRequest request,
            HttpSession session){

        Long userId = (Long) session.getAttribute("USER_ID");

        return reviewService.addReview(userId, request);
    }
}