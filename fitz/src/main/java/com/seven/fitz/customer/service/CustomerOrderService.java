package com.seven.fitz.customer.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seven.fitz.customer.DTO.CheckoutRequest;
import com.seven.fitz.entity.Cart;
import com.seven.fitz.entity.Order;
import com.seven.fitz.entity.OrderItem;
import com.seven.fitz.entity.OrderStatus;
import com.seven.fitz.entity.User;
import com.seven.fitz.repository.CartRepository;
import com.seven.fitz.repository.OrderRepository;
import com.seven.fitz.repository.UserRepository;

@Service
public class CustomerOrderService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;

    public String checkout(Long userId, CheckoutRequest request){

        List<Cart> carts = cartRepository.findByUserId(userId);

        if(carts.isEmpty()){
            throw new RuntimeException("Cart empty");
        }

        Order order = new Order();
        order.setAddressId(request.getAddressId());
        order.setStatus(OrderStatus.PLACED);
        order.setCreatedAt(LocalDateTime.now());
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        order.setUser(user);

        int total = 0;

        List<OrderItem> items = new ArrayList<>();

        for(Cart cart : carts){

            OrderItem item = new OrderItem();

            item.setOrder(order);
            item.setVariant(cart.getVariant());
            item.setSize(cart.getSize());
            item.setQuantity(cart.getQuantity());

            int price = cart.getVariant()
                    .getSizes()
                    .stream()
                    .filter(s -> s.getSize().getId()
                    .equals(cart.getSize().getId()))
                    .findFirst()
                    .get()
                    .getMrp();

            item.setPrice(price);

            total += price * cart.getQuantity();

            items.add(item);
        }

        order.setItems(items);
        order.setTotalAmount(total);

        orderRepository.save(order);

        cartRepository.deleteAll(carts);

        return "Order placed";
    }
    
    public List<Order> myOrders(Long userId){

        return orderRepository.findByUser_Id(userId);

    }

    public Order trackOrder(Long userId, Long orderId){

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if(!order.getUser().getId().equals(userId)){
            throw new RuntimeException("Unauthorized order access");
        }

        return order;
    }
    
}