package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import logic.controller.ConvertDataToFile;
import logic.model.Route;
import logic.model.Train;
import logic.model.traincars.HardSeatCar;
import logic.model.traincars.LuxurySeatCar;

import org.junit.jupiter.api.Test;

class ConvertDataToFileTests {

	@Test
	void ConvertDataToFile() {
		ConvertDataToFile test = new ConvertDataToFile();
		Train train = new Train("580");
		train.setIsExpressTrain(true);
		train.addMultipleCarsToTrain(new HardSeatCar(), new LuxurySeatCar(), new HardSeatCar(), new LuxurySeatCar());
		assertTrue(test.trainData(train).equalsIgnoreCase("580E2H2L@"));
	}
	
	@Test
	void getSpecificSeatID() {
		ConvertDataToFile test = new ConvertDataToFile();
		Train train = new Train("580");
		train.setIsExpressTrain(true);
		train.addMultipleCarsToTrain(new HardSeatCar(), new LuxurySeatCar(), new HardSeatCar(), new LuxurySeatCar());
		
		assertTrue(test.getSpecificSeatID("HardSeatCar", 0, 0).equalsIgnoreCase("0H0"));
		assertTrue(test.getSpecificSeatID("LuxurySeatCar", 5, 5).equalsIgnoreCase("5L5"));
		assertTrue(test.getSpecificSeatID("LuxurySleeperCar", 1, 3).equalsIgnoreCase("1X3"));
	}
	
	@Test
	void getAllBookedSeats() {
		ConvertDataToFile test = new ConvertDataToFile();
		Train train = new Train("580");
		train.setIsExpressTrain(true);
		train.addCarToTrain(new HardSeatCar());
		
		for(int i = 0; i < 5; i++)
			train.bookASeat(0, i);

		assertTrue(test.getAllBookedSeats(train).equalsIgnoreCase("0H0,0H1,0H2,0H3,0H4"));
	}
	
	@Test
	void getRouteData() {
		ConvertDataToFile test = new ConvertDataToFile();
		ArrayList<Route> routeList = new ArrayList<>();
		
		Route r1 = new Route("05");
		Route r2 = new Route("06");
		Route r3 = new Route("07");
		routeList.add(r1);
		routeList.add(r2);
		routeList.add(r3);
		
		assertFalse(test.getRouteData(routeList).equalsIgnoreCase("R05,Default Route Name\n"));
		
		
	}

}
