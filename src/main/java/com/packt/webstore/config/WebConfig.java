package com.packt.webstore.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packt.webstore.component.CustomPerformanceMonitorInterceptor;
import com.packt.webstore.component.PerformanceMonitorInterceptor;
import com.packt.webstore.domain.Product;
import com.packt.webstore.interceptor.AuditingInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.packt.webstore")
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        registry.addResourceHandler("/resourcesImg/**")
                .addResourceLocations("/resourcesImg/");
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

//    @Bean
//    public CustomPerformanceMonitorInterceptor performanceMonitorInterceptor() {
//        return new CustomPerformanceMonitorInterceptor();
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
//        interceptor.setParamName("language");
//        return interceptor;
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor((HandlerInterceptor) performanceMonitorInterceptor());
//        registry.addInterceptor(localeChangeInterceptor());
//    }


    @Bean
    public PerformanceMonitorInterceptor performanceMonitorInterceptor(){
        return  new PerformanceMonitorInterceptor();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Bean
    public AuditingInterceptor auditingInterceptor() {
        return new AuditingInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(performanceMonitorInterceptor());
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(auditingInterceptor());
    }

//    public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
//        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
//
//        List<View> defaultViews = new ArrayList<>();
//        defaultViews.add(jsonView());
//        defaultViews.add(xmlView());
//
//        viewResolver.setDefaultViews(Collections.unmodifiableList(defaultViews));
//        return viewResolver;
//    }
//
//    public MappingJackson2JsonView jsonView() {
//        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
//        jsonView.setPrettyPrint(true);
//        return jsonView;
//    }
//
//    public MarshallingView xmlView() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        marshaller.setClassesToBeBound(Product.class);
//
//        return new MarshallingView(marshaller);
//    }
}
