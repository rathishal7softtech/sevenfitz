package com.seven.fitz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.seven.fitz.entity.PasswordResetOtp;

public interface PasswordResetOtpRepository extends JpaRepository<PasswordResetOtp,Long>{
	
	Optional<PasswordResetOtp> findTopByEmailOrderByExpiryTimeDesc(String email);
	
	@Modifying
	@Transactional
	void deleteByEmail(String email);
}
