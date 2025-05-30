package com.budgetService.BudgetService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class BudgetServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetServiceApplication.class, args);
	}

}
