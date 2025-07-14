package com.fooddelivery.service;

public interface PaymentService {

	String makePayment(int orderId, String method);
}
