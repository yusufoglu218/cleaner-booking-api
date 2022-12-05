package com.justlife.booking.service;

import com.justlife.booking.constant.Constants;
import com.justlife.booking.repository.CleanerRepository;
import com.justlife.booking.util.BookingUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of CleanerService
 */
@Service
public class CleanerServiceImpl implements CleanerService {

    private CleanerRepository cleanerRepository;

    public CleanerServiceImpl(CleanerRepository cleanerRepository) {
        this.cleanerRepository = cleanerRepository;
    }

    /**
     * Get available cleaner list.
     * Returns list of cleaner with available times if only date parameter is given.
     * If other parameters are also given then it returns only cleaner list
     * @param date date for the cleaner availability
     * @param startTime starTime of availability
     * @param numberOfCleaner number of cleaner available together
     * @param duration duration of availability from startTime
     * @return list of cleaner
     */
    @Override
    public List getAvailableCleanerList(LocalDateTime date, LocalDateTime startTime, Integer numberOfCleaner, Integer duration) {

        // if only date is given
        if (startTime == null && numberOfCleaner == null && duration == null) {
            return cleanerRepository.findAvailableCleanerListByDate(date);
        }

        Integer numberOfInterval = duration * 60 / Constants.WORKING_TIME_SLOT;
        List<LocalDateTime> localDateTimeList = BookingUtils.createListOfDateTimeByInterval(startTime, duration);

        // if multiple cleaner is requested then call related operation
        if (numberOfCleaner > 1) {
            return cleanerRepository.findAvailableMultipleCleaner(date, localDateTimeList, numberOfCleaner, numberOfInterval);
        }

        return cleanerRepository.findAvailableCleanerListByTimeDuration(date, localDateTimeList, numberOfInterval);
    }


}
