package com.fooddelivery.controller;

import com.fooddelivery.dto.PlaceOrderRequest;
import com.fooddelivery.entity.OrderStatus;
import com.fooddelivery.dto.OrderResponseDTO;
import com.fooddelivery.service.OrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	public OrderResponseDTO placeOrder(@RequestBody PlaceOrderRequest request) {
		return orderService.placeOrder(request);
	}
	
	@PutMapping("/order/{orderId}/status")
	public ResponseEntity<String> updateOrderStatus(
	    @PathVariable int orderId,
	    @RequestParam String status) {

	    OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
	    orderService.updateOrderStatus(orderId, orderStatus);
	    return ResponseEntity.ok("Order status updated to: " + orderStatus);
	}
	
	@GetMapping("/order/user/{userId}/history")
	public ResponseEntity<List<OrderResponseDTO>> getUserOrderHistory(@PathVariable int userId) {
	    List<OrderResponseDTO> orders = orderService.getOrderHistoryByUserId(userId);
	    return ResponseEntity.ok(orders);
	}



}
