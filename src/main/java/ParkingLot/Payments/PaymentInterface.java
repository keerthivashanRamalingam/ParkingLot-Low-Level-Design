package ParkingLot.Payments;

import ParkingLot.Constants.PaymentType;
import ParkingLot.Constants.VehicleType;

import static ParkingLot.Constants.ParkingConstants.*;


public abstract class PaymentInterface {
    public abstract long calculatePayment(long durationInMinutes, int  floorNo, PaymentType paymentType) throws Exception;

    public static long processPayment(long durationInMinutes, int floorNo) {
        return switch (floorNo) {
            case 2 -> durationInMinutes * AMOUNT_PER_MIN_FOR_CAR;
            case 3 -> durationInMinutes * AMOUNT_PER_MIN_FOR_BIKE;
            case 1 -> durationInMinutes * AMOUNT_PER_MIN_FOR_TRUCK;
            default -> throw new IllegalStateException("Unexpected value: " + floorNo);
        };
    }
}

