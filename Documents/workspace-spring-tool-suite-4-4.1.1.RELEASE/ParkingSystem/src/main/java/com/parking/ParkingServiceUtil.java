package com.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingServiceUtil implements ParkingService {
	private Integer parkingCapacity = 2;

	private ArrayList<ParkingSlot> parkingSlots = new ArrayList<>();

	private ArrayList<Ticket> tickets = new ArrayList<>();

	ParkingSlot slot = null;

	public String exitVehicle(String v_num) {
		String msg = "Enter valid vehicle number.";
		for (int i = 0; i < tickets.size(); i++) {
			if (tickets.get(i).getVehicleNum().equalsIgnoreCase(v_num)) {
				tickets.get(i).setStatus(false);
				parkingSlots.set(tickets.get(i).getSlotNumer(), new ParkingSlot(tickets.get(i).getSlotNumer(), true));
				msg = "Thank you..! Visit Again.";
			}
		}
		return msg;
	}

	public Integer serchVehicle(String v_num) {
		int slotnum = -1;
		for (Ticket t : tickets) {
			if (t.getVehicleNum().equalsIgnoreCase(v_num)) {
				slotnum = t.getSlotNumer();
			}
		}
		return slotnum;
	}

	public List<Integer> getAvailableParkingSlots() {
		ArrayList<Integer> availableSlots = new ArrayList<>();
		for (int i = 0; i < parkingCapacity; i++) {
			ParkingSlot slot = parkingSlots.get(i);
			if (slot.getAvailability() == true) {
				availableSlots.add(slot.getSlotNo());
			}
		}
		return availableSlots;
	}

	public Ticket BookSlot(String vehicleNum) {
		ParkingSlot slot = checkAvailibility();
		Ticket issueTicket = null;
		if (slot != null) {
			issueTicket = new Ticket(slot.getSlotNo(), vehicleNum, true);
			tickets.add(issueTicket);
			slot.setAvailability(false);
			return issueTicket;
		} else {
			System.out.println("Parking slot is not available.");
		}
		return issueTicket;
	}

	public ParkingSlot checkAvailibility() {
		slot = null;
		if (parkingSlots.size() == 0) {
			for (int i = 0; i < parkingCapacity; i++) {
				ParkingSlot slot = new ParkingSlot(i, true);
				parkingSlots.add(slot);
			}
			slot = parkingSlots.get(0);
		} else if (parkingSlots.size() < parkingCapacity) {
			for (int i = 0; i < parkingCapacity; i++) {
				slot = parkingSlots.get(i);
				if (slot.getAvailability() == true) {
					return slot;
				}
			}
		}
		return slot;
	}

}
