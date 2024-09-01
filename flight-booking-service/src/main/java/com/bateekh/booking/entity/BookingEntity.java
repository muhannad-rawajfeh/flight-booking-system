package com.bateekh.booking.entity;

import com.bateekh.booking.constant.FlightType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;
    private int numOfPassengers;
    @Enumerated(EnumType.STRING)
    private FlightType flightType;

    @ManyToOne
    @JoinColumn(name = "flight_code", referencedColumnName = "code")
    private FlightEntity flight;
}
