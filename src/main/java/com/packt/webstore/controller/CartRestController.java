package com.packt.webstore.controller;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.CartItem;
import com.packt.webstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/rest/cart")
public class CartRestController {

    @Autowired
    private CartService cartService; // Убедитесь, что у вас есть сервис для работы с корзиной

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable("cartId") String cartId) {
        Cart cart = cartService.getCartById(cartId);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> clearCart(@PathVariable("cartId") String cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/add/{productId}")
    public ResponseEntity<Void> addToCart(@PathVariable("productId")CartItem cartItem,
                                          @RequestParam("cartId") String cartId) {
        cartService.addCartItem(cartId, cartItem);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/remove/{productId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable("productId") String productId,
                                               @RequestParam("cartId") String cartId) {
        cartService.removeCartItem(cartId, productId);
        return ResponseEntity.noContent().build();
    }
}
