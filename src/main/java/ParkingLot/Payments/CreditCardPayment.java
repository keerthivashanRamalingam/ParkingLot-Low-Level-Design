package ParkingLot.Payments;

import ParkingLot.Constants.PaymentType;
import ParkingLot.Constants.VehicleType;

import java.util.Scanner;

public class CreditCardPayment extends PaymentInterface {
    Long cardNumber;
    int pin;
    @Override
    public long calculatePayment(long durationInMinutes, int floorNo, PaymentType paymentType) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Credit Card Number: ");
        String cardNumber = sc.nextLine();
        System.out.print("Enter PIN : ");
        String pin = sc.nextLine();
        // Here, you would typically validate the card details.
        // For simplicity, we'll assume the details are valid.
        return processPayment(durationInMinutes, floorNo); // Example rate
    }
}
