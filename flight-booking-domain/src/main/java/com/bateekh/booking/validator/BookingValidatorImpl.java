package com.bateekh.booking.validator;

import com.bateekh.booking.dto.BookingDetails;

public class BookingValidatorImpl implements BookingValidator {

    private final BookingBasicValidatorsExecutor bookingBasicValidatorsExecutor;

    public BookingValidatorImpl(BookingBasicValidatorsExecutor bookingBasicValidatorsExecutor) {
        this.bookingBasicValidatorsExecutor = bookingBasicValidatorsExecutor;
    }

    @Override
    public void validate(BookingDetails bookingDetails) {
        bookingBasicValidatorsExecutor.execute(bookingDetails);
    }
}
