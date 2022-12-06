package com.justlife.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Request object for booking save operation
 */
@Data
@AllArgsConstructor
@Builder
public class GetBookingResponse {

    private Long bookingId;

    private Long customerId;

    private List<Long> cleanerIds;

    private LocalDateTime startTime;

    private Integer duration;

}
