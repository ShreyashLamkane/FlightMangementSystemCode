package com.flight.bookings.external.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.flight.bookings.entity.TransactionDetails;



@FeignClient(name="RAZORPAY-SERVICE")
public interface TransactionService {
	@GetMapping("/payment/makePayment/{amount}")
	public TransactionDetails createTransaction(@PathVariable Integer amount);
}
