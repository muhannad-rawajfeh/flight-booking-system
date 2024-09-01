package com.bateekh.booking.validator.basic;

import com.bateekh.booking.dto.BookingDetails;

public class NullValidator implements BookingBasicValidator {

    @Override
    public void validate(BookingDetails bookingDetails) {
        validateNotNull(bookingDetails, "Booking request cannot be null");
        validateNotNull(bookingDetails.getDepartureDate(), "Departure date cannot be null");
        validateNotEmpty(bookingDetails.getDepartureFlightCode(), "Departure flight code cannot be empty");
    }
}
