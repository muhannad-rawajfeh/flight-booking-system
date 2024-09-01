package com.bateekh.booking.dto;

import lombok.Getter;

@Getter
public class AvailableFlight {

    private final FlightDetails departureFlightDetails;

    public AvailableFlight(FlightDetails departureFlightDetails) {
        this.departureFlightDetails = departureFlightDetails;
    }
}
