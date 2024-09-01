package com.bateekh.booking.mapper;

import com.bateekh.booking.dto.Booking;
import com.bateekh.booking.dto.Flight;
import com.bateekh.booking.entity.BookingEntity;
import com.bateekh.booking.entity.FlightEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BookingMapper {

    BookingEntity toEntity(Booking booking);

    List<Flight> toDomain(List<FlightEntity> flightEntities);
}
