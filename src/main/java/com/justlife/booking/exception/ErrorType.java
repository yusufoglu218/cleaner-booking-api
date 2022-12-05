package com.justlife.booking.exception;

/**
 * Enums for error description and internal error code
 */
public enum ErrorType {
    BOOKING_NOT_FOUND("Booking not found with id: ");

    private final String description;

    ErrorType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }

}
