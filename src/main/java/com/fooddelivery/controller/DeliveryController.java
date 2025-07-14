package com.fooddelivery.controller;

import com.fooddelivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PutMapping("/assign/{orderId}")
    public ResponseEntity<String> assignDelivery(@PathVariable int orderId) {
        String result = deliveryService.assignDeliveryPersonToOrder(orderId);
        return ResponseEntity.ok(result);
    }
}
