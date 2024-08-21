package com.packt.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.packt.webstore.exeption.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import com.packt.webstore.domain.Product;
import com.packt.webstore.service.ProductService;
public class ProductIdValidator implements
        ConstraintValidator<com.packt.webstore.validator.ProductId, String>{
    @Autowired
    private ProductService productService;
    public void initialize(com.packt.webstore.validator.ProductId constraintAnnotation) {
// intentionally left blank; this is the place toinitialize theconstraint annotation for any sensibledefault values.
    }
    public boolean isValid(String value,ConstraintValidatorContext
            context) {
        Product product;
        try {
            product = productService.getProductById(value);
        } catch (ProductNotFoundException e) {
            return true;
        }
        if(product!= null) {
            return false;
        }
        return true;
    }
}
