package com.example.flight;

public interface PaymentService {
    boolean processPayment(Passenger passenger, Flight flight);
}
