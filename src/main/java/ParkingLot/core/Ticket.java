package ParkingLot.core;


import ParkingLot.Constants.VehicleType;
import ParkingLot.Floors.Slot;

public class Ticket {
    private final int ticketId;
    private String licensePlate;
    private final long ticketIssueTime;
    private final VehicleType vehicleType;
    private final Slot assignedSlot;
    private final int floorNumber;

    public Ticket(String vehicleNumber, long ticketIssueTime, VehicleType vehicleType, Slot assignedSlot) {
        this.licensePlate = vehicleNumber;
        this.ticketIssueTime = ticketIssueTime;
        this.vehicleType = vehicleType;
        this.ticketId = this.hashCode();
        this.assignedSlot = assignedSlot;
        this.floorNumber = vehicleType.getFloorNo();
    }

    public int getTicketId(){
        return this.ticketId;
    }

    public int getSlotNumber(){
        return this.assignedSlot.getSlotNumber();
    }

    public int getFloorNumber(){
        return this.floorNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public long getTicketIssueTime(){ return this.ticketIssueTime;}
}