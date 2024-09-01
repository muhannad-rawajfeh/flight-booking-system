package com.bateekh.booking.validator.basic;

import com.bateekh.booking.dto.SearchFlightsDetails;
import com.bateekh.booking.exception.BookingException;

public class PassengersValidator implements SearchFlightsBasicValidator {

    private final int maxPassengersPerBooking;

    public PassengersValidator(int maxPassengersPerBooking) {
        this.maxPassengersPerBooking = maxPassengersPerBooking;
    }

    @Override
    public void validate(SearchFlightsDetails flightsDetails) {
        int numOfPassengers = flightsDetails.getNumOfPassengers();
        if (numOfPassengers <= 0 || numOfPassengers > maxPassengersPerBooking) {
            throw new BookingException(String.format("Number of passengers should be 1 to %d", maxPassengersPerBooking));
        }
    }
}
