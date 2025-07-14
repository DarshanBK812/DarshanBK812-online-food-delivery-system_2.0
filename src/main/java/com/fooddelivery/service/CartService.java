package com.fooddelivery.service;

import com.fooddelivery.dto.AddToCartRequest;
import com.fooddelivery.entity.Cart;

public interface CartService {
    Cart addToCart(AddToCartRequest request);
    Cart getCartByUser(int userId);
     void clearCart(int userId);

}
