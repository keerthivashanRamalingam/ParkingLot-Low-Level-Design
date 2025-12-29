package ParkingLot.Floors;

import ParkingLot.Constants.VehicleType;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import static ParkingLot.Constants.ParkingConstants.TOTAL_BIKE_SLOTS;
import static ParkingLot.Constants.ParkingConstants.TOTAL_CAR_SLOTS;

public class FirstFloor implements Floor {
    Map<Integer, Slot> slotMap;
    VehicleType vehicleType;
    private final TreeSet<Integer> availableSlots ;

    public FirstFloor(VehicleType type) {
        this.vehicleType = type;
        slotMap = new HashMap<>(TOTAL_CAR_SLOTS);
        availableSlots = new TreeSet<>();
        for(int i = 1; i <= TOTAL_CAR_SLOTS; i++){
            availableSlots.add(i);
        }
    }

    @Override
    public VehicleType getVehicleType() {
        return this.vehicleType;
    }


    @Override
    public int getAvailableSlot() {
        try{
            return this.availableSlots.getFirst();
        } catch (Exception e){
            throw new RuntimeException("No slots available");
        }
    }

    @Override
    public Slot occupySlot(int slotNumber) {
        Slot slot = new Slot(this.vehicleType, slotNumber);
        slotMap.put(slotNumber, slot);
        availableSlots.remove(slotNumber);
        return slot;
    }

    @Override
    public boolean freeSlot(int slotNumber) {
        if(slotNumber < 0 || slotNumber >= TOTAL_CAR_SLOTS){
            return false;
        }
        slotMap.remove(slotNumber);
        availableSlots.add(slotNumber);
        return true;
    }

    @Override
    public boolean isSlotAvailable() {
        return !availableSlots.isEmpty();
    }
}

