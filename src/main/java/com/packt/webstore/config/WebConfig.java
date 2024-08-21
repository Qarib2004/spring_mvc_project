package com.packt.webstore.config;

import com.packt.webstore.interceptor.PerformanceMonitorInterceptor;
import com.packt.webstore.interceptor.AuditingInterceptor;
import com.packt.webstore.interceptor.PromoCodeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.webflow.config.FlowBuilderServicesBuilder;
import org.springframework.webflow.config.FlowDefinitionRegistryBuilder;
import org.springframework.webflow.config.FlowExecutorBuilder;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.packt.webstore")
@Import(WebFlowConfig.class)
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public PerformanceMonitorInterceptor performanceMonitorInterceptor() {
        return new PerformanceMonitorInterceptor();
    }

    @Bean
    public AuditingInterceptor auditingInterceptor() {
        return new AuditingInterceptor();
    }

    @Bean
    public PromoCodeInterceptor promoCodeInterceptor() {
        return new PromoCodeInterceptor();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
//        resolver.setPrefix("/WEB-INF/flows/checkout/");
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

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }



    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles/definitions/tile-definition.xml" });
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }

    @Bean
    public TilesViewResolver tilesViewResolver() {
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        tilesViewResolver.setViewClass(org.springframework.web.servlet.view.tiles3.TilesView.class);
        //tilesViewResolver.setOrder(-2); // Ensure TilesViewResolver has a higher precedence than default
        return tilesViewResolver;
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(performanceMonitorInterceptor());
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(auditingInterceptor());
        registry.addInterceptor(promoCodeInterceptor())
                .addPathPatterns("/**");
    }
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

