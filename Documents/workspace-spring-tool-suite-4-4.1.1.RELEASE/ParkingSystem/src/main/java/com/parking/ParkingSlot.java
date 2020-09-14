package com.parking;

public class ParkingSlot {

	private Integer slotNo;
	
	private Boolean availability;
	

	public ParkingSlot(Integer slotNo, Boolean availability) {
		super();
		this.slotNo = slotNo;
		this.availability = availability;
	}

	public Integer getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(Integer slotNo) {
		this.slotNo = slotNo;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "ParkingSlot [slotNo=" + slotNo + "]";
	}
}
