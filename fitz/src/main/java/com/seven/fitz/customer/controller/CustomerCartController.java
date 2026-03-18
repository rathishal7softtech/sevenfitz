package com.seven.fitz.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.seven.fitz.customer.DTO.AddToCartRequest;
import com.seven.fitz.customer.service.CustomerCartService;
import com.seven.fitz.entity.Cart;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/customer/cart")
public class CustomerCartController {

    @Autowired
    private CustomerCartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestBody AddToCartRequest request, HttpSession session){

        Long userId = (Long) session.getAttribute("USER_ID");

        return cartService.addToCart(userId, request);
    }

    @GetMapping("/view")
    public List<Cart> viewCart(HttpSession session){

        Long userId = (Long) session.getAttribute("USER_ID");

        return cartService.getCart(userId);
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable Long id, HttpSession session){

        Long userId = (Long) session.getAttribute("USER_ID");

        return cartService.removeCart(userId, id);
    }
}
