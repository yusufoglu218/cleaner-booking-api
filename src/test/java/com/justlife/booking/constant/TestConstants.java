package com.justlife.booking.constant;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Constant class for test
 */
public class TestConstants {

    private TestConstants() {
    }

    public static final Long BOOKING_ID = 11111l;
    public static final Long CUSTOMER_ID = 22222l;
    public static final Long BOOKING_CLEANER_ID = 5555l;

    public static final List<Long> CLEANER_ID_LIST = Arrays.asList(3331l, 33332l, 3334l);
    public static final String DATE = "2022-12-09 00:00:00";
    public static final String START_TIME = "2022-12-08 10:30:00";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final int DURATION = 2;

    public static final List<Long> TIME_PERIOD_ID_LIST = Arrays.asList(4441l, 4442l, 4443l, 4444L, 4445l);

    public static final Long CLEANER_AVAILABLE_ID_1 = 7771l;
    public static final Long CLEANER_ID_1 = 6661l;
    public static final String CLEANER_ID_1_NAME = "Name1";
    public static final String CLEANER_ID_1_SURNAME = "Surname1";
    public static final String START_TIME_1 = "2022-12-08 10:30:00";
    public static final String END_TIME_1 = "2022-12-08 11:00:00";

    public static final Long CLEANER_AVAILABLE_ID_2 = 7772l;
    public static final Long CLEANER_ID_2 = 6662l;
    public static final String CLEANER_ID_2_NAME = "Name2";
    public static final String CLEANER_ID_2_SURNAME = "Surname2";
    public static final String START_TIME_2 = "2022-12-08 11:30:00";
    public static final String END_TIME_2 = "2022-12-08 12:00:00";

    public static final Long CLEANER_AVAILABLE_ID_3 = 7773l;
    public static final Long CLEANER_ID_3 = 6663l;
    public static final String CLEANER_ID_3_NAME = "Name3";
    public static final String CLEANER_ID_3_SURNAME = "Surname3";
    public static final String START_TIME_3 = "2022-12-08 13:00:00";
    public static final String END_TIME_3 = "2022-12-08 13:30:00";

}
