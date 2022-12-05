package com.justlife.booking.model;

import java.time.LocalDateTime;

/**
 * Class for cleaner with available time
 */
public interface CleanerAvailableTime {
    Long getId();

    String getName();

    String getSurName();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();
}
