package logic.model;

import java.util.ArrayList;

public class Route {
	private String nameOfRoute;
	private String routeID;
	private String departureStation;
	private String arrivalStation;
	private ArrayList<Train> trainsInRoute;
	
	public Route(String ID) {
		setNameOfRoute("Default Route Name");
		trainsInRoute = new ArrayList<>();
		setRouteID(ID);
	}
	
	public Route(String ID, String name, String depart, String Destination) {
		trainsInRoute = new ArrayList<>();
		setNameOfRoute(name);
		setRouteID(ID);
		setDepartureStation(depart);
		setArrivalStation(Destination);
	}
	
	public boolean addTrainToRoute(Train train) {
		trainsInRoute.add(train);
		return true;
	}
	
	public boolean addMultipleTrainsToRoute(Train ...trains) {
		for(Train train : trains)
			trainsInRoute.add(train);
		return true;
	}
	
	public boolean removeTrainFromRoute(int index) {
		if (index < 0 || index > trainsInRoute.size()) return false;
		trainsInRoute.remove(index);
		return true;
	}
	
	public int getMaxCapacityOfRoute() {
		int passengerCapacity = 0;
		for(Train train : trainsInRoute)
			passengerCapacity += train.getMaxPassengers();
		return passengerCapacity;
	}
	
	public int getCurrentCapacityOfRoute() {
		int passengerCapacity = 0;
		for(Train train : trainsInRoute)
			passengerCapacity += train.getCurrentNumberOfPassengers();
		return passengerCapacity;
	}
	
	public int getNumberOfExpressTrainsOnRoute() {
		int numOfTrains = 0;
		for(Train train : trainsInRoute)
			if(train.getIsExpresssTrain()) 
				numOfTrains++;
		return numOfTrains;
	}
	
	public Train getSpecificTrain(int index) { 
		if (index < 0 || index > trainsInRoute.size()) return null;
		return trainsInRoute.get(index); 
	}
	
	public int findSpecificTrain(String id) { 
		for(int i = 0; i < trainsInRoute.size(); i++)
			if(trainsInRoute.get(i).getTrainID().equalsIgnoreCase(id)) return i;
		return -1;
	}
	
	public boolean bookSeatOnTrain(int specificTrain, int locationOnTrain, int seatNum) {
		if(trainsInRoute.get(specificTrain).bookASeat(locationOnTrain, seatNum)) return true;
		return false;
	}
	
	public boolean freeSeatOnTrain(int specificTrain, int locationOnTrain, int seatNum) {
		if(trainsInRoute.get(specificTrain).freeASeat(locationOnTrain, seatNum)) return true;
		return false;
	}
	
	public void setRouteID(String id) { routeID = id; }
	public void setNameOfRoute(String name) { this.nameOfRoute = name; }
	
	public String getDepartureStation() { return departureStation; }
	public String getArrivalStation() { return arrivalStation; }

	public void setDepartureStation(String departureStation) { this.departureStation = departureStation; }
	public void setArrivalStation(String arrivalStation) { this.arrivalStation = arrivalStation; }

	public String getNameOfRoute() { return this.nameOfRoute; }
	public ArrayList<Train> getTrainsInRoute() { return trainsInRoute; }
	public int getNumOfTrainsInRoute() { return trainsInRoute.size(); }
	public String getRouteID() { return routeID; }
	
	@Override
	public String toString() {
		return String.format("Route Name: %s%nRoute ID: %s%nTrain(s) on route: %d%n"
				+ "Current number of passengers: %d%nMaximum Capacity: %d%n"
				+ "Number of Express Trains: %s%n", 
				getNameOfRoute(), getRouteID(), trainsInRoute.size(), getCurrentCapacityOfRoute(), 
				getMaxCapacityOfRoute(), getNumberOfExpressTrainsOnRoute());
	}
}
