package com.bateekh.booking.entity;

import com.bateekh.booking.constant.Airport;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalTime;
import java.util.List;

@Data
@Entity(name = "flight")
@ToString(exclude = "bookings")
public class FlightEntity {

    @Id
    private Long id;
    @Column(unique = true)
    private String code;
    @Enumerated(EnumType.STRING)
    private Airport origin;
    @Enumerated(EnumType.STRING)
    private Airport destination;
    private LocalTime operatingTime;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<BookingEntity> bookings;
}
