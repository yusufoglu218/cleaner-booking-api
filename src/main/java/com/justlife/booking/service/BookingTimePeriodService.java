package com.justlife.booking.service;

import com.justlife.booking.model.Booking;
import com.justlife.booking.model.BookingTimePeriod;
import com.justlife.booking.model.TimePeriod;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface for BookingTimePeriod services
 */
public interface BookingTimePeriodService {

    /**
     * Get BookingTimePeriod list by bookingId
     * @param bookingId
     * @return list of BookingTimePeriod
     */
    List<BookingTimePeriod> getBookingTimePeriodListByBookingId(Long bookingId);

    /**
     * Save BookingTimePeriod
     * @param bookingTimePeriod
     * @return BookingTimePeriod
     */
    BookingTimePeriod saveBookingTimePeriod(BookingTimePeriod bookingTimePeriod);

    /**
     * Deactivate list of BookingTimePeriod by setting status
     * @param bookingTimePeriodList
     * @return list of BookingTimePeriod
     */
    List<BookingTimePeriod> deactivateBookingTimePeriodList(List<BookingTimePeriod> bookingTimePeriodList);

    /**
     * Deactivate BookingTimePeriod by setting status
     * @param bookingTimePeriod
     * @return BookingTimePeriod
     */
    BookingTimePeriod deactivateBookingTimePeriod(BookingTimePeriod bookingTimePeriod);

    /**
     * Create and save list of BookingTimePeriod by parameters
     * @param timePeriodList timePeriod list to save
     * @param booking booking to save bookingTimePeriod
     * @param bookingEndTime endTime to save bookingTimePeriod with break type
     * @return list of BookingTimePeriod
     */
    List<BookingTimePeriod> saveBookingTimePeriodList(List<TimePeriod> timePeriodList, Booking booking, LocalDateTime bookingEndTime);
}
