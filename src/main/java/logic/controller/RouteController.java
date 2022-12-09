package logic.controller;

import java.util.ArrayList;

import constants.TrainStops;
import logic.model.Route;

/**
 * Singleton Class - Call outside this class with getInstance()
 */
public class RouteController {
	private static RouteController obj = new RouteController();
	private ArrayList<Route> routes;
	private ArrayList<String> trainStations;
	private ConvertDataToFile saveToFile;
	
	public static RouteController getInstance() { return obj; }
	
	private RouteController() {
		saveToFile = new ConvertDataToFile();
		routes = GUIController.getInstance().getListOfRoutes();
		trainStations = new ArrayList<>();
		
		trainStations.add(TrainStops.CHIC.stops);
		trainStations.add(TrainStops.NOLA.stops);
		trainStations.add(TrainStops.DC.stops);
		
	}
	
	public void saveRoutesToFile() {
		saveToFile.recordAllToFile(routes);
	}
	
	/**
	 * 
	 * @param index of route in ArrayList
	 * @param id - Id of Route
	 * @param name - Route name
	 * @param depart - Departure Station of route
	 * @param destination - Destination station of route
	 */
	public boolean changeExistingRoute(int index, String id, String name, String depart, String destination) {
		Route route = routes.get(index);
		route.setRouteID(id);
		route.setDepartureStation(depart);
		route.setArrivalStation(destination);
		route.setNameOfRoute(name);
		return true;
	}
	
	/**
	 * 
	 * @param id - Id of Route
	 * @param name - Route name
	 * @param depart - Departure Station of route
	 * @param dest  - Destination station of route
	 * @return
	 */
	public boolean addRoute(String id, String name, String depart, String dest) {
		if(id.isEmpty()) return false;
		if(name.isEmpty()) return false;
		if(checkIfRouteExists(id)) return false;
		routes.add(new Route(id, name, depart, dest));
		return true;
	}
	
	public boolean removeRouteByIndex(int index) {
		if(routes.remove(index) == null) return false;
		return true;
	}
	
	public boolean checkIfRouteExists(String id) {
		for(Route ro : routes)
			if(ro.getRouteID().equalsIgnoreCase(id)) 
				return true;
		return false;
	}
	
	public String getRouteInfoForPanel() {
		String formattedReturnString = "";
		
		for(Route route : routes) {
			formattedReturnString+= String.format("Route ID: %s%nDeparture: %s%nDestination: %s%n%n", 
										route.getRouteID(), route.getDepartureStation(), route.getArrivalStation());
		}
		return formattedReturnString;
	}
	
	/**
	 * 
	 * @return Ex. => 03, NOLA to DC
	 */
	public String[] getListOfRouteNames() {
		String[] listOfRouteNames = new String[routes.size()];
		for(int i = 0; i < listOfRouteNames.length; i++) {
			listOfRouteNames[i] = String.format("%s, %s", routes.get(i).getRouteID(), routes.get(i).getNameOfRoute());
		}
		return listOfRouteNames;
	}
	
	/**
	 * 
	 * @param index of route in list
	 * @return Ex. => 03, NOLA to DC
	 */
	public String getRouteNameAtIndex(int index) {
		return String.format("%s, %s", routes.get(index).getRouteID(), routes.get(index).getNameOfRoute());
	}
	
	public Route getSpecificRoute(int index) {
		if(index >= routes.size() || index < 0) return null;
		return routes.get(index);
		
	}
	
	public ArrayList<String> getTrainStations() { return trainStations; }
	
	
}
