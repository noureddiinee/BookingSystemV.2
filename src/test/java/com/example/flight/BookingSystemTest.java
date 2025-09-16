package com.example.flight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookingSystemTest {

    private PaymentService paymentService;
    private NotificationService notificationService;
    private BookingSystem system;

    @BeforeEach
    void setup() {
        paymentService = mock(PaymentService.class);
        notificationService = mock(NotificationService.class);
        system = new BookingSystem(paymentService, notificationService);
    }

    @Test
    void testBookingSuccessReducesSeats() {
        Flight flight = new Flight("AF123", "Paris", "NYC", 2);
        Passenger passenger = new Passenger("John Doe", "P123456");
        system.addFlight(flight);
        system.addPassenger(passenger);

        when(paymentService.processPayment(passenger, flight)).thenReturn(true);

        boolean booked = system.bookFlight(passenger, flight);

        assertTrue(booked);
        assertEquals(1, flight.getAvailableSeats());

        verify(notificationService).sendBookingConfirmation(passenger, flight);
    }

    @Test
    void testCannotBookWhenPaymentFails() {
        Flight flight = new Flight("AF124", "Paris", "NYC", 1);
        Passenger passenger = new Passenger("Jane Smith", "P654321");
        system.addFlight(flight);
        system.addPassenger(passenger);

        when(paymentService.processPayment(passenger, flight)).thenReturn(false);

        boolean booked = system.bookFlight(passenger, flight);

        assertFalse(booked);
        assertEquals(1, flight.getAvailableSeats());

        verify(notificationService, never()).sendBookingConfirmation(passenger, flight);
    }

    @Test
    void testCancelBookingIncreasesSeats() {
        Flight flight = new Flight("AF125", "Paris", "NYC", 1);
        Passenger passenger = new Passenger("John Doe", "P123456");
        system.addFlight(flight);
        system.addPassenger(passenger);

        when(paymentService.processPayment(passenger, flight)).thenReturn(true);

        system.bookFlight(passenger, flight);
        system.cancelBooking(passenger, flight);

        assertEquals(1, flight.getAvailableSeats());
        verify(notificationService).sendCancellationNotice(passenger, flight);
    }
}
