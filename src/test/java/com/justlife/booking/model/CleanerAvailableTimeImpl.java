package com.justlife.booking.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Implementation of CleanerAvailableTime to be able to initialize
 */
@Data
@Builder
public class CleanerAvailableTimeImpl implements CleanerAvailableTime{
    private Long id;
    private String name;
    private String surName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
