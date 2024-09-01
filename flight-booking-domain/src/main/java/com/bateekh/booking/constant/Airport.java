package com.bateekh.booking.constant;

import lombok.Getter;

@Getter
public enum Airport {

    AMM("AMM"),
    DXB("DXB");

    private final String value;

    Airport(String value) {
        this.value = value;
    }
}
