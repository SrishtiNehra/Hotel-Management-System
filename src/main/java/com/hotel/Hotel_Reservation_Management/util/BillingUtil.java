package com.hotel.Hotel_Reservation_Management.util;

import java.math.BigDecimal;

public class BillingUtil {

    // calculate total bill
    public static BigDecimal calculateAmount(BigDecimal pricePerDay, long days) {
        return pricePerDay.multiply(BigDecimal.valueOf(days));
    }

    // apply discount
    public static BigDecimal applyDiscount(BigDecimal amount, double percent) {
        BigDecimal discount = amount.multiply(BigDecimal.valueOf(percent / 100));
        return amount.subtract(discount);
    }

    // tax calculation (example 18% GST)
    public static BigDecimal applyGST(BigDecimal amount) {
        BigDecimal tax = amount.multiply(BigDecimal.valueOf(0.18));
        return amount.add(tax);
    }
}