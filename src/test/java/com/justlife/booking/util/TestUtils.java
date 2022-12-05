package com.justlife.booking.util;

import com.justlife.booking.constant.Constants;
import com.justlife.booking.constant.TestConstants;
import com.justlife.booking.dto.BookingSaveRequest;
import com.justlife.booking.dto.BookingUpdateRequest;
import com.justlife.booking.model.Booking;
import com.justlife.booking.model.BookingCleaner;
import com.justlife.booking.model.BookingTimePeriod;
import com.justlife.booking.model.Cleaner;
import com.justlife.booking.model.CleanerAvailableTime;
import com.justlife.booking.model.CleanerAvailableTimeImpl;
import com.justlife.booking.model.TimePeriod;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Util class for unit tests to create test objects
 */
public class TestUtils {

    public static Booking createBooking() {
        return Booking.builder().id(TestConstants.BOOKING_ID).customerId(TestConstants.CUSTOMER_ID).build();
    }

    public static List<BookingCleaner> createBookingCleanerListWitSameCleanerId() {
        List<BookingCleaner> bookingCleanerList = new ArrayList<>();
        bookingCleanerList.add(createBookingCleaner());
        bookingCleanerList.add(createBookingCleaner());
        bookingCleanerList.add(createBookingCleaner());
        return bookingCleanerList;
    }

    public static BookingCleaner createBookingCleaner() {
        return BookingCleaner.builder().id(TestConstants.BOOKING_CLEANER_ID).bookingId(TestConstants.BOOKING_ID).cleanerId(TestConstants.CLEANER_ID_LIST.get(0)).build();
    }

    public static BookingSaveRequest createBookingSaveRequest() {
        return BookingSaveRequest.builder()
                .customerId(TestConstants.CUSTOMER_ID)
                .cleanerIds(TestConstants.CLEANER_ID_LIST)
                .startTime(LocalDateTime.parse(TestConstants.START_TIME, TestConstants.DATE_TIME_FORMATTER))
                .duration(TestConstants.DURATION)
                .build();
    }

    public static BookingUpdateRequest createBookingUpdateRequest() {
        return BookingUpdateRequest.builder()
                .startTime(LocalDateTime.parse(TestConstants.START_TIME, TestConstants.DATE_TIME_FORMATTER))
                .duration(TestConstants.DURATION)
                .build();
    }

    public static List<TimePeriod> createTimePeriodList() {
        List<TimePeriod> timePeriodList = new ArrayList<>();
        LocalDateTime startTime = LocalDateTime.parse(TestConstants.START_TIME, TestConstants.DATE_TIME_FORMATTER);
        for (int i = 0; i <= TestConstants.DURATION * (60 / Constants.WORKING_TIME_SLOT); i++) {
            timePeriodList.add(TimePeriod.builder().id(TestConstants.TIME_PERIOD_ID_LIST.get(i)).startTime(startTime).endTime(startTime.plusMinutes(Constants.WORKING_TIME_SLOT)).build());
            startTime = startTime.plusMinutes(Constants.WORKING_TIME_SLOT);
        }
        return timePeriodList;
    }


    public static BookingTimePeriod createBookingTimePeriod() {
        return BookingTimePeriod.builder().bookingId(TestConstants.BOOKING_ID).timePeriodId(TestConstants.TIME_PERIOD_ID_LIST.get(0)).build();
    }

    public static List<BookingTimePeriod> createBookingTimePeriodList() {
        List<BookingTimePeriod> bookingTimePeriodList = new ArrayList<>();
        bookingTimePeriodList.add(BookingTimePeriod.builder().bookingId(TestConstants.BOOKING_ID).timePeriodId(TestConstants.TIME_PERIOD_ID_LIST.get(0)).build());
        bookingTimePeriodList.add(BookingTimePeriod.builder().bookingId(TestConstants.BOOKING_ID).timePeriodId(TestConstants.TIME_PERIOD_ID_LIST.get(1)).build());
        bookingTimePeriodList.add(BookingTimePeriod.builder().bookingId(TestConstants.BOOKING_ID).timePeriodId(TestConstants.TIME_PERIOD_ID_LIST.get(2)).build());

        return bookingTimePeriodList;
    }

    public static List<BookingTimePeriod> createBookingTimePeriodListWithSamePeriodId() {
        List<BookingTimePeriod> bookingTimePeriodList = new ArrayList<>();
        bookingTimePeriodList.add(createBookingTimePeriod());
        bookingTimePeriodList.add(createBookingTimePeriod());
        bookingTimePeriodList.add(createBookingTimePeriod());
        bookingTimePeriodList.add(createBookingTimePeriod());
        bookingTimePeriodList.add(createBookingTimePeriod());

        return bookingTimePeriodList;
    }

    public static List<CleanerAvailableTime> createCleanerAvailableTimeList() {
        List<CleanerAvailableTime> cleanerAvailableTimeList = new ArrayList<>();
        cleanerAvailableTimeList.add(CleanerAvailableTimeImpl.builder()
                .id(TestConstants.CLEANER_AVAILABLE_ID_1)
                .name(TestConstants.CLEANER_ID_1_NAME)
                .surName(TestConstants.CLEANER_ID_1_SURNAME)
                .startTime(LocalDateTime.parse(TestConstants.START_TIME_1, TestConstants.DATE_TIME_FORMATTER))
                .endTime(LocalDateTime.parse(TestConstants.END_TIME_1, TestConstants.DATE_TIME_FORMATTER))
                .build());

        cleanerAvailableTimeList.add(CleanerAvailableTimeImpl.builder()
                .id(TestConstants.CLEANER_AVAILABLE_ID_2)
                .name(TestConstants.CLEANER_ID_2_NAME)
                .surName(TestConstants.CLEANER_ID_2_SURNAME)
                .startTime(LocalDateTime.parse(TestConstants.START_TIME_2, TestConstants.DATE_TIME_FORMATTER))
                .endTime(LocalDateTime.parse(TestConstants.END_TIME_2, TestConstants.DATE_TIME_FORMATTER))
                .build());

        cleanerAvailableTimeList.add(CleanerAvailableTimeImpl.builder()
                .id(TestConstants.CLEANER_AVAILABLE_ID_3)
                .name(TestConstants.CLEANER_ID_3_NAME)
                .surName(TestConstants.CLEANER_ID_3_SURNAME)
                .startTime(LocalDateTime.parse(TestConstants.START_TIME_3, TestConstants.DATE_TIME_FORMATTER))
                .endTime(LocalDateTime.parse(TestConstants.END_TIME_3, TestConstants.DATE_TIME_FORMATTER))
                .build());

        return cleanerAvailableTimeList;
    }

    public static List<Cleaner> createCleanerList() {
        List<Cleaner> cleanerList = new ArrayList<>();
        cleanerList.add(Cleaner.builder()
                .id(TestConstants.CLEANER_ID_1)
                .name(TestConstants.CLEANER_ID_1_NAME)
                .surname(TestConstants.CLEANER_ID_1_SURNAME)
                .build());

        cleanerList.add(Cleaner.builder()
                .id(TestConstants.CLEANER_ID_2)
                .name(TestConstants.CLEANER_ID_2_NAME)
                .surname(TestConstants.CLEANER_ID_2_SURNAME)
                .build());

        cleanerList.add(Cleaner.builder()
                .id(TestConstants.CLEANER_ID_3)
                .name(TestConstants.CLEANER_ID_3_NAME)
                .surname(TestConstants.CLEANER_ID_3_SURNAME)
                .build());

        return cleanerList;
    }

    public static CleanerAvailableTime createCleanerAvailable() {
        return CleanerAvailableTimeImpl.builder()
                .id(TestConstants.CLEANER_AVAILABLE_ID_1)
                .name(TestConstants.CLEANER_ID_1_NAME)
                .surName(TestConstants.CLEANER_ID_1_SURNAME)
                .startTime(LocalDateTime.parse(TestConstants.START_TIME_1, TestConstants.DATE_TIME_FORMATTER))
                .endTime(LocalDateTime.parse(TestConstants.END_TIME_1, TestConstants.DATE_TIME_FORMATTER))
                .build();
    }

    public static LocalDateTime createLocalDateTimeAddDuration() {
        return LocalDateTime.parse(TestConstants.START_TIME, TestConstants.DATE_TIME_FORMATTER).plusHours(TestConstants.DURATION);
    }

}
