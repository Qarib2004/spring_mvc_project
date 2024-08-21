package com.packt.webstore.service;

import com.packt.webstore.domain.Order;

public interface OrderService {
    void processOrder(String productID,int count);

    Long saveOrder(Order order);

}
