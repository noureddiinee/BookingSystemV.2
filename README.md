# Flight Booking System

**Flight Booking System** is a Java-based application that simulates managing flights, passengers, and bookings. This project demonstrates object-oriented programming, test-driven development, and the use of **test doubles** with **Mockito** for unit testing.

---

## Features

- **Flight Management**
  - Create flights with a unique flight number, origin, destination, and seat capacity.
  
- **Passenger Management**
  - Add passengers with unique identifiers.

- **Booking Functionality**
  - Book passengers into flights.
  - Reduce available seats when a booking is made.
  - Prevent overbooking if a flight is full.

- **Cancellation**
  - Cancel bookings and automatically increase available seats.

- **Payment and Notifications**
  - PaymentService and NotificationService are **mocked using Mockito** in tests.
  - Allows testing without requiring real payment processing or notifications.

---

## Technologies Used

- Java 17+
- JUnit 5 (Unit Testing)
- Mockito (Test Doubles)
- Eclipse IDE
- EclEmma (Code Coverage)

---

## Installation & Running

1. **Clone the repository**
```bash
git clone https://github.com/YOUR_USERNAME/BookingSystem.git
