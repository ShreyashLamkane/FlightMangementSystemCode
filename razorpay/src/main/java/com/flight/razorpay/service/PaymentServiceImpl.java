package com.flight.razorpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.flight.razorpay.entity.TransactionDetails;
import com.flight.razorpay.repository.PaymentServiceRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentServiceRepository paymentServiceRepository;

	@Override
	public TransactionDetails createTransaction(TransactionDetails details) {
		// TODO Auto-generated method stub
		return paymentServiceRepository.save(details);
	}
}
