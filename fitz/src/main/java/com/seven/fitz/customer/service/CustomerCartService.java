package com.seven.fitz.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seven.fitz.customer.DTO.AddToCartRequest;
import com.seven.fitz.entity.Cart;
import com.seven.fitz.entity.ProductVariant;
import com.seven.fitz.entity.Size;
import com.seven.fitz.repository.CartRepository;
import com.seven.fitz.repository.ProductVariantRepository;
import com.seven.fitz.repository.SizeRepository;

@Service
public class CustomerCartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductVariantRepository variantRepository;

    @Autowired
    private SizeRepository sizeRepository;

    public String addToCart(Long userId, AddToCartRequest request){

        ProductVariant variant = variantRepository.findById(request.getVariantId())
                .orElseThrow(() -> new RuntimeException("Variant not found"));

        Size size = sizeRepository.findById(request.getSizeId())
                .orElseThrow(() -> new RuntimeException("Size not found"));

        Cart cart = new Cart();

        cart.setUserId(userId);
        cart.setVariant(variant);
        cart.setSize(size);
        cart.setQuantity(request.getQuantity());

        cartRepository.save(cart);

        return "Added to cart";
    }

    public List<Cart> getCart(Long userId){

        return cartRepository.findByUserId(userId);
    }

    public String removeCart(Long userId, Long cartId){

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // security check
        if(!cart.getUserId().equals(userId)){
            throw new RuntimeException("Unauthorized cart access");
        }

        cartRepository.delete(cart);

        return "Item removed";
    }
}