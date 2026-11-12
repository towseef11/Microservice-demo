package com.productservice.demo.Config;

import com.productservice.demo.Security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

  private JwtAuthenticationFilter jwtAuthenticationFilter;

  public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }

  @Bean
  public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            auth ->
                auth
                    // every get method from controller can be accessed to every authenticated user
                    .requestMatchers(HttpMethod.GET, "/Product/**")
                    .authenticated()

                    // post mwthod of product controller should be accessed by only hotel members
                    .requestMatchers(HttpMethod.POST, "/Product/**")
                    .hasRole("HOTEL_MEMBER")

                    // patch and delete also should be accessed by hotel members only
                    .requestMatchers(HttpMethod.PATCH, "/Product/**")
                    .hasRole("HOTEL_MEMBER")
                    .requestMatchers(HttpMethod.DELETE, "/Product/**")
                    .hasRole("HOTEL_MEMBER")
                    .anyRequest()
                    .authenticated())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
