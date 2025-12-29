package ParkingLot.Payments;

import ParkingLot.Constants.PaymentType;
import ParkingLot.Constants.VehicleType;

import java.util.Scanner;

public class UpiPayment extends PaymentInterface {
    String upiId;
    @Override
    public long calculatePayment(long durationInMinutes, int floorNo, PaymentType paymentType) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter UPI ID: ");
        String upiId = sc.nextLine();
        // Here, you would typically validate the UPI details.
        // For simplicity, we'll assume the details are valid.
        return processPayment(durationInMinutes, floorNo); // Example rate
    }
}
