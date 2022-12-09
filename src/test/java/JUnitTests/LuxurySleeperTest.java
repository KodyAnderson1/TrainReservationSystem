package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import constants.ETicketInfo;
import logic.model.seats.LuxurySleeperSeat;

class LuxurySleeperTest {

	static double baseTicketPrice = ETicketInfo.baseTicketPrice.price;
	static double sleeperPercentageIncrease = ETicketInfo.sleeperPercentageIncrease.price;
	static double LuxuryPercentageIncrease = ETicketInfo.LuxuryPercentageIncrease.price;
	final static double DELTA = 0.001;
	
	@Test
	void defaultETicketInfo() {
		assertEquals(baseTicketPrice, 89.99, DELTA);
		assertEquals(sleeperPercentageIncrease, .30, DELTA);
		assertEquals(LuxuryPercentageIncrease, .65, DELTA);
	}
	
	@Test
	void testTicketPrice() {
		LuxurySleeperSeat hs = new LuxurySleeperSeat();
		double luxPrice = baseTicketPrice + (baseTicketPrice * ETicketInfo.sleeperPercentageIncrease.price) 
				+ (baseTicketPrice * ETicketInfo.LuxuryPercentageIncrease.price);
		assertEquals(hs.getTicketPrice(), luxPrice, DELTA);
		
		hs.setTicketPrice(899.99);
		assertFalse(hs.getTicketPrice() == baseTicketPrice);
		assertEquals(baseTicketPrice, 89.99, DELTA); // Checking to see if enum changed (Shouldn't)
	}
	
	@Test
	void testIsSleeper() {
		LuxurySleeperSeat hs = new LuxurySleeperSeat();
		assertEquals(hs.getIsSleeper(), true);
	}
	
	@Test
	void testIsExpress() {
		LuxurySleeperSeat hs = new LuxurySleeperSeat();
		assertEquals(hs.getCanBeExpressTicket(), false);
	}
	
	@Test
	void testIsBooked() {
		LuxurySleeperSeat hs = new LuxurySleeperSeat();
		
		hs.setIsBooked(true);
		assertTrue(hs.getIsBooked());
		
		hs.setIsBooked(false);
		assertFalse(hs.getIsBooked());
	}

}
