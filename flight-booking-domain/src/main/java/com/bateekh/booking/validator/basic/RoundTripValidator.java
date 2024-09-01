package com.bateekh.booking.validator.basic;

import com.bateekh.booking.dto.BookingDetails;
import com.bateekh.booking.exception.BookingException;

public class RoundTripValidator implements BookingBasicValidator {

    @Override
    public void validate(BookingDetails bookingDetails) {
        if (bookingDetails.isRoundTrip()) {
            validateNotNull(bookingDetails.getReturnDate(), "Return date cannot be null in a round trip");

            if (bookingDetails.getReturnDate().isBefore(bookingDetails.getDepartureDate())) {
                throw new BookingException("Return date cannot be before departure date");
            }

            if (bookingDetails.getDepartureFlightCode().equals(bookingDetails.getReturnFlightCode())) {
                throw new BookingException("Departure and return codes cannot be the same");
            }
        }
    }
}
