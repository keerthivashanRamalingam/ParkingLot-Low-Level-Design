package ParkingLot.Floors;

import ParkingLot.Constants.VehicleType;

public class Slot {
    final int slotNumber;
    final int floorNumber;
    private final VehicleType vehicleType;
    Slot(VehicleType type, int slotNumber) {
        this.vehicleType = type;
        this.floorNumber = type.getFloorNo();
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber(){
        return this.slotNumber;
    }

    public int getFloorNumber(){
        return this.floorNumber;
    }
}
