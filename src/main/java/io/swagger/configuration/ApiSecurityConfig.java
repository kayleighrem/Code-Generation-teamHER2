//package io.swagger.configuration;
//
//import io.swagger.api.Repositories.ApiKeyRepository;
//import io.swagger.security.APIKeyAuthFilter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@Order(2)
//public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
//    private ApiKeyRepository apiKeyRepository;
//
//    public ApiSecurityConfig(ApiKeyRepository apiKeyRepository) {
//        this.apiKeyRepository = apiKeyRepository;
//    }
//
//    @Value("X-AUTHTOKEN")
//    private String headerName;
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        APIKeyAuthFilter filter = new APIKeyAuthFilter(headerName);
//        filter.setAuthenticationManager(authentication -> {
//            String principal = (String) authentication.getPrincipal();
//            if (apiKeyRepository.findById(principal) == null) {
//                throw new BadCredentialsException("API Key was not found on the system");
//            }
//            authentication.setAuthenticated(true);
//            return authentication;
//        });
//    }
//}