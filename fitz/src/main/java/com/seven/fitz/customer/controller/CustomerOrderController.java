package com.seven.fitz.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seven.fitz.customer.DTO.CheckoutRequest;
import com.seven.fitz.customer.service.CustomerOrderService;
import com.seven.fitz.entity.Order;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/customer/order")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService orderService;

    @PostMapping("/checkout")
    public String checkout(
            @RequestBody CheckoutRequest request,
            HttpSession session){

        Long userId = (Long) session.getAttribute("USER_ID");

        return orderService.checkout(userId, request);
    }
 // My Orders
    @GetMapping("/myorder")
    public List<Order> myOrders(HttpSession session){

        Long userId = (Long) session.getAttribute("USER_ID");

        return orderService.myOrders(userId);
    }

  // Track Order
    @GetMapping("/track/{id}")
    public Order trackOrder(@PathVariable Long id,HttpSession session){
    	
    	Long userId = (Long) session.getAttribute("USER_ID");
        return orderService.trackOrder(userId,id);
    }
}