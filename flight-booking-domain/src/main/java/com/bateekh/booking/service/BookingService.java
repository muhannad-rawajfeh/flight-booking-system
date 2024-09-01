package com.bateekh.booking.service;

import com.bateekh.booking.dto.AvailableFlight;
import com.bateekh.booking.dto.BookingDetails;
import com.bateekh.booking.dto.SearchFlightsDetails;

import java.util.List;

public interface BookingService {

    List<? extends AvailableFlight> findAvailableFlights(SearchFlightsDetails searchFlightsDetails);

    void bookFlight(BookingDetails bookingDetails);
}
