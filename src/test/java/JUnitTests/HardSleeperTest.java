package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import constants.ETicketInfo;
import logic.model.seats.HardSleeperSeat;

class HardSleeperTest {

	static double baseTicketPrice = ETicketInfo.baseTicketPrice.price;
	static double sleeperPercentageIncrease = ETicketInfo.sleeperPercentageIncrease.price;
	final static double DELTA = 0.001;
	
	@Test
	void defaultETicketInfo() {
		assertEquals(baseTicketPrice, 89.99, DELTA);
		assertEquals(sleeperPercentageIncrease, .30, DELTA);
	}
	
	@Test
	void testTicketPrice() {
		HardSleeperSeat hs = new HardSleeperSeat();
		double sleeperPrice = baseTicketPrice + (baseTicketPrice * sleeperPercentageIncrease);
		assertEquals(hs.getTicketPrice(), sleeperPrice, DELTA);
		
		hs.setTicketPrice(39.99);
		sleeperPrice = 39.99 + (39.99 * sleeperPercentageIncrease);
		assertTrue(hs.getTicketPrice() == sleeperPrice);
		assertEquals(baseTicketPrice, 89.99, DELTA); // Checking to see if enum changed (Shouldn't)
	}
	
	@Test
	void testIsSleeper() {
		HardSleeperSeat hs = new HardSleeperSeat();
		assertEquals(hs.getIsSleeper(), true);
	}
	
	@Test
	void testIsExpress() {
		HardSleeperSeat hs = new HardSleeperSeat();
		assertEquals(hs.getCanBeExpressTicket(), false);
	}

}
