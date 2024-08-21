package com.packt.webstore.controller;

import com.packt.webstore.domain.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @RequestMapping(method = RequestMethod.GET)
    public String getCheckoutPage(Model model) {
        // Добавьте модель с информацией о заказе
        model.addAttribute("order", new Order());
        return "checkout";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processCheckout(@ModelAttribute("order") Order order, Model model) {
        // Обработка заказа
        return "orderConfirmation";
    }
}