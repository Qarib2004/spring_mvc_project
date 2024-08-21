package com.packt.webstore.repository;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.repository.CartRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartRepositoryImpl implements CartRepository {

    private final Map<String, Cart> cartStore = new HashMap<>();

    @Override
    public Cart findById(String cartId) {
        return cartStore.get(cartId);
    }

    @Override
    public void save(Cart cart) {
        cartStore.put(String.valueOf(cart.getCartItems()), cart);
    }
}