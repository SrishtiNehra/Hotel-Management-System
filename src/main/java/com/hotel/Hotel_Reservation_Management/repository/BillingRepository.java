package com.hotel.Hotel_Reservation_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotel.Hotel_Reservation_Management.entity.Billing;
import com.hotel.Hotel_Reservation_Management.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BillingRepository extends JpaRepository<Billing, Long> {

	@Query("""
		    SELECT b
		    FROM Billing b
		    WHERE b.reservation.customer.customerId = :customerId
		""")
		List<Billing> findByCustomerId(@Param("customerId") Long customerId);
	
	@Query("""
		    SELECT COALESCE(SUM(b.amount), 0)
		    FROM Billing b
		    WHERE b.reservation.customer.customerId = :customerId
		""")
		BigDecimal sumByCustomerId(@Param("customerId") Long customerId);
	
	@Query("""
		    SELECT COALESCE(SUM(b.amount), 0)
		    FROM Billing b
		    WHERE b.reservation.customer.customerId = :customerId
		    AND LOWER(b.paymentStatus) = 'PENDING'
		""")
		BigDecimal sumPendingByCustomerId(@Param("customerId") Long customerId);
	
	
	
	@Query("""
		    SELECT COALESCE(SUM(b.amount), 0)
		    FROM Billing b
		""")
		BigDecimal sumAllRevenue();
	
	 Optional<Billing> findByReservation_ReservationId(Long reservationId);

	    List<Billing> findByReservation_Customer_CustomerId(Long customerId);
	    
	    
}