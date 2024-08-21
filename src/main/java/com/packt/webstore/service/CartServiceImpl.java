package com.packt.webstore.service;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.CartItem;
import com.packt.webstore.exeption.InvalidCartException;
import com.packt.webstore.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void addCartItem(String cartId, CartItem cartItem) {
        Cart cart = cartRepository.findById(cartId);
        if (cart != null) {
            cart.addCartItem(cartItem);
            cartRepository.save(cart);
        }
    }

    @Override
    public void removeCartItem(String cartId, String productId) {
        Cart cart = cartRepository.findById(cartId);
        if (cart != null) {
            cart.removeCartItem(productId);
            cartRepository.save(cart);
        }
    }

    @Override
    public void updateCartItem(String cartId, CartItem cartItem) {
        Cart cart = cartRepository.findById(cartId);
        if (cart != null) {
            cart.removeCartItem(cartItem.getProduct().getProductId());
            cart.addCartItem(cartItem);
            cartRepository.save(cart);
        }
    }

    @Override
    public Cart getCartById(String cartId) {
        return cartRepository.findById(cartId);
    }

    @Override
    public void clearCart(String cartId) {
        Cart cart = cartRepository.findById(cartId);
        if (cart != null) {
            cart.setCartItems(new ArrayList<>());
            cartRepository.save(cart);
        }
    }
   @Override
    public Cart validate(String cartId) {
        Cart cart = cartRepository.findById(cartId);
        if(cart==null || cart.getCartItems().size()==0) {
            throw new InvalidCartException(cartId);
        }
        return cart;
    }

}