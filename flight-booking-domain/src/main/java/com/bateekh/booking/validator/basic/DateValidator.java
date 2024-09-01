package com.bateekh.booking.validator.basic;

import com.bateekh.booking.dto.BookingDetails;
import com.bateekh.booking.exception.BookingException;

import java.time.LocalDate;
import java.util.Objects;

public class DateValidator implements BookingBasicValidator {

    private final int maxDaysFromToday;

    public DateValidator(int maxDaysFromToday) {
        this.maxDaysFromToday = maxDaysFromToday;
    }

    @Override
    public void validate(BookingDetails bookingDetails) {
        if (bookingDetails.getDepartureDate().isAfter(LocalDate.now().plusDays(maxDaysFromToday))) {
            throw new BookingException(String.format("Date cannot be after %d days from today", maxDaysFromToday));
        }
        LocalDate returnDate = bookingDetails.getReturnDate();
        if (Objects.nonNull(returnDate) && returnDate.isAfter(LocalDate.now().plusDays(maxDaysFromToday))) {
            throw new BookingException(String.format("Date cannot be after %d days from today", maxDaysFromToday));
        }
    }
}
