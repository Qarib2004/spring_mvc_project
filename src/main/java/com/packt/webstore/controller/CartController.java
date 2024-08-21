package com.packt.webstore.controller;

import javax.servlet.http.HttpServletRequest;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService; // Допустим, у вас есть сервис для работы с корзинами

    @RequestMapping
    public String redirectToCart(HttpServletRequest request) {
        String cartId = request.getSession(true).getId();
        return "redirect:/cart/" + cartId;
    }

    @RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
    public String getCart(@PathVariable("cartId") String cartId, Model model) {
        // Получаем информацию о корзине через сервис
        Cart cart = cartService.getCartById(cartId);
        if (cart == null) {
            // Если корзина не найдена, перенаправляем на страницу с ошибкой или отображаем соответствующее сообщение
            return "redirect:/error";
        }

        model.addAttribute("cart", cart);
        model.addAttribute("cartId", cartId);
        return "cart";
    }
}