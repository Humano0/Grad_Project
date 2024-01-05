package com.final_project.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.AuthorizeHttpRequestsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

/* @Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Override
    protected void configure (HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
            .antMatchers("/api/v1/user/**").permitAll()
            .anyRequest().authenticated();
    }


} */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
             csrf()
             .disable()
             .authorizeRequests()
             .requestMatchers("/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        return http.build();


    }
  
}
