package com.bateekh.booking.controller;

import com.bateekh.booking.dto.AvailableFlight;
import com.bateekh.booking.dto.BookingDetails;
import com.bateekh.booking.dto.SearchFlightsDetails;
import com.bateekh.booking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/search")
    public ResponseEntity<List<? extends AvailableFlight>> findAvailableFlights(
            @RequestBody SearchFlightsDetails searchFlightsDetails) {

        List<? extends AvailableFlight> availableFlights = bookingService.findAvailableFlights(searchFlightsDetails);
        return ResponseEntity.ok(availableFlights);
    }

    @PostMapping("/book")
    public ResponseEntity<HttpStatus> bookFlight(@RequestBody BookingDetails bookingDetails) {
        bookingService.bookFlight(bookingDetails);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
