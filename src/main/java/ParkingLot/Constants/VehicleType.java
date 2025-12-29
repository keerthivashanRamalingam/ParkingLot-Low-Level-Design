package ParkingLot.Constants;

public enum VehicleType {
    CAR(2),
    BIKE(3),
    TRUCK(1);
    final int floorNo;
    VehicleType(int floorNo) {
        this.floorNo = floorNo;
    }

    public static VehicleType getVehicleType(int floorNo) {
        for (VehicleType type : VehicleType.values()) {
            if (type.floorNo == floorNo) {
                return type;
            }
        }
        return null;
    }

    public int getFloorNo() {
        return this.floorNo;
    }

    public static int fetchFlorNo(String vehicleType) {
        for (VehicleType type : VehicleType.values()) {
            if(vehicleType.equals(type.name())){
                return type.getFloorNo();
            }
        }
        throw new UnsupportedOperationException();
    }
}
