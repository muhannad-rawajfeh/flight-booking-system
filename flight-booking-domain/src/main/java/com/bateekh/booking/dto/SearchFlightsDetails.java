package com.bateekh.booking.dto;

import com.bateekh.booking.constant.Airport;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class SearchFlightsDetails {

    private LocalDate departureDate;
    private LocalDate returnDate;
    private Airport origin;
    private Airport destination;
    private int numOfPassengers;

    public boolean isRoundTrip() {
        return Objects.nonNull(returnDate);
    }
}
