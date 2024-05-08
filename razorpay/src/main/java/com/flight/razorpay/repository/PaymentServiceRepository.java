package com.flight.razorpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flight.razorpay.entity.TransactionDetails;

public interface PaymentServiceRepository extends JpaRepository<TransactionDetails, String> {

}
