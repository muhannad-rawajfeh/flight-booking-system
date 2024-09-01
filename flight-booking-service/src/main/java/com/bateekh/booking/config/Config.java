package com.bateekh.booking.config;

import com.bateekh.booking.mapper.BookingMapper;
import com.bateekh.booking.mapper.BookingMapperImpl;
import com.bateekh.booking.repository.BookingRepositoryAdaptor;
import com.bateekh.booking.service.BookingService;
import com.bateekh.booking.service.BookingServiceImpl;
import com.bateekh.booking.validator.BookingBasicValidatorsExecutor;
import com.bateekh.booking.validator.BookingValidator;
import com.bateekh.booking.validator.BookingValidatorImpl;
import com.bateekh.booking.validator.basic.DateValidator;
import com.bateekh.booking.validator.basic.NullValidator;
import com.bateekh.booking.validator.basic.PassengersValidator;
import com.bateekh.booking.validator.basic.RoundTripValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {

    @Value("${business.maxDaysFromToday}")
    private int maxDaysFromToday;

    @Value("${business.maxPassengersPerFlight}")
    private int maxPassengersPerFlight;

    @Value("${business.maxPassengersPerBooking}")
    private int maxPassengersPerBooking;

    @Bean
    public BookingService bookingService(BookingValidator bookingValidator, BookingRepositoryAdaptor adaptor) {
        return new BookingServiceImpl(maxPassengersPerFlight, bookingValidator, adaptor);
    }

    @Bean
    public BookingValidator bookingValidator() {
        return new BookingValidatorImpl(bookingBasicValidatorsExecutor());
    }

    @Bean
    public BookingBasicValidatorsExecutor bookingBasicValidatorsExecutor() {
        return new BookingBasicValidatorsExecutor(List.of(
                new NullValidator(),
                new RoundTripValidator(),
                new DateValidator(maxDaysFromToday),
                new PassengersValidator(maxPassengersPerBooking)
        ));
    }

    @Bean
    public BookingMapper bookingMapper() {
        return new BookingMapperImpl();
    }
}
