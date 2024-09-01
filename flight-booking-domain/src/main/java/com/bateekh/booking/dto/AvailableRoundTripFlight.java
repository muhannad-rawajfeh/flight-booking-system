package com.bateekh.booking.dto;

import lombok.Getter;

@Getter
public class AvailableRoundTripFlight extends AvailableFlight {

    private final FlightDetails returnFlightDetails;

    public AvailableRoundTripFlight(FlightDetails departureFlightDetails, FlightDetails returnFlightDetails) {
        super(departureFlightDetails);
        this.returnFlightDetails = returnFlightDetails;
    }
}
