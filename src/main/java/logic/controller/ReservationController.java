package logic.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import constants.FilePaths;
import logic.model.Route;
import logic.users.UserReservation;
/**
 * This class reads in reservations and puts to array to be used
 * This class also writes to reservations files
 */
public class ReservationController {
	private static ReservationController controllerObject = new ReservationController();
	private ArrayList<UserReservation> reservationList;
	private final String RESERVATIONS_FILE = FilePaths.RESERVATIONS.toString();
	private FileIO fileIO = new FileIO();

	public static ReservationController getInstance() { return controllerObject; }

	private ReservationController() {
		reservationList = new ArrayList<>();
		populateList();
	}

	public void setReservationList(ArrayList<UserReservation> list) {
		Collections.copy(reservationList, list);
	}
	
	public ArrayList<UserReservation> getreservationList() { 
		return this.reservationList;
	}

	public String getUsersFromFile() {
		return fileIO.readInEntireFile(RESERVATIONS_FILE);
	}
	/**
	 * This method populates the array list
	 */
	private void populateList() {
		String[] usersSplit = getUsersFromFile().split("\n");
		for(int i = 0; i < usersSplit.length; i++) {
			reservationList.add(createReservation(usersSplit[i].split(",")));	
		}
	}

	
	/*
	 * This method create the reservation to put into populate list
	 * [0] = userName
	 * [1] = reservationID => ex. 110E3H2L@R01#0H0
	 * [2] = cost of reservation
	 */
	public UserReservation createReservation (String[] newRes) {
		String reservationID = newRes[1];
		UserReservation newBook = new UserReservation();
		newBook.setUserName(newRes[0]);
		newBook.setCost(Double.parseDouble(newRes[2]));
		newBook.setExpress(parseIsExpress(reservationID));
		newBook.setSeat(findSeat(reservationID));
		newBook.setReservationID(reservationID);
		newBook.setDeparture(addResDepature(reservationID));
		newBook.setTripDestination(addResDestination(reservationID));
		newBook.setDepartTime(newRes[3]);
		return newBook;
	}
	
	public void addReservationToList (String[] newString) {
		reservationList.add(createReservation(newString));
	}
	
	public boolean parseIsExpress(String newString) {
		return (newString.contains("E")) ? true : false;
	}
	
	public String findSeat(String newString) {
		String searchString = newString.substring(newString.indexOf('#') + 1);
		if(searchString.contains("H")) return "HardSeatCar";
		if(searchString.contains("S")) return "HardSleeperCar";
		if(searchString.contains("L")) return "LuxurySeatCar";
		if(searchString.contains("X")) return "LuxurySleeperCar";
		return "Error";
	}
	
	/*
	 * Searches for the route ID finds it, and returns the destination station
	 */
	public String addResDestination (String newString) {
		String RouteID = newString.substring(newString.indexOf("@") + 1, newString.indexOf("#"));
		
		final ArrayList<Route> routesList = GUIController.getInstance().getListOfRoutes();
		for (Route r : routesList) 
			if (newString.contains(RouteID)) 
				return r.getArrivalStation();
		
		return "Error Destination";
	}
	
	/*
	 * Searches for the route ID finds it, and returns the departure station
	 */
	public String addResDepature (String newString) {
		String RouteID = newString.substring(newString.indexOf("@") + 1, newString.indexOf("#"));
		
		final ArrayList<Route> routesList = GUIController.getInstance().getListOfRoutes();
		for (Route r : routesList) 
			if (newString.contains(RouteID)) 
				return r.getDepartureStation();
		
		return "Error Arrival";
	}
	
	// Need to call this somewhere 
	public void saveReservationsToFile() {
		StringBuilder txtToSave = new StringBuilder();

		for(UserReservation user : reservationList)
			txtToSave.append(String.format("%s,%s,%.2f,%s%n", user.getUserName(), user.getReservationID(), user.getCost(), user.getTime()));
			
		fileIO.writeToFile(RESERVATIONS_FILE, txtToSave.toString()); 
	}

	// etc... methods as needed
}