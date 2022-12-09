package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import logic.controller.RouteController;

class RouteControllerTests {

	@Test
	void testGetTrainStations() {
		RouteController rc = RouteController.getInstance();
		ArrayList<String> trainstops = rc.getTrainStations();

		assertTrue(trainstops.get(0).equalsIgnoreCase("Chicago"));
		assertTrue(trainstops.get(1).equalsIgnoreCase("New Orleans"));
		assertTrue(trainstops.get(2).equalsIgnoreCase("Washington D.C."));
	}

	@Test
	void testGetListOfRouteNames() {
		RouteController rc = RouteController.getInstance();
		String[] test = rc.getListOfRouteNames();

		assertTrue(test[0].equalsIgnoreCase("01, NOLA to DC"));
		assertTrue(test[1].equalsIgnoreCase("02, NOLA To Chicago"));
		assertTrue(test[2].equalsIgnoreCase("03, DC to Chicago"));
	}
	
	@Test
	void testCheckIfRouteExists() {
		RouteController rc = RouteController.getInstance();
		boolean test = rc.checkIfRouteExists("01");
		boolean testFail = rc.checkIfRouteExists("10");
		
		assertTrue(test);
		assertFalse(testFail);
		assertTrue(rc.getRouteInfoForPanel().contains("Departure: New Orleans"));
	}
	
	@Test
	void testGetRouteInfoForPanel() {
		RouteController rc = RouteController.getInstance();

		assertTrue(rc.getRouteInfoForPanel().contains("Departure: New Orleans"));
		assertTrue(rc.getRouteInfoForPanel().contains("Route ID: 01"));
		assertTrue(rc.getRouteInfoForPanel().contains("Route ID: 02"));
		assertTrue(rc.getRouteInfoForPanel().contains("Route ID: 03"));
	}
	
	@Test
	void testGetRouteNameAtIndex() {
		RouteController rc = RouteController.getInstance();
		
		assertTrue(rc.getRouteNameAtIndex(0).equalsIgnoreCase("01, NOLA to DC"));
		assertTrue(rc.getRouteNameAtIndex(1).equalsIgnoreCase("02, NOLA To Chicago"));
		assertTrue(rc.getRouteNameAtIndex(2).equalsIgnoreCase("03, DC to Chicago"));
	}
	
	@Test
	void testAddRoute() {
		RouteController rc = RouteController.getInstance();
		
		assertNull(rc.getSpecificRoute(3));
		rc.addRoute("R55", "TEST ROUTE", "TEST DEPART", "TEST DEST");
		assertNotNull(rc.getSpecificRoute(3));
		rc.removeRouteByIndex(3);
	}
	
	@Test
	void testChangeExistingRoute() {
		RouteController rc = RouteController.getInstance();
		assertNull(rc.getSpecificRoute(3));
		rc.addRoute("R55", "TEST ROUTE", "TEST DEPART", "TEST DEST");
		assertNotNull(rc.getSpecificRoute(3));
		
		rc.changeExistingRoute(3, "R500", "TEST CHANGE", "TEST DEP!", "TEST DEST!");
		assertTrue(rc.getSpecificRoute(3).getRouteID().equalsIgnoreCase("R500"));
		assertTrue(rc.getSpecificRoute(3).getNameOfRoute().equalsIgnoreCase("TEST CHANGE"));
		assertTrue(rc.getSpecificRoute(3).getDepartureStation().equalsIgnoreCase("TEST DEP!"));
		assertTrue(rc.getSpecificRoute(3).getArrivalStation().equalsIgnoreCase("TEST DEST!"));
		rc.removeRouteByIndex(3);
	}

}
