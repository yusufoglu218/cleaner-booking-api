package com.justlife.booking.service;

import com.justlife.booking.dto.BookingSaveRequest;
import com.justlife.booking.dto.BookingUpdateRequest;
import com.justlife.booking.model.Booking;
import com.justlife.booking.model.BookingCleaner;

import java.util.List;

/**
 * Interface for Booking services
 */
public interface BookingService {

    /**
     * Save booking
     * @param bookingRequest dto object to save booking
     * @return saved booking object
     */
    Booking saveBooking(BookingSaveRequest bookingRequest);

    /**
     * Update booking. If the booking does not exists with the id then not found exception is thrown.
     * @param id booking id to update
     * @param bookingUpdateRequest dto object to update booking
     * @return updated booking object
     */
    Booking updateBooking(Long id, BookingUpdateRequest bookingUpdateRequest);

    /**
     * Get booking by id. If the booking does not exists with the id then not found exception is thrown.
     * @param id booking id to find
     * @return found booking object
     */
    Booking getBookingById(Long id);

    /**
     * Create and save BookingCleaner list by parameters
     * @param bookingId booking id to save
     * @param cleanerIdList list of ids to save
     * @return saved list of BookingCleaner
     */
    List<BookingCleaner> saveBookingCleanerListByCleanerList(Long bookingId, List<Long> cleanerIdList);
}
