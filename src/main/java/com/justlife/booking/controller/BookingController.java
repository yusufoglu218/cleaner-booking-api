package com.justlife.booking.controller;

import com.justlife.booking.dto.BookingSaveRequest;
import com.justlife.booking.dto.BookingUpdateRequest;
import com.justlife.booking.exception.RecordNotFoundException;
import com.justlife.booking.model.Booking;
import com.justlife.booking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * Controller class for booking operations
 */
@Tag(name = "Booking-Controller", description = "Booking Rest API")
@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Save booking by request body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Booking.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))})
    })
    @PostMapping
    public Booking bookingSave(@Parameter(description = "Booking request body") @RequestBody @NotNull BookingSaveRequest bookingRequest) {
        return bookingService.saveBooking(bookingRequest);
    }

    @Operation(summary = "Update the booking by id and booking body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Booking.class))}),
            @ApiResponse(responseCode = "404", description = "Booking not found", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RecordNotFoundException.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))})
    })
    @PatchMapping(value = "{id}")
    public Booking bookingUpdate(@Parameter(description = "Id of the booking to update") @PathVariable Long id,
                                 @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Booking request body") @RequestBody BookingUpdateRequest bookingUpdateRequest) {
        return bookingService.updateBooking(id, bookingUpdateRequest);
    }

    @Operation(summary = "Get booking by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Booking.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Exception.class))})
    })
    @GetMapping(value = "{id}")
    public Booking getBookingById(@Parameter(description = "Id of the booking") @PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

}
