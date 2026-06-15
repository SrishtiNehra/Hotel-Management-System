package com.hotel.Hotel_Reservation_Management.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        String role = authentication.getAuthorities().toString();

        if (role.contains("ADMIN")) {
            response.sendRedirect("/api/dashboard/admin");
        } else {
            response.sendRedirect("/api/dashboard/customer");
        }
    }
}