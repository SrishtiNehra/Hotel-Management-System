package com.hotel.Hotel_Reservation_Management.mapper;

import com.hotel.Hotel_Reservation_Management.dto.BillingDTO;
import com.hotel.Hotel_Reservation_Management.entity.Billing;
import com.hotel.Hotel_Reservation_Management.entity.Reservation;

public class BillingMapper {

    public static Billing toEntity(BillingDTO dto, Reservation reservation) {

        Billing billing = new Billing();

        billing.setBillingId(dto.getBillingId());
        billing.setAmount(dto.getAmount());
        billing.setPaymentDate(dto.getPaymentDate());
        billing.setPaymentStatus(dto.getPaymentStatus());

        billing.setReservation(reservation);

        return billing;
    }

    public static BillingDTO toDTO(Billing billing) {

        BillingDTO dto = new BillingDTO();

        dto.setBillingId(billing.getBillingId());
        dto.setAmount(billing.getAmount());
        dto.setPaymentDate(billing.getPaymentDate());
        dto.setPaymentStatus(billing.getPaymentStatus());

        if (billing.getReservation() != null) {
            dto.setReservationId(billing.getReservation().getReservationId());
        }

        return dto;
    }
}