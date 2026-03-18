package com.seven.fitz.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seven.fitz.customer.DTO.PaymentRequest;
import com.seven.fitz.entity.Order;
import com.seven.fitz.entity.OrderStatus;
import com.seven.fitz.entity.Payment;
import com.seven.fitz.repository.OrderRepository;
import com.seven.fitz.repository.PaymentRepository;

@Service
public class CustomerPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public String makePayment(Long userId,PaymentRequest request){

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        if(!order.getUser().getId().equals(userId)){
            throw new RuntimeException("Unauthorized payment");
        }


        Payment payment = new Payment();

        payment.setOrder(order);
        payment.setPaymentMethod(request.getMethod());
        payment.setStatus("SUCCESS");

        paymentRepository.save(payment);

        order.setStatus(OrderStatus.PAID);

        orderRepository.save(order);

        return "Payment successful";
    }
    
    public List<Payment> getUserPayments(Long userId){
        return paymentRepository.findByOrder_User_Id(userId);
    }
}
