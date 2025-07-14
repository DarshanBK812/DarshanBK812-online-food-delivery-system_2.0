package com.fooddelivery.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooddelivery.dto.AddToCartRequest;
import com.fooddelivery.entity.Cart;
import com.fooddelivery.entity.CartItem;
import com.fooddelivery.entity.FoodItem;
import com.fooddelivery.entity.User;
import com.fooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.repository.CartRepository;
import com.fooddelivery.repository.FoodItemRepository;
import com.fooddelivery.repository.UserRepository;
import com.fooddelivery.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	FoodItemRepository foodItemRepository;

	@Autowired
	CartRepository cartRepository;

	@Override
	public Cart addToCart(AddToCartRequest request) {

	    User user = userRepository.findById(request.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    FoodItem foodItem = foodItemRepository.findById(request.getFoodItemId())
	            .orElseThrow(() -> new ResourceNotFoundException("FoodItem is not found"));

	    if (foodItem.getStock() < request.getQuantity()) {
	        throw new RuntimeException(foodItem.getName() + " is out of stock!");
	    }

	    // Reduce stock
	    foodItem.setStock(foodItem.getStock() - request.getQuantity());
	    foodItemRepository.save(foodItem);

	    // Get or create cart
	    Cart cart = user.getCart();
	    if (cart == null) {
	        cart = new Cart();
	        cart.setUser(user);
	        cart.setItems(new ArrayList<>());
	    }

	    // Check if item already exists in cart
	    CartItem existingItem = null;
	    for (CartItem item : cart.getItems()) {
	        if (item.getFoodItem().getId() == foodItem.getId()) {
	            existingItem = item;
	            break;
	        }
	    }

	    if (existingItem != null) {
	        existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
	    } else {
	        CartItem newItem = new CartItem();
	        newItem.setCart(cart);
	        newItem.setFoodItem(foodItem);
	        newItem.setQuantity(request.getQuantity());
	        cart.getItems().add(newItem);
	    }

	    return cartRepository.save(cart);
	}


	@Override
	public Cart getCartByUser(int userId) {

		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		return user.getCart();

	}

	@Override
	public void clearCart(int userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

		Cart cart = user.getCart();
		if (cart != null) {
			cart.getItems().clear();
			cartRepository.save(cart);
		}
	}

}
