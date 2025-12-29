package ParkingLot.core;

import ParkingLot.Constants.PaymentType;
import ParkingLot.Constants.TicketsTableConstants;
import ParkingLot.Constants.VehicleType;
import ParkingLot.Factories.PaymentFactory;
import ParkingLot.Payments.*;
import ParkingLot.Repo.TicketDao;

import java.util.*;
import java.util.Scanner;

public class ServiceStarter {
    static void main() throws Exception {
        MLCP mlcpInstance = MLCP.initialise();
        while(true) {
            System.out.println("Multi-Level Car Parking System Initialized.");
            Scanner sc = new Scanner(System.in);
            System.out.println("If you wanna park your vehicle press 1 else press 0 to exit.");
            int userInput = sc.nextInt();
            if (userInput == 0) {
                System.out.println("Exiting the system. Goodbye!");
                System.out.println("Can you share your ticketId to close the ticket?");
                int ticketId = sc.nextInt();
                try {
                    collectPaymentInfo(ticketId);
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Error processing payment: " + e.getMessage());
                    return;
                }
                if (mlcpInstance.closeTicket(ticketId)) {
                    System.out.println("Ticket closed successfully. Thank you!");
                } else {
                    System.out.println("Invalid Ticket ID. Please try again.");
                }
            } else if(userInput == 1) {
                System.out.println("Welcome to the Multi-Level Car Parking System!");
                System.out.println("Can you share your vehicle type (TRUCK/CAR/BIKE)  for truck press 1 or if it's car enter 2 or if it's bike enter 3?");
                int vehicleTypeInput = sc.nextInt();
                System.out.println("Enter the vehicle number:");
                String vehicleNumber = sc.next();
                VehicleType vehicleType = VehicleType.getVehicleType(vehicleTypeInput);
                try {
                    Map<String, Object> map = mlcpInstance.issueTicket(vehicleType, vehicleNumber);
                    System.out.println("Ticket issued successfully. You can park your vehicle now and the ticket ID is " + map.get(TicketsTableConstants.TICKET_ID) + " and the slot Number is " + map.get(TicketsTableConstants.SLOT_NUMBER) + " and the floor number is " + map.get(TicketsTableConstants.FLOOR_NUMBER));
                } catch (Exception e) {
                    System.out.println("Sorry, no available slots for your vehicle type.");
                }
            }
        }
    }

    public static void collectPaymentInfo(int ticketId) throws Exception {
        Map<String, Object> ticketRow = TicketDao.fetchRow(ticketId);
        long parkedDurationInMinutes = (System.currentTimeMillis() - Long.parseLong(ticketRow.get(TicketsTableConstants.CREATION_TIME).toString())) / 60000;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Payment Method: 1. Credit Card 2. Debit Card 3. UPI");
        int paymentMethod = sc.nextInt();
        PaymentInterface paymentProcessor = PaymentFactory.getPaymentInstance(paymentMethod);
        int floorNo = VehicleType.fetchFlorNo(ticketRow.get(TicketsTableConstants.VEHICLE_TYPE).toString());
        long amount = paymentProcessor.calculatePayment(parkedDurationInMinutes, floorNo, PaymentType.getPaymentType(paymentMethod));
        System.out.println("Payment of amount " + amount + " processed successfully.");
    }
}