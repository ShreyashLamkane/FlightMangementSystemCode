package com.flight.razorpay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight.razorpay.entity.TransactionDetails;
import com.flight.razorpay.util.PaymentUtil;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentUtil paymentUtil;
	
	
	@GetMapping("/makePayment/{amount}")
	public TransactionDetails createTransaction(@PathVariable Integer amount) {
		return paymentUtil.createTransaction(amount);
	}
}
