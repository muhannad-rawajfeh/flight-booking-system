package com.bateekh.booking.repository;

import com.bateekh.booking.constant.Airport;
import com.bateekh.booking.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightJpaRepository extends JpaRepository<FlightEntity, String> {

    Optional<FlightEntity> findByCode(String code);

    @Query("SELECT f FROM flight f " +
            "LEFT JOIN f.bookings b ON b.date = :date " +
            "WHERE f.origin = :origin AND f.destination = :destination " +
            "GROUP BY f " +
            "HAVING (COALESCE(SUM(b.numOfPassengers), 0) + :numOfPassengers) <= :maxPassengersPerFlight")
    List<FlightEntity> findAvailableFlights(
            @Param("origin") Airport origin,
            @Param("destination") Airport destination,
            @Param("date") LocalDate date,
            @Param("numOfPassengers") int numOfPassengers,
            @Param("maxPassengersPerFlight") int maxPassengersPerFlight);
}
