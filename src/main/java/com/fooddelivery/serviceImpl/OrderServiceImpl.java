package com.fooddelivery.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.dto.OrderItemDTO;
import com.fooddelivery.dto.OrderResponseDTO;
import com.fooddelivery.dto.PlaceOrderRequest;
import com.fooddelivery.entity.Cart;
import com.fooddelivery.entity.CartItem;
import com.fooddelivery.entity.FoodItem;
import com.fooddelivery.entity.Order;
import com.fooddelivery.entity.OrderItem;
import com.fooddelivery.entity.OrderStatus;
import com.fooddelivery.entity.User;
import com.fooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.repository.CartItemRepository;
import com.fooddelivery.repository.CartRepository;
import com.fooddelivery.repository.FoodItemRepository;
import com.fooddelivery.repository.OrderItemRepository;
import com.fooddelivery.repository.OrderRepository;
import com.fooddelivery.repository.UserRepository;
import com.fooddelivery.service.OrderService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private OrderItemRepository orderItemRepo;

	@Autowired
	private CartItemRepository cartItemRepo;
	
	@Autowired
	private FoodItemRepository foodItemRepository;

	@Override
	public OrderResponseDTO placeOrder(PlaceOrderRequest request) {
	    User user = userRepo.findById(request.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Cart cart = user.getCart();
	    if (cart == null || cart.getItems().isEmpty()) {
	        throw new ResourceNotFoundException("Cart is empty");
	    }

	    Order order = new Order();
	    order.setUser(user);
	    order.setOrderTime(LocalDateTime.now());

	    List<OrderItem> orderItems = new ArrayList<>();

	    for (CartItem cartItem : cart.getItems()) {
	        FoodItem food = foodItemRepository.findById(cartItem.getFoodItem().getId())
	                .orElseThrow(() -> new ResourceNotFoundException("FoodItem is not found :" + cartItem.getFoodItem().getId()));

	        if (food.getStock() < cartItem.getQuantity()) {
	            throw new RuntimeException(food.getName() + " is out of stock or insufficient quantity.");
	        }

	        // Decrease stock
	        food.setStock(food.getStock() - cartItem.getQuantity());
	        foodItemRepository.save(food);

	        // Create order item
	        OrderItem orderItem = new OrderItem();
	        orderItem.setFoodItem(food);
	        orderItem.setQuantity(cartItem.getQuantity());
	        orderItem.setPrice(cartItem.getQuantity() * food.getPrice());
	        orderItem.setOrder(order);
	        orderItems.add(orderItem);
	    }

	    // Set items and total
	    order.setItems(orderItems);
	    double totalAmount = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
	    order.setTotalAmount(totalAmount);

	    // Save order
	    Order savedOrder = orderRepo.save(order);

	    // Clear cart
	    cart.getItems().clear();
	    cartRepo.save(cart);

	    // Prepare response DTO
	    List<OrderItemDTO> itemDTOs = savedOrder.getItems().stream().map(item -> {
	        OrderItemDTO dto = new OrderItemDTO();
	        dto.setFoodItemName(item.getFoodItem().getName());
	        dto.setQuantity(item.getQuantity());
	        dto.setPrice(item.getPrice());
	        return dto;
	    }).collect(Collectors.toList());

	    OrderResponseDTO response = new OrderResponseDTO();
	    response.setOrderId(savedOrder.getId());
	    response.setTotalAmount(savedOrder.getTotalAmount());
	    response.setOrderTime(savedOrder.getOrderTime());
	    response.setItems(itemDTOs);

	    return response;
	}


	@Override
	public void updateOrderStatus(int orderId, OrderStatus status) {
		Order order = orderRepo.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order is not found "));
		order.setStatus(status);
		orderRepo.save(order);
	}

	@Override
	public List<OrderResponseDTO> getOrderHistoryByUserId(int userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User Not found"));

		List<Order> orders = orderRepo.findByUser(user);

		return orders.stream().map(order -> {
			OrderResponseDTO dto = new OrderResponseDTO();
			dto.setOrderId(order.getId());
			dto.setStatus(order.getStatus());
			dto.setOrderTime(order.getOrderTime());
			dto.setTotalAmount(order.getTotalAmount());

			List<OrderItemDTO> itemDTOs = order.getItems().stream().map(item -> {
				OrderItemDTO itemDTO = new OrderItemDTO();
				itemDTO.setFoodItemName(item.getFoodItem().getName());
				itemDTO.setQuantity(item.getQuantity());
				itemDTO.setPrice(item.getPrice());
				return itemDTO;
			}).toList();

			dto.setItems(itemDTOs);
			return dto;
		}).toList();
	}

}
