package com.cg.financesecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true)
@EnableDiscoveryClient
public class FinanceSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceSecurityApplication.class, args);
	}

}
