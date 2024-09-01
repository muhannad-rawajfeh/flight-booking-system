package com.bateekh.booking.validator.basic;

import com.bateekh.booking.dto.SearchFlightsDetails;
import com.bateekh.booking.exception.BookingException;

import static java.util.Objects.isNull;

public interface SearchFlightsBasicValidator {

    void validate(SearchFlightsDetails flightsDetails);

    default void validateNotNull(Object value, String message) {
        if (isNull(value)) {
            throw new BookingException(message);
        }
    }

    default void validateNotEmpty(String value, String message) {
        validateNotNull(value, message);
        if (value.isBlank()) {
            throw new BookingException(message);
        }
    }
}
