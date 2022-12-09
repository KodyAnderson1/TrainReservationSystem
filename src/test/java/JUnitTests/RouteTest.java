package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import logic.model.Route;
import logic.model.Train;

class RouteTest {

	@Test
	void defaultConstructorTest() {
		Route testRoute = new Route("1");
		
		assertTrue(testRoute.getNameOfRoute().equalsIgnoreCase("Default Route Name"));
		assertTrue(testRoute.getRouteID().equalsIgnoreCase("1"));
		assertTrue(testRoute.getNumOfTrainsInRoute() == 0);
	}
	
	@Test
	void paramConstructorTest() {
		Route testRoute = new Route("ab4s");
		
		assertTrue(testRoute.getNameOfRoute().equalsIgnoreCase("Default Route Name"));
		assertTrue(testRoute.getRouteID().equalsIgnoreCase("ab4s"));
		assertTrue(testRoute.getNumOfTrainsInRoute() == 0);
	}
	
	@Test
	void addTrainToRouteTest() {
		Route testRoute = new Route("1");
		Route multipleTrainsAddedRoute = new Route("2");
		Train train1 = new Train();
		Train train2 = new Train();
		Train train3 = new Train();
		
		assertTrue(testRoute.addTrainToRoute(new Train()));
		assertTrue(testRoute.getNumOfTrainsInRoute() == 1);
		
		assertTrue(multipleTrainsAddedRoute.addMultipleTrainsToRoute(train1, train2, train3));
		assertTrue(multipleTrainsAddedRoute.getNumOfTrainsInRoute() == 3);
	}

}
