package com.flight.razorpay.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Transaction_details")
public class TransactionDetails {
	@Id
	private String orderId;
	private String currency;
	private int amount;
	private String paymentKey;
}
