package com.bateekh.booking.dto;

import com.bateekh.booking.constant.Airport;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FlightDetails {

    private String flightCode;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDate date;
    private int availableSeats;
}
