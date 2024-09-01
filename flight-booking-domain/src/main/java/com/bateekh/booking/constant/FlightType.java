package com.bateekh.booking.constant;

import lombok.Getter;

@Getter
public enum FlightType {

    DEPARTURE("DEPARTURE"),
    RETURN("RETURN");

    private final String value;

    FlightType(String value) {
        this.value = value;
    }
}
