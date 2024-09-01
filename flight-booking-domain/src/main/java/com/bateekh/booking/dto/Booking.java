package com.bateekh.booking.dto;

import com.bateekh.booking.constant.FlightType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Booking {

    private String name;
    private LocalDate date;
    private int numOfPassengers;
    private FlightType flightType;
    private Flight flight;
}
