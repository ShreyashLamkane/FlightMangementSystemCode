package com.flight.bookings.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetails {
	
	private String orderId;
	private String currency;
	private int amount;
	private String paymentKey;
}
