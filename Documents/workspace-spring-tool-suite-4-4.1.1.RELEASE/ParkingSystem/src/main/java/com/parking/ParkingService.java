package com.parking;

import java.util.List;

public interface ParkingService {
	
	public String exitVehicle(String v_num) ;

	public Integer serchVehicle(String v_num) ;

	public List<Integer> getAvailableParkingSlots() ;

	public Ticket BookSlot(String vehicleNum) ;

	public ParkingSlot checkAvailibility() ;
}
