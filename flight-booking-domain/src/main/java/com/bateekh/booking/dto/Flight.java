package com.bateekh.booking.dto;

import com.bateekh.booking.constant.Airport;
import lombok.Data;

import java.time.LocalTime;

@Data
public class Flight {

    private String code;
    private Airport origin;
    private Airport destination;
    private LocalTime operatingTime;

    public Flight(String code) {
        this.code = code;
    }
}
