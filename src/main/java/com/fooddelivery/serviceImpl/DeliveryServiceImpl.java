package com.fooddelivery.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.entity.DeliveryPerson;
import com.fooddelivery.entity.Order;
import com.fooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.repository.DeliveryPersonRepository;
import com.fooddelivery.repository.OrderRepository;
import com.fooddelivery.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService {
	
	

	    @Autowired
	    private OrderRepository orderRepo;

	    @Autowired
	    private DeliveryPersonRepository deliveryPersonRepo;

	    @Override
	    public String assignDeliveryPersonToOrder(int orderId) {
	        Order order = orderRepo.findById(orderId)
	                .orElseThrow(() -> new ResourceNotFoundException("Order id"+ orderId));

	        if (order.getDeliveryPerson() != null) {
	            return "Delivery person already assigned to this order.";
	        }

	        DeliveryPerson dp = deliveryPersonRepo.findFirstByAvailableTrue()
	                .orElseThrow(() -> new RuntimeException("No delivery person available"));

	        order.setDeliveryPerson(dp);
	        dp.setAvailable(false);

	        orderRepo.save(order);
	        deliveryPersonRepo.save(dp);

	        return "Delivery assigned to: " + dp.getName();
	    }
	}



