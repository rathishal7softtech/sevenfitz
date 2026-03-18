package com.seven.fitz.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seven.fitz.customer.DTO.WishlistRequest;
import com.seven.fitz.entity.Product;
import com.seven.fitz.entity.WishList;
import com.seven.fitz.repository.ProductRepository;
import com.seven.fitz.repository.WishListRepository;

@Service
public class CustomerWishlistService {

    @Autowired
    private WishListRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    public String addWishlist(Long userId, WishlistRequest request){

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        
        boolean exists = wishlistRepository.existsByUserIdAndProductId(
                userId, request.getProductId());

        if(exists){
            return "Product already in wishlist";
        }

        WishList wishlist = new WishList();

        wishlist.setUserId(userId);
        wishlist.setProduct(product);

        wishlistRepository.save(wishlist);

        return "Added to wishlist";
    }

    public List<WishList> getWishlist(Long userId){

        return wishlistRepository.findByUserId(userId);
    }

    public String removeWishlist(Long userId, Long id){

        WishList wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wishlist item not found"));

        if(!wishlist.getUserId().equals(userId)){
            throw new RuntimeException("Unauthorized wishlist access");
        }

        wishlistRepository.delete(wishlist);

        return "Removed from wishlist";
    }
}
