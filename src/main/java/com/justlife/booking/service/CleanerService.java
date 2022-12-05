package com.justlife.booking.service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface for cleaner service
 */
public interface CleanerService {

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
    List getAvailableCleanerList(LocalDateTime date, LocalDateTime startTime, Integer numberOfCleaner, Integer duration);
}
