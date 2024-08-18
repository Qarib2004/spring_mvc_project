package com.packt.webstore.repository;

import com.packt.webstore.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface ProductRepository {
    List<Product> getAllProducts();
    Product getProductById(String productID);
    List<Product> getProductsByCategory(String category);
    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);

    void addProduct(Product product);

    List<Product> getProductsByManufacturer(String manufacturer);
}
