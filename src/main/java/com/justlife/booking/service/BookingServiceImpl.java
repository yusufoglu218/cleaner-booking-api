package com.justlife.booking.service;

import com.justlife.booking.dto.BookingSaveRequest;
import com.justlife.booking.dto.BookingUpdateRequest;
import com.justlife.booking.exception.ErrorType;
import com.justlife.booking.exception.RecordNotFoundException;
import com.justlife.booking.mapper.BookingMapper;
import com.justlife.booking.model.Booking;
import com.justlife.booking.model.BookingCleaner;
import com.justlife.booking.model.BookingTimePeriod;
import com.justlife.booking.model.TimePeriod;
import com.justlife.booking.repository.BookingCleanerRepository;
import com.justlife.booking.repository.BookingRepository;
import com.justlife.booking.repository.TimePeriodRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of BookingService
 */
@Service
public class BookingServiceImpl implements BookingService {

    private BookingMapper bookingMapper;
    private BookingTimePeriodService bookingTimePeriodService;
    private BookingRepository bookingRepository;
    private TimePeriodRepository timePeriodRepository;
    private BookingCleanerRepository bookingCleanerRepository;

    public BookingServiceImpl(BookingMapper bookingMapper, BookingRepository bookingRepository, BookingTimePeriodServiceImpl bookingTimePeriodService, TimePeriodRepository timePeriodRepository, BookingCleanerRepository bookingCleanerRepository) {
        this.bookingMapper = bookingMapper;
        this.bookingRepository = bookingRepository;
        this.bookingTimePeriodService = bookingTimePeriodService;
        this.timePeriodRepository = timePeriodRepository;
        this.bookingCleanerRepository = bookingCleanerRepository;
    }

    @Override
    public Booking saveBooking(BookingSaveRequest bookingRequest) {
        Booking booking = bookingMapper.bookingSaveRequestToBooking(bookingRequest);
        Booking savedBooking = bookingRepository.save(booking);

        // find timePeriod list by startTime and endTime to save
        LocalDateTime bookingEndTime = bookingRequest.getStartTime().plusHours(bookingRequest.getDuration());
        List<TimePeriod> reservedTimePeriod = timePeriodRepository.getTimePeriodBetweenStartEndTime(bookingRequest.getStartTime(), bookingEndTime);

        bookingTimePeriodService.saveBookingTimePeriodList(reservedTimePeriod, savedBooking, bookingEndTime);
        saveBookingCleanerListByCleanerList(savedBooking.getId(), bookingRequest.getCleanerIds());

        return savedBooking;
    }

    @Override
    public Booking updateBooking(Long id, BookingUpdateRequest bookingUpdateRequest) {
        Booking bookingCurrentFromDb = getBookingById(id);

        // deactivate current BookingTimePeriod records
        List<BookingTimePeriod> bookingTimePeriodsToDeactivate = bookingTimePeriodService.getBookingTimePeriodListByBookingId(id);
        bookingTimePeriodService.deactivateBookingTimePeriodList(bookingTimePeriodsToDeactivate);

        // find timePeriod list by startTime and endTime to save
        LocalDateTime bookingEndTime = bookingUpdateRequest.getStartTime().plusHours(bookingUpdateRequest.getDuration());
        List<TimePeriod> reservedTimePeriod = timePeriodRepository.getTimePeriodBetweenStartEndTime(bookingUpdateRequest.getStartTime(), bookingEndTime);

        bookingTimePeriodService.saveBookingTimePeriodList(reservedTimePeriod, bookingCurrentFromDb, bookingEndTime);

        return bookingCurrentFromDb;
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(ErrorType.BOOKING_NOT_FOUND + id.toString()));
    }

    @Override
    public List<BookingCleaner> saveBookingCleanerListByCleanerList(Long bookingId, List<Long> cleanerIdList) {
        List<BookingCleaner> bookingCleanerList = new ArrayList<>();
        cleanerIdList.forEach(cleanerId -> {
            BookingCleaner bookingCleaner = BookingCleaner.builder().cleanerId(cleanerId).bookingId(bookingId).build();
            bookingCleanerList.add(bookingCleanerRepository.save(bookingCleaner));
        });

        return bookingCleanerList;
    }
}
