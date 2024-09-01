package com.bateekh.booking.repository;

import com.bateekh.booking.constant.Airport;
import com.bateekh.booking.dto.Booking;
import com.bateekh.booking.dto.Flight;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepositoryAdaptor {

    List<Flight> findAvailableFlights(Airport origin,
                                      Airport destination,
                                      LocalDate date,
                                      int numOfPassengers,
                                      int maxPassengersPerFlight);

    int countBookedSeats(String flightCode, LocalDate date);

    void save(Booking booking);
}
