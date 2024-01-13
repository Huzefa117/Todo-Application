package com.todoApp.rest.webservices.restfulwebservices.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthenticationSecurityConfiguration {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //1. All requests should be authenticated
    http
            .authorizeHttpRequests(
                    auth ->
                            auth
                                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                                    .requestMatchers("/console/**").permitAll()
                                    .anyRequest().authenticated());
    //2. If a request is not authenticated, a web page is shown
    http     .httpBasic(Customizer.withDefaults());
    //To disable csrf it is advised to have session creation policy as stateless
    http        .sessionManagement(
            session -> session.sessionCreationPolicy
                    (SessionCreationPolicy.STATELESS));
    //3. CSRF -> POST, PUT
    http    .headers().frameOptions().disable();
    http .csrf().disable();
    return http.build();
  }
}
