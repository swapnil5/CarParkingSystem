package com.parking;

public class Ticket {

	private Integer slotNumer;
	
	private String vehicleNum;
	
	private Boolean status;
	
	public Ticket(Integer slotNumer, String vechicleNumer, Boolean status) {
		super();
		this.slotNumer = slotNumer;
		this.vehicleNum = vechicleNumer;
		this.status =  status;
	}
	
	
	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	public String getVehicleNum() {
		return vehicleNum;
	}

	public void setVehicleNum(String vehicleNum) {
		this.vehicleNum = vehicleNum;
	}

	

	public Integer getSlotNumer() {
		return slotNumer;
	}

	public void setSlotNumer(Integer slotNumer) {
		this.slotNumer = slotNumer;
	}
	
}
