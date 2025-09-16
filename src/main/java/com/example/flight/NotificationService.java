package com.example.flight;

public interface NotificationService {
    void sendBookingConfirmation(Passenger passenger, Flight flight);
    void sendCancellationNotice(Passenger passenger, Flight flight);
}
