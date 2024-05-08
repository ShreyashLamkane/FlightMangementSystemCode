package com.flight.razorpay.service;

import com.flight.razorpay.entity.TransactionDetails;

public interface PaymentService {
	public TransactionDetails createTransaction(TransactionDetails details);
}
