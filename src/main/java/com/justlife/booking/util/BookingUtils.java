package com.justlife.booking.util;

import com.justlife.booking.constant.Constants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Util class for booking
 */
public class BookingUtils {

    /**
     * Find list of LocalDateTime by startTime and duration
     * @param startTime
     * @param durationHour
     * @return list of LocalDateTime
     */
    public static List<LocalDateTime> createListOfDateTimeByInterval(LocalDateTime startTime, Integer durationHour) {
        List<LocalDateTime> localDateTimeList = new ArrayList<>();
        for (int i = 0; i < durationHour * (60 / Constants.WORKING_TIME_SLOT); i++) {
            localDateTimeList.add(startTime);
            startTime = startTime.plusMinutes(Constants.WORKING_TIME_SLOT);
        }
        return localDateTimeList;
    }

}

