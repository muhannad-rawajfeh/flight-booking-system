package com.bateekh.booking.validator.basic;

import com.bateekh.booking.dto.SearchFlightsDetails;
import com.bateekh.booking.exception.BookingException;

public class RoundTripValidator implements SearchFlightsBasicValidator {

    @Override
    public void validate(SearchFlightsDetails flightsDetails) {
        if (flightsDetails.isRoundTrip()) {
            validateNotNull(flightsDetails.getReturnDate(), "Return date cannot be null in a round trip");

            if (flightsDetails.getReturnDate().isBefore(flightsDetails.getDepartureDate())) {
                throw new BookingException("Return date cannot be before departure date");
            }

            if (flightsDetails.getOrigin().equals(flightsDetails.getDestination())) {
                throw new BookingException("Origin and destination cannot be the same");
            }
        }
    }
}
