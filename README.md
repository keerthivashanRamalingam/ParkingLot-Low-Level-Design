This project is a Low-Level Design (LLD) implementation of a Parking Lot system built using Java (Maven) and PostgreSQL.

The system supports:
  * Parking and unparking vehicles
  * Ticket generation and lifecycle management
  * Multiple vehicle types (TRUCK, CAR, BIKE)
  * Slot allocation based on floor eligibility
  * Payment processing using different payment methods
  * Persistent storage of ticket information in a relational database
The project focuses on clean object-oriented design, correct responsibility separation, and applying design patterns only where they are justified.

High-Level Design:
  * MLCP (ParkingLot) acts as the facade / orchestration layer
  * Floor manages slot eligibility and availability
  * Slot represents an allocatable parking space
  * Ticket represents a parking session and is persisted in the database
  * Payment logic is decoupled using behavior-based design

Project Overview :
  In this Parking lot project we have used java maven project to park and unpark a vehicle and as of now we have limited the vehicle types like TRUCK, CAR, BIKE type to park in the lot.And also we
used PostgreSQL to store the ticket details.

Design Patterns Used:
1️⃣ Factory Pattern
Used to encapsulate object creation logic, such as:
  * Creating different Floor instances
  * Creating appropriate Payment implementations
This avoids tight coupling between object creation and business logic, making the system easier to extend.

2️⃣ Strategy Pattern
Used for payment processing, where different payment methods share a common interface but implement different behaviors.
Examples:
  * Cash payment
  * UPI payment
  * Credit card payment
This allows new payment methods to be added without modifying existing logic.

