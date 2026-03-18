package com.seven.fitz.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerEmailService {
	
	@Autowired
    private JavaMailSender mailSender;

    public void sendOtp(String email, String otp){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("SevenFitz Password Reset OTP");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);
    }
}
