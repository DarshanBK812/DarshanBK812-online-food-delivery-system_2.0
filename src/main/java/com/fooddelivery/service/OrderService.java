package com.fooddelivery.service;

import com.fooddelivery.dto.PlaceOrderRequest;
import com.fooddelivery.entity.OrderStatus;

import java.util.List;

import com.fooddelivery.dto.OrderResponseDTO;

public interface OrderService {
    OrderResponseDTO placeOrder(PlaceOrderRequest request);
    
    void updateOrderStatus(int orderId, OrderStatus status);
    
    List<OrderResponseDTO> getOrderHistoryByUserId(int userId);


}
