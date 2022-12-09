package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import logic.controller.*;

public class ReservationControllerTest {

	
	
	
	
	@Test	
	void testSeatInfo () {
		ReservationController res = ReservationController.getInstance();
		String inputSeatH = "H";
		String inputSeatS = "S";
		String inputSeatL = "L";
		String inputSeatX = "X";
		String outputSeatH = "HardSeatCar";
		String outputSeatS = "HardSleeperCar";
		String outputSeatL = "LuxurySeatCar";
		String outputSeatX = "LuxurySleeperCar";
				
		assertTrue(res.findSeat(inputSeatH).equalsIgnoreCase(outputSeatH));
		assertTrue(res.findSeat(inputSeatS).equalsIgnoreCase(outputSeatS));
		assertTrue(res.findSeat(inputSeatL).equalsIgnoreCase(outputSeatL));
		assertTrue(res.findSeat(inputSeatX).equalsIgnoreCase(outputSeatX));
	}
	
	
}
