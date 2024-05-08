package com.flight.razorpay.util;

import com.flight.razorpay.entity.TransactionDetails;

public interface PaymentUtil {
	public TransactionDetails createTransaction(Integer amount);
}
