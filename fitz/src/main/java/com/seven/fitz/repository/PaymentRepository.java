package com.seven.fitz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.seven.fitz.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Long>{
	
	List<Payment> findByOrder_User_Id(Long userId);

}
