package com.packt.webstore.service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public void processOrder(String productID, int count) {

            Product productById = productRepository.getProductById(productID);
            if (productById.getUnitsInStock() < count) {
                throw new IllegalArgumentException("Out of Stock. Available Units in stock" + productById.getUnitsInStock());
            }
            productById.setUnitsInStock(productById.getUnitsInStock() -
                    count);
        }
    }

