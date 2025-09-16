package com.example.flight;

import java.util.ArrayList;
import java.util.List;

public class BookingSystem {
    private final PaymentService paymentService;
    private final NotificationService notificationService;
    private final List<Flight> flights = new ArrayList<>();
    private final List<Passenger> passengers = new ArrayList<>();

    public BookingSystem(PaymentService paymentService, NotificationService notificationService) {
        this.paymentService = paymentService;
        this.notificationService = notificationService;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public boolean bookFlight(Passenger passenger, Flight flight) {
        if (flight.getAvailableSeats() <= 0) {
            return false;
        }
        boolean paid = paymentService.processPayment(passenger, flight);
        if (paid) {
            flight.reduceSeats();
            notificationService.sendBookingConfirmation(passenger, flight);
            return true;
        }
        return false;
    }

    public void cancelBooking(Passenger passenger, Flight flight) {
        flight.increaseSeats();
        notificationService.sendCancellationNotice(passenger, flight);
    }
}
