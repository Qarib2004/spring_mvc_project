package com.packt.webstore.validator;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UnitsInStockValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnitsInStock {
    String message() default "Invalid units in stock"; // Сообщение об ошибке
    Class<?>[] groups() default {}; // Группы валидации
    Class<? extends Payload>[] payload() default {}; // Дополнительные данные
}
