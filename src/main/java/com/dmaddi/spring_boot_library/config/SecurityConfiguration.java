package com.dmaddi.spring_boot_library.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Disable Cross Site Request Forgery

        http.csrf().disable();

        // Add cors filter
        http.cors();

        // Protect endpoints at api/<type>/secure
        http.authorizeHttpRequests(configurer ->
                        configurer.requestMatchers("/api/books/secure/**")
                                .authenticated()
                                .requestMatchers("/api/books/**", "/api/reviews/**")
                                .permitAll())
                .oauth2ResourceServer().jwt();


        // Add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

        // Force a non-empty response body to make unauthorized response body more friendly.
        Okta.configureResourceServer401ResponseBody(http);
        return http.build();

    }
}
