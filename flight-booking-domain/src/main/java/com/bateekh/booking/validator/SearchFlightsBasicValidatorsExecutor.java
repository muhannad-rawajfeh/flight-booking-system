package com.bateekh.booking.validator;

import com.bateekh.booking.dto.SearchFlightsDetails;
import com.bateekh.booking.validator.basic.SearchFlightsBasicValidator;

import java.util.List;

public class SearchFlightsBasicValidatorsExecutor {

    private final List<SearchFlightsBasicValidator> searchFlightsBasicValidators;

    public SearchFlightsBasicValidatorsExecutor(List<SearchFlightsBasicValidator> searchFlightsBasicValidators) {
        this.searchFlightsBasicValidators = searchFlightsBasicValidators;
    }

    void execute(SearchFlightsDetails flightsDetails) {
        searchFlightsBasicValidators.forEach(bookingBasicValidator ->
                bookingBasicValidator.validate(flightsDetails));
    }
}
