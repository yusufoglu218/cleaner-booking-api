package com.justlife.booking.mapper;

import com.justlife.booking.dto.BookingSaveRequest;
import com.justlife.booking.dto.GetBookingResponse;
import com.justlife.booking.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Mapper for converting dto objects to the entity objects
 */
@Mapper(componentModel = "spring")
public interface BookingMapper {

    /**
     * Map BookingSaveRequest to the Booking
     * @param bookingSaveRequest
     * @return Booking
     */
    Booking bookingSaveRequestToBooking(BookingSaveRequest bookingSaveRequest);


    /**
     * Map Booking to the GetBookingResponse
     * @param booking
     * @return BookingSaveRequest
     */
    @Mapping(source = "booking.id", target ="bookingId")
    GetBookingResponse bookingToGetBookingResponse(Booking booking, LocalDateTime startTime, Integer duration, List<Long> cleanerIds);



}
