package com.packt.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private static final long serialVersionUID = -6781341245112329820L;

    private List<CartItem> cartItems;
    private BigDecimal totalPrice;

    public Cart() {
        this.cartItems = new ArrayList<>();
        this.totalPrice = BigDecimal.ZERO;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        recalculateTotalPrice();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
        recalculateTotalPrice();
    }

    public void removeCartItem(String productId) {
        cartItems.removeIf(item -> item.getProduct().getProductId().equals(productId));
        recalculateTotalPrice();
    }

    public void recalculateTotalPrice() {
        totalPrice = cartItems.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                ", totalPrice=" + totalPrice +
                '}';
    }
}