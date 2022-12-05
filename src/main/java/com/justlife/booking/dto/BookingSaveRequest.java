package com.justlife.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Request object for booking save operation
 */
@Data
@AllArgsConstructor
@Builder
public class BookingSaveRequest {

    @NotNull(message = "customerId is mandatory")
    private Long customerId;

    @NotNull(message = "cleanerId list is mandatory")
    private List<Long> cleanerIds;

    @NotNull(message = "startTime is mandatory")
    private LocalDateTime startTime;

    @NotNull(message = "duration is mandatory")
    private Integer duration;

}
