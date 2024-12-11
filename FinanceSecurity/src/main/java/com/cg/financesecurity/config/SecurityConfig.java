package com.cg.financesecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cg.financesecurity.filter.JwtAuthenticationFilter;

@Configuration

public class SecurityConfig {

	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .requestMatchers("/register", "/login","/profile").permitAll() // Allow registration and login without JWT
                   // .requestMatchers("/users/**").hasRole("ADMIN") // Only admin can access these endpoints
                    .anyRequest().authenticated() // Require JWT authentication for all other endpoints
               // .and()
               // .httpBasic();
//                .exceptionHandling()
//                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)

                    .and()
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add the JWT filter before UsernamePasswordAuthenticationFilter

        return http.build();
    }

   @Bean
   AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
	   return config.getAuthenticationManager();
   }
   
}
