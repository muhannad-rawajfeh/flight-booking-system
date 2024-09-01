package com.bateekh.booking.repository;

import com.bateekh.booking.constant.Airport;
import com.bateekh.booking.dto.Booking;
import com.bateekh.booking.dto.Flight;
import com.bateekh.booking.entity.BookingEntity;
import com.bateekh.booking.entity.FlightEntity;
import com.bateekh.booking.mapper.BookingMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaBookingRepositoryAdapter implements BookingRepositoryAdaptor {

    private final BookingMapper bookingMapper;
    private final FlightJpaRepository flightJpaRepository;
    private final BookingJpaRepository bookingJpaRepository;

    public JpaBookingRepositoryAdapter(BookingMapper bookingMapper,
                                       FlightJpaRepository flightJpaRepository,
                                       BookingJpaRepository bookingJpaRepository) {
        this.bookingMapper = bookingMapper;
        this.flightJpaRepository = flightJpaRepository;
        this.bookingJpaRepository = bookingJpaRepository;
    }

    @Override
    public List<Flight> findAvailableFlights(Airport origin,
                                             Airport destination,
                                             LocalDate date,
                                             int numOfPassengers,
                                             int maxPassengersPerFlight) {
        List<FlightEntity> flights = flightJpaRepository.findAvailableFlights(
                origin,
                destination,
                date,
                numOfPassengers,
                maxPassengersPerFlight);
        return bookingMapper.toDomain(flights);
    }

    @Override
    public int countBookedSeats(String flightCode, LocalDate date) {
        return bookingJpaRepository.countBookedSeats(flightCode, date);
    }

    @Override
    public void save(Booking booking) {
        BookingEntity bookingEntity = bookingMapper.toEntity(booking);
        Optional<FlightEntity> flightByCode = flightJpaRepository.findByCode(booking.getFlight().getCode());
        bookingEntity.setFlight(flightByCode.orElseThrow());
        bookingJpaRepository.save(bookingEntity);
    }
}
