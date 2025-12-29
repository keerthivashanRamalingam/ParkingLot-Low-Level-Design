package ParkingLot.Payments;

import ParkingLot.Constants.PaymentType;
import ParkingLot.Constants.VehicleType;

public class CashPayment extends PaymentInterface {
    @Override
    public long calculatePayment(long durationInMinutes, int floorNo, PaymentType paymentType) {
        //Assume the cash is always valid and received.
        return processPayment(durationInMinutes, floorNo); // Example rate
    }
}
