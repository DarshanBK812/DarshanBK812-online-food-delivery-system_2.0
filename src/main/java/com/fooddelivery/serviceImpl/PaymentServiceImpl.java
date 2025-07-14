package com.fooddelivery.serviceImpl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.entity.Order;
import com.fooddelivery.entity.Payment;
import com.fooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.repository.OrderRepository;
import com.fooddelivery.repository.PaymentRepository;
import com.fooddelivery.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private PaymentRepository paymentRepo;

	@Override
	public String makePayment(int orderId, String method) {
		Order order = orderRepo.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order is not found Order id : " + orderId));

		if (order.getPayment() != null) {
			return "Payment already done for this order.";
		}

		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setMethod(method.toUpperCase());
		payment.setStatus("SUCCESS");
		payment.setTransactionId(UUID.randomUUID().toString().substring(0, 8));
		payment.setPaymentTime(LocalDateTime.now());

		paymentRepo.save(payment);

		return "Payment successful via " + method.toUpperCase() + ". Transaction ID: " + payment.getTransactionId();
	}
}
