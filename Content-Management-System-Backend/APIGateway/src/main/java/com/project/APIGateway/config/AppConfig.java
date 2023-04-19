package com.project.APIGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration

public class AppConfig {
    @CrossOrigin("*")
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p
                        .path("/userdata/**")
                        .uri("http://localhost:8085/")
                ).route(p->p
                        .path("/contentdata/**")
                        .uri("http://localhost:8080/")
                ).build();
    }
}
