package ParkingLot.Factories;

import ParkingLot.Constants.VehicleType;
import ParkingLot.Floors.FirstFloor;
import ParkingLot.Floors.Floor;
import ParkingLot.Floors.*;

public class FloorFactory {
    public static Floor createFloor(VehicleType type) {
        switch (type){
            case TRUCK -> {
                return new GroundFloor(VehicleType.TRUCK);
            }
            case CAR -> {
                return new FirstFloor(VehicleType.CAR);
            }
            case BIKE -> {
                return new SecondFloor(VehicleType.BIKE);
            }
            default -> throw new IllegalArgumentException("Invalid Vehicle Type");
        }
    }
}