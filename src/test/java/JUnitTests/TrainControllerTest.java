package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import logic.controller.TrainAdminController;

class TrainControllerTest {

	@Test
	void testCheckIfTrainIdIsAvailable() {
		TrainAdminController tc = TrainAdminController.getInstance();
		
		assertTrue(tc.checkIfTrainIdIsAvailable("001"));
		assertTrue(tc.checkIfTrainIdIsAvailable("500"));
		assertTrue(tc.checkIfTrainIdIsAvailable("750"));
		assertFalse(tc.checkIfTrainIdIsAvailable("100"));
		assertFalse(tc.checkIfTrainIdIsAvailable("101"));
	}
	
	@Test
	void testGetCarTypes() {
		TrainAdminController tc = TrainAdminController.getInstance();
		String[] arr = tc.getCarTypes();
		
		assertTrue(arr[0].equalsIgnoreCase("HardSeatCar"));
		assertTrue(arr[1].equalsIgnoreCase("HardSleeperCar"));
		assertTrue(arr[2].equalsIgnoreCase("LuxurySeatCar"));
		assertTrue(arr[3].equalsIgnoreCase("LuxurySleeperCar"));
	}
	
	@Test
	void testGetTrainAndRoute() {
		TrainAdminController tc = TrainAdminController.getInstance();
		ArrayList<String> arr = tc.getTrainAndRoute();

		assertTrue(arr.get(0).equalsIgnoreCase("110, NOLA to DC"));
		assertTrue(arr.get(2).equalsIgnoreCase("112, NOLA to DC"));
		assertTrue(arr.get(4).equalsIgnoreCase("100, NOLA To Chicago"));
	}
	
	@Test
	void testGetDetailsOfTrainById() {
		TrainAdminController tc = TrainAdminController.getInstance();
		
		assertTrue(tc.getDetailsOfTrainById("100").equalsIgnoreCase("100, NOLA To Chicago"));
		assertTrue(tc.getDetailsOfTrainById("101").equalsIgnoreCase("101, NOLA To Chicago"));
		assertTrue(tc.getDetailsOfTrainById("150").equalsIgnoreCase("150, NOLA to DC"));	
	}
	
	@Test
	void testGetNumberOfTrainsOnRoute() {
		TrainAdminController tc = TrainAdminController.getInstance();
		
		assertTrue(tc.getNumberOfTrainsOnRoute(0) == 4);
		assertTrue(tc.getNumberOfTrainsOnRoute(1) == 3);
		assertTrue(tc.getNumberOfTrainsOnRoute(2) == 3);
		
		assertTrue(tc.getNumberOfTrainsOnRoute(3) == -1);
		assertTrue(tc.getNumberOfTrainsOnRoute(4) == -1);
		
	}
	
	@Test
	void testGetNumberOfRoutes() {
		TrainAdminController tc = TrainAdminController.getInstance();
		
		assertTrue(tc.getNumberOfRoutes() == 3);
		assertFalse(tc.getNumberOfRoutes() == 2);
		assertFalse(tc.getNumberOfRoutes() == 5);
	}
	
	@Test
	void testGetTrainToString() {
		TrainAdminController tc = TrainAdminController.getInstance();
		System.out.println(tc.getTrainToString("100"));
		assertTrue(tc.getTrainToString("100").contains("6 car(s)"));
		assertTrue(tc.getTrainToString("101").contains("4 car(s)"));
		assertTrue(tc.getTrainToString("150").contains("7 car(s)"));
	}
	

}
