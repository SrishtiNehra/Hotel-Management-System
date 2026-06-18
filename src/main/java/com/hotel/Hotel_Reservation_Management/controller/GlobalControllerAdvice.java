package com.hotel.Hotel_Reservation_Management.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("loggedInUser")
    public String loggedInUser(HttpSession session) {
        return (String) session.getAttribute("username");
    }

    @ModelAttribute("role")
    public String role(HttpSession session) {
        return (String) session.getAttribute("role");
    }
}