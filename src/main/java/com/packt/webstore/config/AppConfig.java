package com.packt.webstore.config;

import com.packt.webstore.validator.ProductValidator;
import com.packt.webstore.validator.UnitsInStockValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.Set;


@Configuration
public class AppConfig {

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10240000); // 10 MB
        return multipartResolver;
    }

        @Bean
        public UnitsInStockValidator unitsInStockValidator() {
            return new UnitsInStockValidator();
        }

    @Bean
    public ProductValidator productValidator() {
        ProductValidator productValidator = new ProductValidator();
        productValidator.setSpringValidators(Set.of(unitsInStockValidator()));
        return productValidator;
    }


}
