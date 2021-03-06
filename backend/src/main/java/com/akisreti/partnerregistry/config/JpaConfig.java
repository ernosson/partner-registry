package com.akisreti.partnerregistry.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;

/**
 * JpaConfiguration.
 *
 * @author kisretia
 */
@Configuration
@EnableTransactionManagement
public class JpaConfig implements WebMvcConfigurer {


    @Override
    public void addArgumentResolvers( List<HandlerMethodArgumentResolver> argumentResolvers ) {
        argumentResolvers.add(new SpecificationArgumentResolver());
    }

}
