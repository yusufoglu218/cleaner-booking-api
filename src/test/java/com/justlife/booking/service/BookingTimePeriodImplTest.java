package com.justlife.booking.service;

import com.justlife.booking.constant.TestConstants;
import com.justlife.booking.model.BookingTimePeriod;
import com.justlife.booking.repository.BookingTimePeriodRepository;
import com.justlife.booking.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Tests for BookingService
 */
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookingTimePeriodImplTest {

    @Mock
    BookingTimePeriodRepository bookingTimePeriodRepository;

    @InjectMocks
    BookingTimePeriodServiceImpl bookingTimePeriodServiceImpl;

    @Test
    public void saveBookingTimePeriod_OK() {
        BookingTimePeriod bookingTimePeriod = TestUtils.createBookingTimePeriod();
        when(bookingTimePeriodRepository.save(bookingTimePeriod)).thenReturn(bookingTimePeriod);
        BookingTimePeriod bookingTimePeriodService = bookingTimePeriodServiceImpl.saveBookingTimePeriod(bookingTimePeriod);

        Assertions.assertEquals(bookingTimePeriodService, bookingTimePeriod);
    }

    @Test
    public void getBookingTimePeriodListByBookingId_OK() {
        List<BookingTimePeriod> bookingTimePeriodList = TestUtils.createBookingTimePeriodList();
        when(bookingTimePeriodRepository.getBookingTimePeriodByBookingId(TestConstants.BOOKING_ID)).thenReturn(bookingTimePeriodList);
        List<BookingTimePeriod> bookingTimePeriodFromService = bookingTimePeriodServiceImpl.getBookingTimePeriodListByBookingId(TestConstants.BOOKING_ID);

        Assertions.assertEquals(bookingTimePeriodList, bookingTimePeriodFromService);
    }

    @Test
    public void deactivateBookingTimePeriodList_OK() {
        List<BookingTimePeriod> bookingTimePeriodList = TestUtils.createBookingTimePeriodList();
        BookingTimePeriod bookingTimePeriod = TestUtils.createBookingTimePeriod();
        when(bookingTimePeriodRepository.save(Mockito.any())).thenReturn(bookingTimePeriod);
        List<BookingTimePeriod> bookingTimePeriodFromService = bookingTimePeriodServiceImpl.deactivateBookingTimePeriodList(bookingTimePeriodList);

        Assertions.assertEquals(bookingTimePeriodList, bookingTimePeriodFromService);
    }

    @Test
    public void deactivateBookingTimePeriod_OK() {
        BookingTimePeriod bookingTimePeriod = TestUtils.createBookingTimePeriod();
        when(bookingTimePeriodRepository.save(Mockito.any())).thenReturn(bookingTimePeriod);
        BookingTimePeriod bookingTimePeriodFromService = bookingTimePeriodServiceImpl.deactivateBookingTimePeriod(bookingTimePeriod);

        Assertions.assertEquals(bookingTimePeriodFromService, bookingTimePeriod);
    }

    @Test
    public void saveBookingTimePeriodList_OK() {
        List<BookingTimePeriod> bookingTimePeriodList = TestUtils.createBookingTimePeriodListWithSamePeriodId();
        BookingTimePeriod bookingTimePeriod = TestUtils.createBookingTimePeriod();
        when(bookingTimePeriodRepository.save(Mockito.any())).thenReturn(bookingTimePeriod);
        List<BookingTimePeriod> bookingTimePeriodListFromService = bookingTimePeriodServiceImpl.saveBookingTimePeriodList(TestUtils.createTimePeriodList(), TestUtils.createBooking(), TestUtils.createLocalDateTimeAddDuration());

        Assertions.assertEquals(bookingTimePeriodListFromService, bookingTimePeriodList);
    }




}
