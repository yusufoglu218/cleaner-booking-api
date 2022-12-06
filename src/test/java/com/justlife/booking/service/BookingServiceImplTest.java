package com.justlife.booking.service;

import com.justlife.booking.constant.TestConstants;
import com.justlife.booking.dto.BookingSaveRequest;
import com.justlife.booking.dto.BookingUpdateRequest;
import com.justlife.booking.dto.GetBookingResponse;
import com.justlife.booking.exception.ErrorType;
import com.justlife.booking.exception.RecordNotFoundException;
import com.justlife.booking.mapper.BookingMapper;
import com.justlife.booking.model.Booking;
import com.justlife.booking.model.BookingCleaner;
import com.justlife.booking.repository.BookingCleanerRepository;
import com.justlife.booking.repository.BookingRepository;
import com.justlife.booking.repository.TimePeriodRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Tests for BookingService
 */
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookingServiceImplTest {

    @Mock
    BookingMapper bookingMapper;

    @Mock
    BookingTimePeriodServiceImpl bookingTimePeriodService;

    @Mock
    BookingRepository bookingRepository;

    @Mock
    TimePeriodRepository timePeriodRepository;

    @Mock
    BookingCleanerRepository bookingCleanerRepository;

    @InjectMocks
    BookingServiceImpl bookingServiceImpl;


    @Test
    public void saveBooking_OK() {
        BookingSaveRequest bookingSaveRequest = TestUtils.createBookingSaveRequest();
        Booking bookingTest = TestUtils.createBooking();

        when(bookingMapper.bookingSaveRequestToBooking(bookingSaveRequest)).thenReturn(bookingTest);
        when(bookingRepository.save(Mockito.any())).thenReturn(bookingTest);
        when(timePeriodRepository.getTimePeriodBetweenStartEndTime(Mockito.any(), Mockito.any())).thenReturn(TestUtils.createTimePeriodList());
        when(bookingCleanerRepository.save(Mockito.any())).thenReturn(TestUtils.createBookingCleaner());

        Booking bookingFromService = bookingServiceImpl.saveBooking(bookingSaveRequest);

        Assertions.assertEquals(bookingTest, bookingFromService);
    }

    @Test
    public void updateBooking_OK() {
        BookingUpdateRequest bookingUpdateRequest = TestUtils.createBookingUpdateRequest();
        Booking bookingTest = TestUtils.createBooking();

        when(bookingTimePeriodService.getBookingTimePeriodListByBookingId(TestConstants.BOOKING_ID)).thenReturn(List.of(TestUtils.createBookingTimePeriod()));
        when(bookingRepository.findById(TestConstants.BOOKING_ID)).thenReturn(java.util.Optional.ofNullable(bookingTest));
        when(timePeriodRepository.getTimePeriodBetweenStartEndTime(bookingUpdateRequest.getStartTime(), bookingUpdateRequest.getStartTime().plusHours(bookingUpdateRequest.getDuration()))).thenReturn(TestUtils.createTimePeriodList());
        when(bookingTimePeriodService.deactivateBookingTimePeriodList(List.of(TestUtils.createBookingTimePeriod()))).thenReturn(List.of(TestUtils.createBookingTimePeriod()));

        Booking bookingFromService = bookingServiceImpl.updateBooking(TestConstants.BOOKING_ID, bookingUpdateRequest);

        Assertions.assertEquals(bookingTest, bookingFromService);
    }

    @Test
    public void getBookingDetailById_OK() {
        GetBookingResponse getBookingResponse = TestUtils.createGetBookingResponse();
        Booking bookingTest = TestUtils.createBooking();

        when(bookingRepository.findById(TestConstants.BOOKING_ID)).thenReturn(java.util.Optional.ofNullable(bookingTest));
        when(bookingTimePeriodService.getBookingTimePeriodListByBookingId(TestConstants.BOOKING_ID)).thenReturn(TestUtils.createBookingTimePeriodListWithSamePeriodId());
        when(timePeriodRepository.findById(TestConstants.TIME_PERIOD_ID_LIST.get(0))).thenReturn(java.util.Optional.ofNullable(TestUtils.createTimePeriod()));
        when(bookingMapper.bookingToGetBookingResponse(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(getBookingResponse);
        when(bookingCleanerRepository.getBookingBookingCleanerByBookingId(Mockito.any())).thenReturn(TestUtils.createBookingCleanerList());

        GetBookingResponse getBookingResponseFromService = bookingServiceImpl.getBookingDetailById(TestConstants.BOOKING_ID);

        Assertions.assertEquals(getBookingResponse, getBookingResponseFromService);
    }

    @Test
    public void getBookingById_NOT_FOUND() {
        when(bookingRepository.findById(TestConstants.BOOKING_ID)).thenThrow(new RecordNotFoundException(ErrorType.BOOKING_NOT_FOUND + TestConstants.BOOKING_ID.toString()));
        RecordNotFoundException thrown = Assertions.assertThrows(RecordNotFoundException.class, () -> {
            bookingServiceImpl.getBookingDetailById(TestConstants.BOOKING_ID);
        });

        assertEquals(ErrorType.BOOKING_NOT_FOUND + TestConstants.BOOKING_ID.toString(), thrown.getMessage());
    }

    @Test
    public void saveBookingCleaner_OK() {
        List <BookingCleaner> bookingCleanerList = TestUtils.createBookingCleanerListWitSameCleanerId();
        BookingCleaner bookingCleaner = TestUtils.createBookingCleaner();
        when(bookingCleanerRepository.save(Mockito.any())).thenReturn(bookingCleaner);
        List<BookingCleaner> bookingCleanerListFromService = bookingServiceImpl.saveBookingCleanerListByCleanerList(TestConstants.BOOKING_ID, TestConstants.CLEANER_ID_LIST);

        Assertions.assertEquals(bookingCleanerList, bookingCleanerListFromService);

    }


}
