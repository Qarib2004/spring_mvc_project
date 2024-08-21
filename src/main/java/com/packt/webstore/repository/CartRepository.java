package com.packt.webstore.repository;

import com.packt.webstore.domain.Cart;

public interface CartRepository {
    Cart findById(String cartId);
    void save(Cart cart);
}
