package com.bateekh.booking.validator;

import com.bateekh.booking.dto.*;
import com.bateekh.booking.exception.BookingException;
import com.bateekh.booking.repository.BookingRepositoryAdaptor;
import com.bateekh.booking.service.AvailableFlightsService;

import java.util.List;
import java.util.Objects;

public class BookingValidatorImpl implements BookingValidator {

    private final AvailableFlightsService availableFlightsService;
    private final BookingRepositoryAdaptor bookingRepositoryAdaptor;

    public BookingValidatorImpl(AvailableFlightsService availableFlightsService,
                                BookingRepositoryAdaptor bookingRepositoryAdaptor) {
        this.availableFlightsService = availableFlightsService;
        this.bookingRepositoryAdaptor = bookingRepositoryAdaptor;
    }

    @Override
    public void validate(BookingDetails bookingDetails) {
        Flight departureFlight = bookingRepositoryAdaptor.findByCode(bookingDetails.getDepartureFlightCode());
        throwIfInvalidFlightCombination(bookingDetails, departureFlight);

        List<? extends AvailableFlight> availableFlights = availableFlightsService
                .findAvailableFlights(toSearchFlightsDetails(bookingDetails, departureFlight));
        throwIfNoSuitableFlights(availableFlights, bookingDetails);
    }

    private void throwIfInvalidFlightCombination(BookingDetails bookingDetails, Flight departureFlight) {
        if (bookingDetails.isRoundTrip()) {
            Flight returnFlight = bookingRepositoryAdaptor.findByCode(bookingDetails.getReturnFlightCode());
            if (!isValidFlightCodeCombination(departureFlight, returnFlight)) {
                throw new BookingException("Invalid flight code combination");
            }
        }
    }

    private void throwIfNoSuitableFlights(List<? extends AvailableFlight> availableFlights, BookingDetails bookingDetails) {
        if (Objects.isNull(availableFlights) || availableFlights.isEmpty()) {
            throw new BookingException("No flights available for the provided booking details");
        }
        boolean isDepartureFlightAvailable = availableFlights.stream().anyMatch(availableFlight ->
                availableFlight.getDepartureFlightDetails().getFlightCode().equals(bookingDetails.getDepartureFlightCode()));
        if (!isDepartureFlightAvailable) {
            throw new BookingException("No flights available for the provided booking details");
        }
        if (bookingDetails.isRoundTrip()) {
            boolean isReturnFlightAvailable = availableFlights.stream().anyMatch(availableFlight -> {
                AvailableRoundTripFlight availableRoundTripFlight = (AvailableRoundTripFlight) availableFlight;
                return availableRoundTripFlight.getReturnFlightDetails().getFlightCode().equals(bookingDetails.getReturnFlightCode());
            });
            if (!isReturnFlightAvailable) {
                throw new BookingException("No flights available for the provided booking details");
            }
        }
    }

    private boolean isValidFlightCodeCombination(Flight departureFlight, Flight returnFlight) {
        return departureFlight.getOrigin().equals(returnFlight.getDestination())
                && departureFlight.getDestination().equals(returnFlight.getOrigin());
    }

    private SearchFlightsDetails toSearchFlightsDetails(BookingDetails bookingDetails, Flight departureFlight) {
        SearchFlightsDetails searchFlightsDetails = new SearchFlightsDetails();
        searchFlightsDetails.setDepartureDate(bookingDetails.getDepartureDate());
        searchFlightsDetails.setReturnDate(bookingDetails.getReturnDate());
        searchFlightsDetails.setOrigin(departureFlight.getOrigin());
        searchFlightsDetails.setDestination(departureFlight.getDestination());
        searchFlightsDetails.setNumOfPassengers(bookingDetails.getNumOfPassengers());
        return searchFlightsDetails;
    }
}
