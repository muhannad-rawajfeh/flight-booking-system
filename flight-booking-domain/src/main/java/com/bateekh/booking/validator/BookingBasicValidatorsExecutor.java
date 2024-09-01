package com.bateekh.booking.validator;

import com.bateekh.booking.dto.BookingDetails;
import com.bateekh.booking.validator.basic.BookingBasicValidator;

import java.util.List;

public class BookingBasicValidatorsExecutor {

    private final List<BookingBasicValidator> bookingBasicValidators;

    public BookingBasicValidatorsExecutor(List<BookingBasicValidator> bookingBasicValidators) {
        this.bookingBasicValidators = bookingBasicValidators;
    }

    void execute(BookingDetails bookingDetails) {
        bookingBasicValidators.forEach(bookingBasicValidator ->
                bookingBasicValidator.validate(bookingDetails));
    }
}
