package com.niit.config;

import com.niit.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.Filter;

@Configuration
@CrossOrigin(origins = "http://localhost:4200")
public class APIConfiguration {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p
                        .path("/api/v1/**")
                        .uri("http://localhost:8081/"))
                .route(p->p
                        .path("/api/v2/**")
                        .uri("http://localhost:8082/"))
                .route(p->p
                        .path("/api/v3/**")
                        .uri("http://localhost:8083/"))
                .route(p->p
                        .path("/api/v4/**")
                        .uri("http://localhost:8086/"))
                .build();
    }

    @Bean
    public FilterRegistrationBean jwtFilterBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter((Filter) new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/api/v2/**","/api/v3/**");
        return filterRegistrationBean;
    }
}
