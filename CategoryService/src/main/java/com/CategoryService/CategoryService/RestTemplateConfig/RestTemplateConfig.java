package com.CategoryService.CategoryService.RestTemplateConfig;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	
	@Bean
    @LoadBalanced  // Enables service discovery using Eureka
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
