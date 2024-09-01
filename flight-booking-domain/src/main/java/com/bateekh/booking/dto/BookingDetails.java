package com.bateekh.booking.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class BookingDetails {

    private String name;
    private String departureFlightCode;
    private String returnFlightCode;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private int numOfPassengers;

    public boolean isRoundTrip() {
        return Objects.nonNull(returnFlightCode);
    }
}
