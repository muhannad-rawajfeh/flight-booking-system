package com.bateekh.booking.validator;

import com.bateekh.booking.dto.SearchFlightsDetails;

public class SearchFlightsValidatorImpl implements SearchFlightsValidator {

    private final SearchFlightsBasicValidatorsExecutor searchFlightsBasicValidatorsExecutor;

    public SearchFlightsValidatorImpl(SearchFlightsBasicValidatorsExecutor searchFlightsBasicValidatorsExecutor) {
        this.searchFlightsBasicValidatorsExecutor = searchFlightsBasicValidatorsExecutor;
    }

    @Override
    public void validate(SearchFlightsDetails flightsDetails) {
        searchFlightsBasicValidatorsExecutor.execute(flightsDetails);
    }
}
