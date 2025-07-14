package com.fooddelivery.controller;

import com.fooddelivery.dto.AddToCartRequest;
import com.fooddelivery.entity.Cart;
import com.fooddelivery.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/add")
	public Cart addToCart(@RequestBody AddToCartRequest request) {
		return cartService.addToCart(request);
	}

	@GetMapping("/user/{userId}")
	public Cart getCart(@PathVariable int userId) {
		return cartService.getCartByUser(userId);
	}

	@DeleteMapping("/clear/{userId}")
	public ResponseEntity<String> clearCart(@PathVariable int userId) {
		cartService.clearCart(userId);
		return ResponseEntity.ok("Cart cleared successfully");
	}

}
