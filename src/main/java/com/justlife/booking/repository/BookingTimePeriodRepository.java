package com.justlife.booking.repository;

import com.justlife.booking.model.BookingTimePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for BookingTimePeriod
 */
@Repository
public interface BookingTimePeriodRepository extends JpaRepository<BookingTimePeriod, Long> {

    /**
     * Get BookingTimePeriod items by bookingId
     * @param bookingId
     * @return list of BookingTimePeriod
     */
    @Query(value = "SELECT \n" +
            "    *\n" +
            "FROM\n" +
            "    cleaner_db.booking_time_period\n" +
            "WHERE\n" +
            "status like 'a'\n" +
            "AND  booking_id = :bookingId",
            nativeQuery = true)
    List<BookingTimePeriod> getBookingTimePeriodByBookingId(@Param("bookingId") Long bookingId);

}
