package com.seven.fitz.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.seven.fitz.customer.DTO.WishlistRequest;
import com.seven.fitz.customer.service.CustomerWishlistService;
import com.seven.fitz.entity.WishList;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/customer/wishlist")
public class CustomerWishlistController {

    @Autowired
    private CustomerWishlistService wishlistService;

    @PostMapping("/add")
    public String addWishlist(
            @RequestBody WishlistRequest request,
            HttpSession session){

        Long userId = (Long) session.getAttribute("CUSTOMER_ID");

        return wishlistService.addWishlist(userId, request);
    }

    @GetMapping("/get")
    public List<WishList> getWishlist(HttpSession session){

        Long userId = (Long) session.getAttribute("CUSTOMER_ID");

        return wishlistService.getWishlist(userId);
    }

    @DeleteMapping("/remove/{id}")
    public String removeWishlist(@PathVariable Long id,HttpSession session){

    	Long userId = (Long) session.getAttribute("USER_ID");

        return wishlistService.removeWishlist(userId, id);
    }
}
