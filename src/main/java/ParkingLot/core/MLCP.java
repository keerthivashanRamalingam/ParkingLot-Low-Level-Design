package ParkingLot.core;

import ParkingLot.Constants.TicketStatus;
import ParkingLot.Constants.TicketsTableConstants;
import ParkingLot.Constants.VehicleType;
import ParkingLot.Factories.FloorFactory;
import ParkingLot.Floors.Floor;
import ParkingLot.Floors.Slot;
import ParkingLot.Repo.DBMaintainer;

import java.sql.SQLException;
import java.time.Instant;
import java.util.*;


public class MLCP {
    static final List<Floor> allFloors = new ArrayList<>();
    private static MLCP MLCPINSATANCE = null;
    public static MLCP initialise() throws Exception{
        if(MLCPINSATANCE == null) {
            Floor Floor = FloorFactory.createFloor(VehicleType.TRUCK);
            allFloors.add(Floor);

            Floor = FloorFactory.createFloor(VehicleType.CAR);
            allFloors.add(Floor);

            Floor = FloorFactory.createFloor(VehicleType.BIKE);
            allFloors.add(Floor);

            MLCPINSATANCE = new MLCP();
        }
        DBMaintainer.initializeDB();
        return MLCPINSATANCE;
    }

    public static Slot bookSlot(VehicleType type) {
        Floor floor = getFloorInstance(type.getFloorNo());
        assert floor != null;
        if (floor.isSlotAvailable()) {
            int currentSlot = floor.getAvailableSlot();
            return floor.occupySlot(currentSlot);
        }
        return null;
    }

    public static Floor getFloorInstance(int floorNo) {
        for (Floor floor : allFloors) {
            if (floor.getVehicleType().getFloorNo() == floorNo) {
                return floor;
            }
        }
        return null;
    }

    public Map<String, Object> issueTicket(VehicleType type, String vehicleNo) throws SQLException, ClassNotFoundException {
        synchronized (this){
            Slot assignedSlot = bookSlot(type);
            if(assignedSlot != null){
                int ticketId = DBMaintainer.addRow(TicketStatus.ACTIVE.toString(),vehicleNo, type.toString(), type.getFloorNo(), assignedSlot.getSlotNumber());
                Map<String , Object> rowMap = new HashMap<>();
                rowMap.put(TicketsTableConstants.TICKET_ID, ticketId);
                rowMap.put(TicketsTableConstants.VEHICLE_NUMBER, vehicleNo);
                rowMap.put(TicketsTableConstants.FLOOR_NUMBER, type.getFloorNo());
                rowMap.put(TicketsTableConstants.SLOT_NUMBER, assignedSlot.getSlotNumber());
                return rowMap;
            }
            throw new RuntimeException("No slots available");
        }
        //TODO: Have to implemet Databse logic to generate ticket
    }

    public boolean closeTicket(int ticketId) {
        synchronized (this){
            Map<String, Object> ticketRow = DBMaintainer.fetchRow(ticketId);
            if(!ticketRow.isEmpty()){
                Floor floor = getFloorInstance(VehicleType.fetchFlorNo(ticketRow.get(TicketsTableConstants.VEHICLE_TYPE).toString()));
                assert floor != null;
                // Free the slot logic to be implemented
                floor.freeSlot(floor.getAvailableSlot());
                DBMaintainer.updateStatus(Integer.parseInt(ticketRow.get(TicketsTableConstants.TICKET_ID).toString()), TicketStatus.CLOSED.name());
                return true; // Ticket closed successfully
            }
            return false; // Ticket ID not found
        }
    }
}