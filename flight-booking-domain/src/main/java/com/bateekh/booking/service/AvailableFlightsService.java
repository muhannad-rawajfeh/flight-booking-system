package com.bateekh.booking.service;

import com.bateekh.booking.dto.AvailableFlight;
import com.bateekh.booking.dto.SearchFlightsDetails;

import java.util.List;

public interface AvailableFlightsService {

    List<? extends AvailableFlight> findAvailableFlights(SearchFlightsDetails searchFlightsDetails);
}
