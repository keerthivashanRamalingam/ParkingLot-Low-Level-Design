package ParkingLot.Factories;

import ParkingLot.Payments.CashPayment;
import ParkingLot.Payments.CreditCardPayment;
import ParkingLot.Payments.PaymentInterface;
import ParkingLot.Payments.UpiPayment;

public class PaymentFactory {
    public static PaymentInterface getPaymentInstance(int paymentMethod){
        return switch (paymentMethod) {
            case 1 -> new CreditCardPayment();
            case 2 -> new CashPayment();
            case 3 -> new UpiPayment();
            default -> throw new UnsupportedOperationException("No such payment method supported");
        };
    }
}
