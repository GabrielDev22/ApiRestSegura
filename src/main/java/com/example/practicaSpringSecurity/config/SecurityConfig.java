package com.example.practicaSpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SecurityConfig {

    /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeHttpRequests()
                    .requestMatchers("/test/pruebaTwo").permitAll()
                    .requestMatchers("/test/pruebaOne").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .httpBasic()
                .and()
                .build();
    } */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf().disable()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/test/pruebaTwo").permitAll();
                    auth.requestMatchers("/test/create").permitAll();
                    auth.requestMatchers("/test/update").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin()
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/login")
                .maximumSessions(1)
                .expiredUrl("/login")
                .sessionRegistry(sessionRegistry())
                .and()
                .sessionFixation()
                .migrateSession()
                .and()
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public SessionRegistry sessionRegistry() { return new SessionRegistryImpl();}

    public AuthenticationSuccessHandler successHandler(){
        return ((request, response, authentication) -> {
            response.sendRedirect("/test/session");
        });
    }

}
