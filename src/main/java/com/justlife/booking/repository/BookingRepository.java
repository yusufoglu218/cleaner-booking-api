package com.justlife.booking.repository;

import com.justlife.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Booking
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
