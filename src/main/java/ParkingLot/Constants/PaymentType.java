package ParkingLot.Constants;

public enum PaymentType {
    CASH(1),
    CARD(2),
    UPI(3);
    int code  ;

    PaymentType(int code) {
     this.code = code;
    }

    public static PaymentType getPaymentType(int code) {
        for(PaymentType paymentType : PaymentType.values()) {
            if(paymentType.code == code) {
                return paymentType;
            }
        }
        return null;
    }
}
