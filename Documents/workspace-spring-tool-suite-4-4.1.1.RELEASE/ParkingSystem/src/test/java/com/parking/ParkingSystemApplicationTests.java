package com.parking;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ArrayList.class, ParkingServiceUtil.class, Ticket.class })
class ParkingSystemApplicationTests {

	private static final String VEHICLE_NUMBER = "MH 12 AB 1234";

	@InjectMocks
	private ParkingServiceUtil classUnderTest;

	@Mock
	private ArrayList<ParkingSlot> parkingSlots;

	@Mock
	private ArrayList<Ticket> tickets;

	@Mock
	private ParkingSlot slots;

	@Mock
	private Ticket ticket;

	@BeforeEach
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		PowerMockito.whenNew(ArrayList.class).withArguments(any()).thenReturn(parkingSlots).thenReturn(tickets);
		PowerMockito.whenNew(ParkingSlot.class).withArguments(Integer.class, Boolean.class).thenReturn(slots);

		when(parkingSlots.get(0)).thenReturn(slots);
	}

	@Test
	public void testBookSlotWhenParkingIsEmpty() {
		when(parkingSlots.size()).thenReturn(0);
		Ticket t = classUnderTest.BookSlot(VEHICLE_NUMBER);

		assertNotNull(t.getSlotNumer());
	}

	@Test
	public void testBookSlotWhenParkingIsNotFull() {
		when(parkingSlots.size()).thenReturn(4);
		when(slots.getAvailability()).thenReturn(true);

		Ticket t = classUnderTest.BookSlot(VEHICLE_NUMBER);

		assertNotNull(t.getSlotNumer());
	}

	@Test
	public void testBookSlotWhenParkingIsFull() {
		when(parkingSlots.size()).thenReturn(10);
		when(slots.getAvailability()).thenReturn(false);

		Ticket t = classUnderTest.BookSlot(VEHICLE_NUMBER);

		assertNull(t);
	}

	@Test
	public void testExitVehicleEntryWhenValidVehicleNumberExpectThankYouMessage() {
		when(tickets.size()).thenReturn(5);
		when(tickets.get(anyInt())).thenReturn(ticket);
		when(ticket.getVehicleNum()).thenReturn(VEHICLE_NUMBER);

		String result = classUnderTest.exitVehicle(VEHICLE_NUMBER);

		verify(tickets, times(6)).size();
		verify(ticket, times(5)).getVehicleNum();
		assertEquals("Thank you..! Visit Again.", result);
	}

	@Test
	public void testExitVehicleEntryWhenInValidVehicleNumberExpectReEnterVehicleNumberMessage() {
		when(tickets.size()).thenReturn(2);
		when(tickets.get(anyInt())).thenReturn(ticket);
		when(ticket.getVehicleNum()).thenReturn("");

		String result = classUnderTest.exitVehicle(VEHICLE_NUMBER);

		verify(tickets, times(3)).size();
		verify(tickets, times(2)).get(anyInt());
		verify(ticket, times(2)).getVehicleNum();
		assertEquals("Enter valid vehicle number.", result);
	}

}
