package com.justlife.booking.mapper;

import com.justlife.booking.dto.BookingSaveRequest;
import com.justlife.booking.model.Booking;
import org.mapstruct.Mapper;

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

}
