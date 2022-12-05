package com.justlife.booking.repository;

import com.justlife.booking.model.BookingCleaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for BookingCleaner
 */
@Repository
public interface BookingCleanerRepository extends JpaRepository<BookingCleaner, Long> {


    /**
     * Get BookingCleaner items by bookingId
     * @param bookingId
     * @return list of BookingCleaner
     */
    @Query(value = "SELECT \n" +
            "    *\n" +
            "FROM\n" +
            "    cleaner_db.booking_cleaner\n" +
            "WHERE\n" +
            "status like 'a'\n" +
            "AND  booking_id = :bookingId",
            nativeQuery = true)
    List<BookingCleaner> getBookingBookingCleanerByBookingId(@Param("bookingId") Long bookingId);

}
