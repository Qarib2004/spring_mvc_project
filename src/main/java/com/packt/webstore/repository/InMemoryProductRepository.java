package com.packt.webstore.repository;

import com.packt.webstore.exeption.ProductNotFoundException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import com.packt.webstore.domain.Product;

    @Repository
    public class InMemoryProductRepository implements ProductRepository{
        private List<Product> listOfProducts = new ArrayList<Product>();
        public InMemoryProductRepository() {
            Product iphone = new Product("P1234","iPhone 5s", new
                    BigDecimal(500));
            iphone.setDescription("Apple iPhone 5s smartphone with 4.00-inch 640x1136 display and 8-megapixel rear camera");
            iphone.setCategory("Smart Phone");
            iphone.setManufacturer("Apple");
            iphone.setUnitsInStock(1000);
            Product laptop_dell = new Product("P1235","Dell Inspiron", new
                    BigDecimal(700));
            laptop_dell.setDescription("Dell Inspiron 14-inch Laptop (Black) with 3rd Generation Intel Core processors");
            laptop_dell.setCategory("Laptop");
            laptop_dell.setManufacturer("Dell");
            laptop_dell.setUnitsInStock(1000);
            Product tablet_Nexus = new Product("P1236","Nexus 7", new BigDecimal(300));
            tablet_Nexus.setDescription("Google Nexus 7 is the lightest 7 inch tablet With a quad-core Qualcomm Snapdragon™ S4 Pro processor");
            tablet_Nexus.setCategory("Tablet");
            tablet_Nexus.setManufacturer("Google");
            tablet_Nexus.setUnitsInStock(1000);
            listOfProducts.add(iphone);
            listOfProducts.add(laptop_dell);
            listOfProducts.add(tablet_Nexus);
        }

        @Override
        public List<Product> getProductsByCategory(String category) {
                List<Product> productsByCategory = new ArrayList<Product>();
                for(Product product: listOfProducts) {
                    if(category.equalsIgnoreCase(product.getCategory())){
                        productsByCategory.add(product);
                    }
                }
                return productsByCategory;
            }

        @Override
        public void deleteById(String productId) {
            Product productToDelete = null;

            // Ищем продукт по ID
            for (Product product : listOfProducts) {
                if (product.getProductId().equals(productId)) {
                    productToDelete = product;
                    break;
                }
            }

            // Если продукт найден, удаляем его из списка
            if (productToDelete != null) {
                listOfProducts.remove(productToDelete);
            } else {
                throw new ProductNotFoundException("No products found with the product id: " + productId);
            }
        }

        @Override
        public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
            Set<Product> productsByBrand = new HashSet<Product>();
            Set<Product> productsByCategory = new HashSet<Product>();
            Set<String> criterias = filterParams.keySet();
            if(criterias.contains("brand")) {
                for(String brandName: filterParams.get("brand")) {
                    for(Product product: listOfProducts) {
                        if(brandName.equalsIgnoreCase(product.getManufacturer())){
                            productsByBrand.add(product);
                        }
                    }
                }
            }
            if(criterias.contains("category")) {
                for(String category: filterParams.get("category")) {
                    productsByCategory.addAll(this.getProductsByCategory(category));
                }
            }
            productsByCategory.retainAll(productsByBrand);
            return productsByCategory;

        }

//        @Override
//        public Product getProductById(String productID) {
//            Product productById = null;
//            for(Product product : listOfProducts) {
//                if(product!=null && product.getProductId()!=null &&
//                        product.getProductId().equals(productID)){
//                    productById = product;
//                    break;
//                }
//            }
//            if(productById == null){
//                throw new IllegalArgumentException("No products found with the product id: "+ productID);
//            }
//            return productById;
//        }

        @Override
        public List<Product> getAllProducts() {
            return listOfProducts;
        }

        @Override
        public List<Product> getProductsByManufacturer(String manufacturer) {
            return listOfProducts.stream()
                    .filter(product -> product.getManufacturer().equalsIgnoreCase(manufacturer))
                    .collect(Collectors.toList());
        }
        @Override
        public void addProduct(Product product) {
            listOfProducts.add(product);
        }

        public Product getProductById(String productId) {
            Product productById = null;
            for(Product product : listOfProducts) {
                if(product!=null && product.getProductId()!=null
                        &&product.getProductId().equals(productId)){
                    productById = product;
                    break;
                }
            }
            if(productById == null){
                throw new ProductNotFoundException("No products found withthe product id: "+ productId);
            }
            return productById;
        }

    }