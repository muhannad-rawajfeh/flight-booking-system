package com.bateekh.booking.service;

import com.bateekh.booking.constant.Airport;
import com.bateekh.booking.constant.FlightType;
import com.bateekh.booking.dto.*;
import com.bateekh.booking.repository.BookingRepositoryAdaptor;
import com.bateekh.booking.validator.SearchFlightsValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvailableFlightsServiceImpl implements AvailableFlightsService {

    private final int maxPassengersPerFlight;
    private final SearchFlightsValidator searchFlightsValidator;
    private final BookingRepositoryAdaptor bookingRepositoryAdaptor;

    public AvailableFlightsServiceImpl(int maxPassengersPerFlight,
                                       SearchFlightsValidator searchFlightsValidator,
                                       BookingRepositoryAdaptor bookingRepositoryAdaptor) {
        this.maxPassengersPerFlight = maxPassengersPerFlight;
        this.searchFlightsValidator = searchFlightsValidator;
        this.bookingRepositoryAdaptor = bookingRepositoryAdaptor;
    }

    @Override
    public List<? extends AvailableFlight> findAvailableFlights(SearchFlightsDetails searchFlightsDetails) {
        searchFlightsValidator.validate(searchFlightsDetails);

        List<FlightDetails> availableDepartureFlights = findAndMapAvailableFlights(searchFlightsDetails, FlightType.DEPARTURE);
        if (!searchFlightsDetails.isRoundTrip()) {
            List<AvailableFlight> availableFlights = new ArrayList<>();
            availableDepartureFlights.forEach(flightDetails -> availableFlights.add(new AvailableFlight(flightDetails)));
            return availableFlights;
        }

        List<FlightDetails> availableReturnFlights = findAndMapAvailableFlights(searchFlightsDetails, FlightType.RETURN);

        List<AvailableRoundTripFlight> availableRoundTripFlights = new ArrayList<>();
        availableDepartureFlights.forEach(departureFlightDetails ->
                availableReturnFlights.forEach(returnFlightDetails ->
                        availableRoundTripFlights.add(
                                new AvailableRoundTripFlight(departureFlightDetails, returnFlightDetails))));

        return availableRoundTripFlights;
    }

    private List<FlightDetails> findAndMapAvailableFlights(SearchFlightsDetails searchFlightsDetails, FlightType flightType) {
        LocalDate date = flightType.equals(FlightType.DEPARTURE)
                ? searchFlightsDetails.getDepartureDate()
                : searchFlightsDetails.getReturnDate();
        Airport origin = flightType.equals(FlightType.DEPARTURE)
                ? searchFlightsDetails.getOrigin()
                : searchFlightsDetails.getDestination();
        Airport destination = flightType.equals(FlightType.DEPARTURE)
                ? searchFlightsDetails.getDestination()
                : searchFlightsDetails.getOrigin();

        List<Flight> availableFlights = bookingRepositoryAdaptor.findAvailableFlights(
                origin,
                destination,
                date,
                searchFlightsDetails.getNumOfPassengers(),
                maxPassengersPerFlight
        );
        return availableFlights
                .stream()
                .map(flight -> toDetails(flight, date, bookingRepositoryAdaptor.countBookedSeats(flight.getCode(), date)))
                .toList();
    }

    private FlightDetails toDetails(Flight flight, LocalDate localDate, int numOfAlreadyBookedSeats) {
        return FlightDetails.builder()
                .flightCode(flight.getCode())
                .departureAirport(flight.getOrigin())
                .arrivalAirport(flight.getDestination())
                .date(localDate)
                .availableSeats(maxPassengersPerFlight - numOfAlreadyBookedSeats)
                .build();
    }
}
