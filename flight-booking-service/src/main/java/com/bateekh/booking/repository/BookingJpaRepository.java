package com.bateekh.booking.repository;

import com.bateekh.booking.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookingJpaRepository extends JpaRepository<BookingEntity, Long> {

    @Query("SELECT COALESCE(SUM(b.numOfPassengers), 0) FROM booking b WHERE b.flight.code = :flightCode AND b.date = :date")
    int countBookedSeats(@Param("flightCode") String flightCode, @Param("date") LocalDate date);
}
