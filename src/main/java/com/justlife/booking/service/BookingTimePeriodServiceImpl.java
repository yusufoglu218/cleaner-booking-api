package com.justlife.booking.service;

import com.justlife.booking.model.Booking;
import com.justlife.booking.model.BookingTimePeriod;
import com.justlife.booking.model.BookingType;
import com.justlife.booking.model.Status;
import com.justlife.booking.model.TimePeriod;
import com.justlife.booking.repository.BookingTimePeriodRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingTimePeriodServiceImpl implements BookingTimePeriodService {

    private BookingTimePeriodRepository bookingTimePeriodRepository;

    public BookingTimePeriodServiceImpl(BookingTimePeriodRepository bookingTimePeriodRepository) {
        this.bookingTimePeriodRepository = bookingTimePeriodRepository;
    }

    @Override
    public BookingTimePeriod saveBookingTimePeriod(BookingTimePeriod bookingTimePeriod) {
        return bookingTimePeriodRepository.save(bookingTimePeriod);
    }

    @Override
    public List<BookingTimePeriod> getBookingTimePeriodListByBookingId(Long bookingId) {
        return bookingTimePeriodRepository.getBookingTimePeriodByBookingId(bookingId);
    }

    @Override
    public List<BookingTimePeriod> deactivateBookingTimePeriodList(List<BookingTimePeriod> bookingTimePeriodList) {
        List<BookingTimePeriod> bookingTimePeriodsToSave = new ArrayList<>();
        bookingTimePeriodList.forEach(bookingTimePeriod -> bookingTimePeriodsToSave.add(deactivateBookingTimePeriod(bookingTimePeriod)));
        return bookingTimePeriodList;
    }

    @Override
    public BookingTimePeriod deactivateBookingTimePeriod(BookingTimePeriod bookingTimePeriod) {
        bookingTimePeriod.setStatus(Status.d);
        return saveBookingTimePeriod(bookingTimePeriod);
    }

    @Override
    public List<BookingTimePeriod> saveBookingTimePeriodList(List<TimePeriod> reservedTimePeriod, Booking booking, LocalDateTime bookingEndTime) {
        List<BookingTimePeriod> bookingTimePeriodList = new ArrayList<>();
        reservedTimePeriod.forEach(timePeriod -> {
            BookingTimePeriod bookingTimePeriod = BookingTimePeriod.builder().timePeriodId(timePeriod.getId()).bookingId(booking.getId()).status(Status.a).bookingType(BookingType.c).build();

            // set the last record as break type
            if (timePeriod.getStartTime().equals(bookingEndTime)) {
                bookingTimePeriod.setBookingType(BookingType.b);
            }
            bookingTimePeriodList.add(saveBookingTimePeriod(bookingTimePeriod));
        });

        return bookingTimePeriodList;
    }
}
