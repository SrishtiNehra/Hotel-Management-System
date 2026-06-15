//package com.hotel.Hotel_Reservation_Management.config;
//
//import com.hotel.Hotel_Reservation_Management.security.CustomUserDetailsService;
//import com.hotel.Hotel_Reservation_Management.security.LoginSuccessHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.*;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//
//    @Autowired
//    private LoginSuccessHandler successHandler;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//
//                // public APIs
//                .requestMatchers("/api/auth/**").permitAll()
//
//                // admin only
//                .requestMatchers("/api/admin/**").hasRole("ADMIN")
//
//                // customer + admin access
//                .requestMatchers("/api/customers/**").hasAnyRole("ADMIN", "CUSTOMER")
//
//                // dashboard
//                .requestMatchers("/api/dashboard/admin").hasRole("ADMIN")
//                .requestMatchers("/api/dashboard/customer/**").hasRole("CUSTOMER")
//
//                .anyRequest().authenticated()
//            )
//            .formLogin(form -> form
//                .successHandler(successHandler)
//                .permitAll()
//            )
//            .logout(logout -> logout.permitAll());
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//}



package com.hotel.Hotel_Reservation_Management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .anyRequest().permitAll()   // TEMP TEST ONLY
	        )
	        .formLogin(form -> form.disable())
	        .httpBasic(httpBasic -> httpBasic.disable());

	    return http.build();
	}

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}