package com.fooddelivery.controller;

import com.fooddelivery.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PutMapping("/order/{orderId}")
    public ResponseEntity<String> payForOrder(@PathVariable int orderId,
                                               @RequestParam String method) {
        String result = paymentService.makePayment(orderId, method);
        return ResponseEntity.ok(result);
    }
}
