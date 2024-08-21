package com.packt.webstore.service;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.CartItem;

import java.util.List;

public interface CartService {

        void addCartItem(String cartId, CartItem cartItem);
        void removeCartItem(String cartId, String productId);
        void updateCartItem(String cartId, CartItem cartItem);
        Cart getCartById(String cartId);
        void clearCart(String cartId);
        Cart validate(String cartId);



}