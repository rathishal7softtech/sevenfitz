package com.seven.fitz.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.seven.fitz.customer.DTO.PaymentRequest;
import com.seven.fitz.customer.service.CustomerPaymentService;
import com.seven.fitz.entity.Payment;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/customer/payment")
public class CustomerPaymentController {

    @Autowired
    private CustomerPaymentService paymentService;

    @PostMapping("/pay")
    public String payment(@RequestBody PaymentRequest request,HttpSession session){
    	
    	Long userId = (Long) session.getAttribute("USER_ID");

        return paymentService.makePayment(userId,request);
    }
    @GetMapping("/user/{userId}")
    public List<Payment> getPayments(@PathVariable Long userId){
        return paymentService.getUserPayments(userId);
    }
}