//package com.cg.financesecurity.config;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FeignConfig {
//
//    private final JwtTokenFetcher jwtTokenFetcher;
//
//    // Inject your service or utility that fetches the JWT token
//    public FeignConfig(JwtTokenFetcher jwtTokenFetcher) {
//        this.jwtTokenFetcher = jwtTokenFetcher;
//    }
//
//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return new RequestInterceptor() {
//            @Override
//            public void apply(RequestTemplate requestTemplate) {
//                // Fetch the JWT token dynamically
//                String token = jwtTokenFetcher.fetchJwtToken();
//                
//                if (token != null) {
//                    // Set the Authorization header with Bearer token
//                    requestTemplate.header("Authorization", "Bearer " + token);
//                }
//            }
//        };
//    }
//}
