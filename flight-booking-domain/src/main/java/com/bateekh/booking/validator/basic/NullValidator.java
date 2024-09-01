package com.bateekh.booking.validator.basic;

import com.bateekh.booking.dto.SearchFlightsDetails;

public class NullValidator implements SearchFlightsBasicValidator {

    @Override
    public void validate(SearchFlightsDetails flightsDetails) {
        validateNotNull(flightsDetails, "Flight details cannot be null");
        validateNotNull(flightsDetails.getDepartureDate(), "Departure date cannot be null");
        validateNotNull(flightsDetails.getOrigin(), "Origin cannot be null");
        validateNotNull(flightsDetails.getDestination(), "Destination cannot be null");
    }
}
