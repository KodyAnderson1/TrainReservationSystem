package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import constants.ETicketInfo;
import logic.model.seats.LuxurySeat;

class LuxurySeatTest {

	static double baseTicketPrice = ETicketInfo.baseTicketPrice.price;
	static double LuxuryPercentageIncrease = ETicketInfo.LuxuryPercentageIncrease.price;
	final static double DELTA = 0.001;
	
	@Test
	void defaultETicketInfo() {
		assertEquals(baseTicketPrice, 89.99, DELTA);
		assertEquals(LuxuryPercentageIncrease, .65, DELTA);
	}
	
	@Test
	void testTicketPrice() {
		LuxurySeat ls = new LuxurySeat();
		double luxuryPrice = baseTicketPrice + (baseTicketPrice * LuxuryPercentageIncrease);
		assertEquals(ls.getTicketPrice(), luxuryPrice, DELTA);
		
		ls.setTicketPrice(9999.99);
		assertFalse(ls.getTicketPrice() == baseTicketPrice);
	}
	
	@Test
	void testIsSleeper() {
		LuxurySeat ls = new LuxurySeat();
		assertEquals(ls.getIsSleeper(), false);
	}
	
	@Test
	void testIsExpress() {
		LuxurySeat ls = new LuxurySeat();
		assertEquals(ls.getCanBeExpressTicket(), true);
	}
}
