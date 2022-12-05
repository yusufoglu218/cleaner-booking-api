package com.justlife.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Request object for booking update operation
 */
@Data
@AllArgsConstructor
@Builder
public class BookingUpdateRequest {

    @NotNull(message = "startTime is mandatory")
    private LocalDateTime startTime;

    @NotNull(message = "duration is mandatory")
    private Integer duration;

}
