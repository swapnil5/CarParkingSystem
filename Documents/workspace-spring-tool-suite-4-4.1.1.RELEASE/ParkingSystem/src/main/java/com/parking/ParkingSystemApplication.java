package com.parking;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Run this application as Java Application.
 * @author Admin
 */
@SpringBootApplication
public class ParkingSystemApplication {
	
	public static void main(String[] args) {
		
		ParkingServiceUtil util = new ParkingServiceUtil();
		
		while (true) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.println(
					"Enter Your choice. \n 1- Entry. \n 2- check available slots \n 3- Vehicle Serch \n 4- Exit");

			String choice = scan.nextLine();

			if (choice.equalsIgnoreCase("1")) {
				System.out.println("Enter vehicle numer.");
				String v_num = scan.next();
				Ticket ticket = util.BookSlot(v_num);
				if (ticket != null) {
					System.out.println("Park Your vehicke on slot number :" + ticket.getSlotNumer());
				}
			} else if (choice.equalsIgnoreCase("2")) {
				System.out.println("Available free slots: " + util.getAvailableParkingSlots());
			} else if (choice.equalsIgnoreCase("3")) {
				System.out.println("Enter vehicle numer.");
				String v_num = scan.next();
				int slotn = util.serchVehicle(v_num);
				if (slotn > 0) {
					System.out.println("Your vehicle at :" + slotn);
				} else {
					System.out.println("Sorry vehicle found...");
				}
			} else if (choice.equalsIgnoreCase("4")) {
				System.out.println("Enter vehicle numer.");
				String v_num = scan.next();
				System.out.println(util.exitVehicle(v_num));
			}
			
		}
	}

	/*
	 * private String exitVehicle(String v_num) { ParkingSlot slot = new
	 * ParkingSlot(null, true); String msg = "Enter valid vehicle number."; for (int
	 * i = 0; i < tickets.size(); i++) { if
	 * (tickets.get(i).getVehicleNum().equalsIgnoreCase(v_num)) {
	 * tickets.get(i).setStatus(false);
	 * parkingSlots.set(tickets.get(i).getSlotNumer(), slot); msg =
	 * "Thank you..! Visit Again."; } } return msg; }
	 * 
	 * private Integer serchVehicle(String v_num) { int slotnum = -1; for (Ticket t
	 * : tickets) { if (t.getVehicleNum().equalsIgnoreCase(v_num)) { slotnum =
	 * t.getSlotNumer(); } } return slotnum; }
	 * 
	 * private List<Integer> getAvailableParkingSlots() { ArrayList<Integer>
	 * availableSlots = new ArrayList<>(); for (int i = 0; i < parkingCapacity; i++)
	 * { ParkingSlot slot = parkingSlots.get(i); if (slot.getAvailability() == true)
	 * { availableSlots.add(slot.getSlotNo()); } } return availableSlots; }
	 * 
	 * public Ticket BookSlot(String vehicleNum) { ParkingSlot slot =
	 * checkAvailibility(); Ticket issueTicket = null; if (slot != null) {
	 * issueTicket = new Ticket(slot.getSlotNo(), vehicleNum, true);
	 * tickets.add(issueTicket); slot.setAvailability(false); return issueTicket; }
	 * else { System.out.println("No Parking slot available."); } return
	 * issueTicket; }
	 * 
	 * private ParkingSlot checkAvailibility() { if (parkingSlots.size() == 0) { for
	 * (int i = 0; i < parkingCapacity; i++) { ParkingSlot slot = new ParkingSlot(i,
	 * true); parkingSlots.add(slot); } slot = parkingSlots.get(0); } else { for
	 * (int i = 0; i < parkingCapacity; i++) { slot = parkingSlots.get(i); // check
	 * availability if (slot.getAvailability() == true) { return slot; } } } return
	 * slot; }
	 */
}
