package com.bateekh.booking.service;

import com.bateekh.booking.constant.FlightType;
import com.bateekh.booking.dto.*;
import com.bateekh.booking.repository.BookingRepositoryAdaptor;
import com.bateekh.booking.validator.BookingValidator;

import java.util.List;

public class BookingServiceImpl implements BookingService {

    private final BookingValidator bookingValidator;
    private final AvailableFlightsService availableFlightsService;
    private final BookingRepositoryAdaptor bookingRepositoryAdaptor;

    public BookingServiceImpl(BookingValidator bookingValidator,
                              AvailableFlightsService availableFlightsService,
                              BookingRepositoryAdaptor bookingRepositoryAdaptor) {
        this.bookingValidator = bookingValidator;
        this.availableFlightsService = availableFlightsService;
        this.bookingRepositoryAdaptor = bookingRepositoryAdaptor;
    }

    @Override
    public List<? extends AvailableFlight> findAvailableFlights(SearchFlightsDetails searchFlightsDetails) {
        return availableFlightsService.findAvailableFlights(searchFlightsDetails);
    }

    @Override
    public void bookFlight(BookingDetails bookingDetails) {
        bookingValidator.validate(bookingDetails);

        Booking departureBooking = buildBooking(bookingDetails, FlightType.DEPARTURE);
        bookingRepositoryAdaptor.save(departureBooking);
        if (bookingDetails.isRoundTrip()) {
            Booking returnBooking = buildBooking(bookingDetails, FlightType.RETURN);
            bookingRepositoryAdaptor.save(returnBooking);
        }
    }

    private Booking buildBooking(BookingDetails bookingDetails, FlightType flightType) {
        Booking booking = new Booking();
        booking.setName(bookingDetails.getName());
        booking.setNumOfPassengers(bookingDetails.getNumOfPassengers());
        if (flightType.equals(FlightType.DEPARTURE)) {
            booking.setDate(bookingDetails.getDepartureDate());
            booking.setFlightType(FlightType.DEPARTURE);
            booking.setFlight(new Flight(bookingDetails.getDepartureFlightCode()));
            return booking;
        }
        booking.setDate(bookingDetails.getReturnDate());
        booking.setFlightType(FlightType.RETURN);
        booking.setFlight(new Flight(bookingDetails.getReturnFlightCode()));
        return booking;
    }
}
