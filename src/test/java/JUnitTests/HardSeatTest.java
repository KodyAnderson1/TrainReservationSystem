package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import constants.ETicketInfo;
import logic.model.seats.HardSeat;

@DisplayName("Tests for the HardSeat Class")
class HardSeatTest {
	
	static double baseTicketPrice = ETicketInfo.baseTicketPrice.price;
	final static double DELTA = 0.001;
	
	@Test
	void defaultETicketInfo() {
		assertEquals(baseTicketPrice, 89.99, DELTA);
	}
	
	@Test
	void testTicketPrice() {
		HardSeat hs = new HardSeat();
		assertEquals(hs.getTicketPrice(), baseTicketPrice, DELTA);
		
		hs.setTicketPrice(39.99);
		assertFalse(hs.getTicketPrice() == baseTicketPrice);
		assertEquals(baseTicketPrice, 89.99, DELTA);
	}
	
	@Test
	void testIsSleeper() {
		HardSeat hs = new HardSeat();
		assertEquals(hs.getIsSleeper(), false);
	}
	
	@Test
	void testIsExpress() {
		HardSeat hs = new HardSeat();
		assertEquals(hs.getCanBeExpressTicket(), true);
	}
	
	@Test
	void testIsBooked() {
		HardSeat hs = new HardSeat();
		
		hs.setIsBooked(true);
		assertTrue(hs.getIsBooked());
		
		hs.setIsBooked(false);
		assertFalse(hs.getIsBooked());
	}

}
