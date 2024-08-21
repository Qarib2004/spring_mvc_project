package com.packt.webstore.validator;

import java.math.BigDecimal;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.packt.webstore.domain.Product;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
//@ComponentScan(basePackages = "com.packt.webstore")
public class UnitsInStockValidator implements ConstraintValidator<UnitsInStock, Integer>,Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        // Проверка на основе UnitPrice и UnitsInStock
        if (product.getUnitPrice() != null &&
                new BigDecimal(10000).compareTo(product.getUnitPrice()) <= 0 &&
                product.getUnitsInStock() > 99) {
            errors.rejectValue("unitsInStock", "com.packt.webstore.validator.UnitsInStockValidator.message");
        }
    }

    @Override
    public void initialize(com.packt.webstore.validator.UnitsInStock constraintAnnotation) {
        // Инициализация, если требуется
    }

    @Override
    public boolean isValid(Integer unitsInStock, ConstraintValidatorContext context) {
        if (unitsInStock == null) {
            return false; // null не является допустимым значением
        }

        // Пример использования BigDecimal для проверки
        BigDecimal minValue = new BigDecimal("0"); // Минимальное допустимое значение
        BigDecimal maxValue = new BigDecimal("1000"); // Максимальное допустимое значение

        BigDecimal units = BigDecimal.valueOf(unitsInStock);

        return units.compareTo(minValue) >= 0 && units.compareTo(maxValue) <= 0;
    }
}