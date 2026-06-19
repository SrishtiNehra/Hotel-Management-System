package com.hotel.Hotel_Reservation_Management.config;

import com.hotel.Hotel_Reservation_Management.security.JwtFilter;

import jakarta.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            		
            	

                // ✅ PUBLIC PAGES
                .requestMatchers(
                    "/",
                    "/auth/**",
                    "/api/auth/**",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/favicon.ico",
                    "/actuator/**"
                   
                ).permitAll()
                
                .requestMatchers("/dashboard/**").permitAll()
                .requestMatchers("/admin/**").permitAll()
                .requestMatchers("/api/admins/**").permitAll()
                .requestMatchers("/customer/**").permitAll()
                .requestMatchers("/api/customers/**").permitAll()
                

                // ✅ ROLE BASED APIs
                .requestMatchers("/api/admins/**").hasRole("ADMIN")
                .requestMatchers("/api/customers/**").hasRole("CUSTOMER")

                .requestMatchers("/api/dashboard/admin").hasRole("ADMIN")
                .requestMatchers("/api/dashboard/customer/**").hasRole("CUSTOMER")

                // ❌ DO NOT use /** permitAll (REMOVE THIS)
                .anyRequest().authenticated()
            )
            
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")   // ✅ THIS IS THE FIX
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                )
            
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        
        
        http.addFilterBefore(jwtFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ REQUIRED FIX (VERY IMPORTANT)
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}