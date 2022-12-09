package logic.controller;

import java.nio.file.Path;
import java.util.ArrayList;

import constants.FilePaths;
import logic.model.Route;
import logic.model.Train;
import logic.model.seats.Seat;
import logic.model.traincars.TrainCar;

/**
 * Takes in an arrayList of routes and string-ifies ALL data and records to file
 */
public class ConvertDataToFile {
	private final Path trainsFile;
	private final Path routesFile;
	private final Path seatsFile;

	public ConvertDataToFile() {
		trainsFile = FilePaths.TRAINS;
		routesFile = FilePaths.ROUTES;
		seatsFile = FilePaths.SEATS;
	}
	
	public ConvertDataToFile(Path routesFile, Path trainsFile, Path seatsFile) {
		this.trainsFile = trainsFile;
		this.routesFile = routesFile;
		this.seatsFile = seatsFile;
	}
	
	/**
	 * Helper method to save all data to file
	 * @param listOfRoutes
	 */
	public void recordAllToFile(ArrayList<Route> listOfRoutes) {
		recordTrainDataToFile(listOfRoutes);
		recordRouteDataToFile(listOfRoutes);
		recordSeatDataToFile(listOfRoutes);
	}
	
	public void recordTrainDataToFile(ArrayList<Route> listOfRoutes) {
		FileIO trainRecords = new FileIO();
		trainRecords.writeToFile(trainsFile, getAllTrainData(listOfRoutes));
	}
	
	public void recordRouteDataToFile(ArrayList<Route> listOfRoutes) {
		FileIO routeRecords = new FileIO();
		routeRecords.writeToFile(routesFile, getRouteData(listOfRoutes));
	}
	
	public void recordSeatDataToFile(ArrayList<Route> listOfRoutes) {
		FileIO seatsRecords = new FileIO();
		seatsRecords.writeToFile(seatsFile, AllBookedSeatsToFile(listOfRoutes));
	}
	
	public String AllBookedSeatsToFile(ArrayList<Route> listOfRoutes) {
		String data = "";
		for(Route r : listOfRoutes) {
			 for(Train t : r.getTrainsInRoute()) {
				 if(t.getCurrentNumberOfPassengers() == 0) continue;
				 data += String.format("%sR%s%n%s%n", trainData(t), r.getRouteID(), getAllBookedSeats(t));
			 }
		 }
		return data;
	}
	
	public String getAllBookedSeats(Train train) {
		String allBookedSeats = "";
		
		for(int t = 0; t < train.getTrainCars().size(); t++) {
			TrainCar tc = train.getSpecificTrainCar(t);
			Seat[] tempSeat = tc.getSeatsInCarArray();
			for(int i = 0; i < tempSeat.length; i++) {
				if(tempSeat[i].getIsBooked()) {
					allBookedSeats += getSpecificSeatID(tc.toString(), t, tempSeat[i].getSeatID()) + ",";
				}
			}
		}
		allBookedSeats = allBookedSeats.substring(0, allBookedSeats.length() - 1);
		return allBookedSeats;
	}

	/**
	 * ex. 0H9
	 * First car in train, HardSeatClass,9th seat
	 */
	public String getSpecificSeatID(String carType, int locationInTrain, int seatID) {
		char[] carCharacters = {'H', 'S', 'L', 'X'};
		String seatIDString = String.format("%d", locationInTrain);
		
		if(carType.equalsIgnoreCase("HardSeatCar")) seatIDString += carCharacters[0];
		if(carType.equalsIgnoreCase("HardSleeperCar")) seatIDString += carCharacters[1];
		if(carType.equalsIgnoreCase("LuxurySeatCar")) seatIDString += carCharacters[2];
		if(carType.equalsIgnoreCase("LuxurySleeperCar")) seatIDString += carCharacters[3];
		
		seatIDString += String.format("%d", seatID);
		return seatIDString;
	}

	/**
	 * Ex. R01,East Cost Line,New Orleans,Washington D.C.
	 */
	public String getRouteData(ArrayList<Route> listOfRoutes) {
		String routeData = "";
		for(Route r : listOfRoutes)
			routeData += String.format("R%s,%s,%s,%s%n", r.getRouteID(), r.getNameOfRoute(), 
										r.getDepartureStation(), r.getArrivalStation());
		return routeData;
	}
	
	/**
	 * ex. 110E3H2L@R01
	 * Gets ALL trainID strings from the ArrayList<Route> passed in. (For file)
	 */
	public String getAllTrainData(ArrayList<Route> listOfRoutes) {
		String trainData = "";
		for(Route r : listOfRoutes)
			for(Train t : r.getTrainsInRoute())
				trainData += String.format("%sR%s%n", trainData(t), r.getRouteID());
		return trainData;
	}
	
	/**
	 * Takes in a train element, parses the data and spits out a string representing the data
	 * ex. 110E3H2L@
	 * Train ID: 100
	 * Express/N: Express (E)
	 * Car Composition: 3H2L => 3 HardSeatCars, 2 LuxurySeatCars
	 * @ : Delimiter
	 * @param train being stringified
	 */
	public String trainData(Train train) {
		String returnString = String.format("%s", train.getTrainID());
		int[] seatCounter = new int[4];
		char[] carCharacters = {'H', 'S', 'L', 'X'};
		
		if(train.getIsExpresssTrain()) returnString += String.format("%c", 'E');
		else returnString += String.format("%c", 'N');
		
		for(TrainCar car : train.getTrainCars()) {
			
			if(car.toString().equalsIgnoreCase("HardSeatCar")) seatCounter[0]++;
			if(car.toString().equalsIgnoreCase("HardSleeperCar")) seatCounter[1]++;
			if(car.toString().equalsIgnoreCase("LuxurySeatCar")) seatCounter[2]++;
			if(car.toString().equalsIgnoreCase("LuxurySleeperCar")) seatCounter[3]++;
		}
		
		for(int i = 0; i < seatCounter.length; i++) {
			if(seatCounter[i] > 0) {
				if(i == 0) returnString += String.format("%d%c", seatCounter[0], carCharacters[0]);
				if(i == 1) returnString += String.format("%d%c", seatCounter[1], carCharacters[1]);
				if(i == 2) returnString += String.format("%d%c", seatCounter[2], carCharacters[2]);
				if(i == 3) returnString += String.format("%d%c", seatCounter[3], carCharacters[3]);
			}
		}
		returnString += '@';
		return returnString;
	}
}
