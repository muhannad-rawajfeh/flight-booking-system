package com.bateekh.booking.config;

import com.bateekh.booking.mapper.BookingMapper;
import com.bateekh.booking.mapper.BookingMapperImpl;
import com.bateekh.booking.repository.BookingRepositoryAdaptor;
import com.bateekh.booking.service.AvailableFlightsService;
import com.bateekh.booking.service.AvailableFlightsServiceImpl;
import com.bateekh.booking.service.BookingService;
import com.bateekh.booking.service.BookingServiceImpl;
import com.bateekh.booking.validator.*;
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
    public BookingService bookingService(BookingValidator bookingValidator,
                                         AvailableFlightsService availableFlightsService,
                                         BookingRepositoryAdaptor bookingRepositoryAdaptor) {
        return new BookingServiceImpl(bookingValidator, availableFlightsService, bookingRepositoryAdaptor);
    }

    @Bean
    public AvailableFlightsService availableFlightsService(SearchFlightsValidator searchFlightsValidator,
                                                           BookingRepositoryAdaptor bookingRepositoryAdaptor) {
        return new AvailableFlightsServiceImpl(
                maxPassengersPerFlight,
                searchFlightsValidator,
                bookingRepositoryAdaptor);
    }

    @Bean
    public SearchFlightsValidator searchFlightsValidator(SearchFlightsBasicValidatorsExecutor searchFlightsBasicValidatorsExecutor) {
        return new SearchFlightsValidatorImpl(searchFlightsBasicValidatorsExecutor);
    }

    @Bean
    public BookingValidator bookingValidator(AvailableFlightsService availableFlightsService,
                                             BookingRepositoryAdaptor bookingRepositoryAdaptor) {
        return new BookingValidatorImpl(availableFlightsService, bookingRepositoryAdaptor);
    }

    @Bean
    public SearchFlightsBasicValidatorsExecutor searchFlightsBasicValidatorsExecutor() {
        return new SearchFlightsBasicValidatorsExecutor(List.of(
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
