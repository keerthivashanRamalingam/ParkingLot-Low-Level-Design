package ParkingLot.Floors;

import ParkingLot.Constants.VehicleType;

public interface Floor {
    VehicleType getVehicleType();
    int getAvailableSlot();
    Slot occupySlot(int slotNumber);
    boolean freeSlot(int slotNumber);
    boolean isSlotAvailable();
}
