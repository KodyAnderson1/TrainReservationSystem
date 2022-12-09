package logic.controller;

import java.util.ArrayList;

import constants.Amenities;
import constants.ETicketInfo;
import constants.TrainMiles;
import constants.TrainStops;
import logic.model.seats.HardSeat;
import logic.model.seats.HardSleeperSeat;
import logic.model.seats.LuxurySeat;
import logic.model.seats.LuxurySleeperSeat;
import logic.model.seats.Seat;

/**
 * Singleton Class - access outside class with 
 * 		ShoppingCart variableName = ShoppingCart.getInstance();
 */
public class ShoppingCart {
	private static ShoppingCart cart = new ShoppingCart();
	GUIController controller;
	private final String NOLA = TrainStops.NOLA.stops;
	private final String DC = TrainStops.DC.stops;
	private final String CHIC = TrainStops.CHIC.stops;
	private String departStation;
	private String destination;
	private boolean isExpress;
	private boolean membership;
	private boolean insurance;
	private double total;
	private double seatPrice;
	private String time;
	private Seat seatType;
	private String ReservationID;
	private double totalMiles;
	private String userName;
	private boolean hasSelectedSeat;
	private ArrayList<Amenities> amenitiesList;
	private ArrayList<TrainMiles> miscList;
	
	private ShoppingCart(){
		amenitiesList = new ArrayList<>();
		miscList = new ArrayList<>();
		seatType = null;
		controller = GUIController.getInstance();
		setDepartStation("");
		setDestination("");
		setTotal(0);
		setReservationID("");
	}
	
	public static ShoppingCart getInstance() { return cart; }
	
	public void setSeatInfo(Seat seat) {
		setSeatType(seat);
		setSeatPrice(seat.getTicketPrice());
	}
	
	
	public boolean getHasSelectedSeat() {
		return hasSelectedSeat;
	}

	public void setHasSelectedSeat(boolean hasSelectedSeat) {
		this.hasSelectedSeat = hasSelectedSeat;
	}
	
	
	public void resetTotal() { this.total = 0; }
	public void addToTotal(double monies) { this.total += monies; } 
	private void setTotal(double monies) { this.total = monies; }
	public double getTotal() { return this.total; }
	
	public void calculateTotal() {
		double runningTotal = getTotalMileage() * ETicketInfo.pricePerMile.price;
		if(getIsExpress()) 
			runningTotal += runningTotal * ETicketInfo.ExpressPercentageIncrease.price;
		
		runningTotal += getSeatPrice();
		runningTotal += getAmenitiesPrice();
		
		runningTotal += getMiscPrice();
		if(getMembership())
			runningTotal -= (runningTotal * TrainMiles.DISCOUNT.miles);
		setTotal(runningTotal);
	}
	
	public void addToAmenitiesList(Amenities toAdd) { amenitiesList.add(toAdd); }
	public void addToMiscList(TrainMiles item) { miscList.add(item); }
	public void resetAmenitiesList() { amenitiesList.clear(); }
	public void resetMiscList() { miscList.clear(); }
	public String displayAmenities() {
		String amenitiesString = "";
		if(amenitiesList.isEmpty()) return "You haven't selected any amenities!";
		for(Amenities amenity : amenitiesList)
			amenitiesString += String.format("%-12s$%.2f%n", amenity.toString(), amenity.extras);
		return amenitiesString;
	}
	
	public double getAmenitiesPrice() { 
		double amenitiesPrice = 0.0;
		for(Amenities amenity : amenitiesList)
			amenitiesPrice += amenity.extras;
		return amenitiesPrice;
	} 
	
	public double getMiscPrice() {
		double miscPrice = 0.0;
		for(TrainMiles misc : miscList)
			miscPrice += misc.miles;
//		if(getMembership())
//			miscPrice -= (miscPrice * TrainMiles.DISCOUNT.miles);
		return miscPrice;
	}
	
	public void setTime(String depart) {
		time = depart;
	}
	
	public String getDepartTime() {
		return time;
	}
	
	public String getArrivalTime() {
		int calcTime;
		if(isExpress) {
			calcTime = Integer.parseInt(time);
			calcTime += 1200;
		}
		else {
			calcTime = Integer.parseInt(time);
			calcTime += 2400;
		}
		
		if(calcTime > 2400)
			calcTime = calcTime - 2400;
		
		if(calcTime < 1000) {
			String arriveTime = "";
			arriveTime = "0" + Integer.toString(calcTime);
			return arriveTime;
		}			
		
		return Integer.toString(calcTime);
	}
	
	/**
	 * FIXME Serves no cart purpose - Move
	 */
	public void addPersonToTrain() {
		String id = "";
		id = controller.AddPersonToTrain(getIsExpress(), getSeatType());
		controller.saveRoutesToFile();
		setReservationID(id.toString());
	}
	
	/**
	 * FIXME Serves no cart purpose - Move
	 */
	public ArrayList<String> getListOfDepartures() {
		ArrayList<String> listOfDepartures = new ArrayList<>();
		listOfDepartures.add(TrainStops.NOLA.stops);
		listOfDepartures.add(TrainStops.CHIC.stops);
		listOfDepartures.add(TrainStops.DC.stops);
		return listOfDepartures;
	}
	
	/**
	 * FIXME Serves no cart purpose - Move
	 */
	public ArrayList<String> getListOfDestinations(int index) {
		ArrayList<String> listOfDestinations = getListOfDepartures();
		listOfDestinations.remove(index);
		return listOfDestinations;
	}
	
	/**
	 * FIXME Serves no cart purpose - Move
	 */
	public ArrayList<Seat> getListOfSeatTypes() {
		ArrayList<Seat> listOfSeats = new ArrayList<>();
		listOfSeats.add(new HardSeat());
		listOfSeats.add(new HardSleeperSeat());
		listOfSeats.add(new LuxurySeat());
		listOfSeats.add(new LuxurySleeperSeat());
		return listOfSeats;
	}
	
	public double calculatePoints() {
		double userPoints = 0.0;
		userPoints = getTotalMiles() / TrainMiles.POINTS_CALC.miles;
		return userPoints;
	}
	
	public double getTotalMileage() {
		boolean routeOne = (departStation.equalsIgnoreCase(NOLA) && destination.equalsIgnoreCase(DC)) 
						|| (departStation.equalsIgnoreCase(DC) && destination.equalsIgnoreCase(NOLA));
		
		boolean routeTwo = (departStation.equalsIgnoreCase(NOLA) && destination.equalsIgnoreCase(CHIC))
						|| (departStation.equalsIgnoreCase(CHIC) && destination.equalsIgnoreCase(NOLA));
		
		boolean routeThree = (departStation.equalsIgnoreCase(CHIC) && destination.equalsIgnoreCase(DC))
						||	(departStation.equalsIgnoreCase(DC) && destination.equalsIgnoreCase(CHIC));
		
		if(routeOne) {
			controller.setRoute("R01");
			setTotalMiles(TrainMiles.NOLA_DC.miles);
			return TrainMiles.NOLA_DC.miles;
		} 
		if(routeTwo) {	
			controller.setRoute("R02");
			setTotalMiles(TrainMiles.NOLA_CHIC.miles);
			return TrainMiles.NOLA_CHIC.miles;
		} 
		if(routeThree) {
			controller.setRoute("R03");
			setTotalMiles(TrainMiles.CHIC_DC.miles);
			return TrainMiles.CHIC_DC.miles;
		}
		return 0;
	}
	
	
	public void setDepartStation(String start) { departStation = start; }
	public void setDestination(String end) { destination = end; }
	public void setIsExpress(boolean isExpress) { this.isExpress = isExpress; }
	public void setMembership(boolean member) { membership = member; }//
	public boolean getMembership() { return membership; }
	public void setInsurance(boolean insured) { insurance = insured; }
	public void setUserName(String userName) { this.userName  = userName; }
	public String getUserName() { return this.userName; }
	public void setTotalMiles(double miles) { this.totalMiles = miles; }
	public double getTotalMiles() { return this.totalMiles; }
	public void setReservationID(String id) { this.ReservationID = id; }
	public String getReservationID() { return ReservationID; }
	public void setSeatType(Seat seat) { this.seatType = seat; }
	public void setSeatPrice(double monies) { this.seatPrice = monies; }
	public double getSeatPrice() { return this.seatPrice; }
	public Seat getSeatType() { return this.seatType; }
	public boolean getIsExpress() { return isExpress; }
	public String getDepartStation() { return departStation; }
	public String getDestination() { return destination; }
	
}
