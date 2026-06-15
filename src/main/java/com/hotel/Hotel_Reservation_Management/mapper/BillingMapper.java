package com.hotel.Hotel_Reservation_Management.mapper;

import com.hotel.Hotel_Reservation_Management.dto.BillingDto;
import com.hotel.Hotel_Reservation_Management.entity.Billing;
import com.hotel.Hotel_Reservation_Management.entity.Reservation;

public class BillingMapper {

    public static BillingDto toDto(Billing billing) {
        if (billing == null) return null;

        BillingDto dto = new BillingDto();
        dto.setBillingId(billing.getBillingId());
        dto.setAmount(billing.getAmount());
        dto.setPaymentDate(billing.getPaymentDate());
        dto.setPaymentStatus(billing.getPaymentStatus());

        if (billing.getReservation() != null)
            dto.setReservationId(billing.getReservation().getReservationId());

        return dto;
    }

    public static Billing toEntity(BillingDto dto, Reservation reservation) {
        if (dto == null) return null;

        Billing billing = new Billing();
        billing.setBillingId(dto.getBillingId());
        billing.setAmount(dto.getAmount());
        billing.setPaymentDate(dto.getPaymentDate());
        billing.setPaymentStatus(dto.getPaymentStatus());
        billing.setReservation(reservation);

        return billing;
    }
}