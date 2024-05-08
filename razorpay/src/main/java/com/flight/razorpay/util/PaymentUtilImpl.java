package com.flight.razorpay.util;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flight.razorpay.entity.TransactionDetails;
import com.flight.razorpay.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Component
public class PaymentUtilImpl implements PaymentUtil{
	
	
	private static final String KEY="rzp_test_NlKCkWX4YhN434";
	private static final String SECRET_KEY="sje5hqRa3NysNN5Sz8P1OcSt";
	private static final String CURRENCY="INR";
	
	@Autowired
	private PaymentService paymentService;
	
	public TransactionDetails createTransaction(Integer amount) {
		try {
			RazorpayClient razorpayClient= new RazorpayClient(KEY, SECRET_KEY);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("amount", amount);
			jsonObject.put("currency", CURRENCY);
			
			Order order=razorpayClient.orders.create(jsonObject);
			
			
			TransactionDetails details= prepareTransactionDetails(order);
			paymentService.createTransaction(details);
			System.out.println(details);
			return details;
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		return null;
	}
	
	private TransactionDetails prepareTransactionDetails(Order order) {
		String orderId=order.get("id");
		String currency=order.get("currency");
		int amount=order.get("amount");
		System.out.println("Here in");
		TransactionDetails transaction=new TransactionDetails(orderId,currency,amount,KEY);
		
		return transaction;
	}
}
