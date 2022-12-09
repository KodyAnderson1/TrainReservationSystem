package logic.controller;

import java.util.ArrayList;

import constants.FilePaths;
import logic.model.Route;
import logic.model.Train;
import logic.model.traincars.HardSeatCar;
import logic.model.traincars.HardSleeperCar;
import logic.model.traincars.LuxurySeatCar;
import logic.model.traincars.LuxurySleeperCar;

/**
 * Creates routes and all subsequent data FROM files provided.
 */
public class DataCreationFromFile {

	private ArrayList<Route> trainRoutes;
	
	public DataCreationFromFile() {
		trainRoutes = new ArrayList<>();
		parseAllData(FilePaths.ROUTES.toString(), FilePaths.TRAINS.toString(), FilePaths.SEATS.toString());
	}
	
	public DataCreationFromFile(String routeFileName, String trainFileName, String seatFileName) {
		trainRoutes = new ArrayList<>();
		parseAllData(routeFileName, trainFileName, seatFileName);
	}
	
	public void parseAllData(String routeFile, String trainFile, String seatFile) {
		if(!createRoutes(routeFile)) return;
		if(!getTrainIdFromFile(trainFile)) return;
		if(!getSeatsFromFile(seatFile)) return;
	}
	
	public boolean getSeatsFromFile(String fileName) {
		FileIO seatFile = new FileIO();
		String seatData = seatFile.readInEntireFile(fileName);
		if(seatData.isBlank()) return false;
		String[] seatSplitter = seatData.split("\n");
		
		for(int i = 0; i < seatSplitter.length; i+=2) {
			String trackingID = seatSplitter[i];
			String trainId = trackingID.substring(0, 3);
			String routeID = trackingID.substring(trackingID.indexOf("R"));
			int routeIndex = findSpecificRoute(routeID);
			int trainIndex = findSpecificTrain(trainId);

			String[] carIds = seatSplitter[i + 1].split(",");
			for(String c : carIds) {
				int locationOnTrain = Character.getNumericValue(c.charAt(0));
				int locationInCar = Integer.parseInt(c.substring(2));
				trainRoutes.get(routeIndex).bookSeatOnTrain(trainIndex, locationOnTrain, locationInCar);
			}
		}
		return true;
	}
	
	public int findSpecificTrain(String id) {
		for(Route r : trainRoutes) {
			if(r.findSpecificTrain(id) != -1) return r.findSpecificTrain(id);
		}
		return -1;
	}
	
	public int findSpecificRoute(String id) {
		for(int i = 0; i < trainRoutes.size(); i++) {
			String routeid = trainRoutes.get(i).getRouteID();
			routeid = "R" + routeid;
			if(routeid.equalsIgnoreCase(id)) return i;
		}
		return -1;
	}
	
	/**
	 * @param fileName
	 * specificData[0] = Route ID
	 * specificData[1] = Route name
	 * specificData[2] = Route Depart Station
	 * specificData[3] = Route Destination Station
	 */
	public boolean createRoutes(String fileName) {
		FileIO routeFile = new FileIO();
		String routeData = routeFile.readInEntireFile(fileName);
		if(routeData.isBlank()) return false;
		String[] routeSplit = routeData.split("\n");
		
		for(String route : routeSplit) {
			String[] specificData = route.split(",");
			trainRoutes.add(new Route(specificData[0].substring(1), 
					specificData[1], specificData[2], specificData[3]));
		}
		return true;
	}
	
	public boolean getTrainIdFromFile(String fileName) {
		FileIO trainFile = new FileIO();
		String trainData = trainFile.readInEntireFile(fileName);
		if(trainData.isBlank()) return false;
		String[] trainSplit = trainData.split("\n");
		
		for(String train : trainSplit) {
			String textToCheck = train.substring(train.indexOf('R') + 1);
			if(checkIfRouteExists(textToCheck))
				CreateTrainWithCars(train, textToCheck);
		}
		return true;
	}
	
	/**
	 * 
	 * @param textToCheck routeID w/o R. 01, 02, 03, etc...
	 * @return true if route exists in ArrayList, false otherwise
	 */
	public boolean checkIfRouteExists(String textToCheck) {
		for(Route route : trainRoutes)
			if(route.getRouteID().equalsIgnoreCase(textToCheck)) return true;
		return false;
	}
	
	/**
	 * @param textToCheck
	 * @return location of route in ArrayList, -1 if ID not found
	 */
	public int getRoutePosition(String textToCheck) {
		if (! checkIfRouteExists(textToCheck)) return -1;
		for(int i = 0; i < trainRoutes.size(); i++)
			if(trainRoutes.get(i).getRouteID().equalsIgnoreCase(textToCheck)) return i;
		return -1;
	}
	
	/**
	 * Tears apart the specificTrain id string and uses parts to construct the train
	 * with specified cars
	 * @param specificTrain = ID used to create train & cars ex. 112N3H1S3L1X@R01
	 * @param routeID = ID to put the train on
	 */
	public Train CreateTrainWithCars(String specificTrain, String routeID) {
		int indexOfExpress = (specificTrain.indexOf('E') != -1) ? 
								specificTrain.indexOf('E') : specificTrain.indexOf('N');
		char isExpress = (specificTrain.indexOf('E') != -1) ? 'E' : 'N';
		int indexOfAt = specificTrain.indexOf('@');
		String trainID = specificTrain.substring(0, indexOfExpress);
		String trainConfig = specificTrain.substring(indexOfExpress + 1, indexOfAt);
		Train train = new Train(trainID);
		
		if(isExpress == 'E') train.setIsExpressTrain(true);
		else train.setIsExpressTrain(false);
		
		for(int i = 0; i < trainConfig.length(); i+=2) 
			train = addCarToTrain(Character.getNumericValue(trainConfig.charAt(i)), trainConfig.charAt(i + 1), train);
		
		if(getRoutePosition(routeID) != -1)
			trainRoutes.get(getRoutePosition(routeID)).addTrainToRoute(train);
		return train;
	}
	
	public Train addCarToTrain(int numOfCars, char typeOfCar, Train train) {
		if(typeOfCar == 'H') createHardSeatCars(numOfCars, train);
		if(typeOfCar == 'S') createHardSleeperCars(numOfCars, train);
		if(typeOfCar == 'L') createLuxuryCars(numOfCars, train);
		if(typeOfCar == 'X') createLuxurySleeperCars(numOfCars, train);
		return train;
	}
	
	public Train createHardSeatCars(int numOfCars, Train train) {
		for(int i = 0; i < numOfCars; i++)
			train.addCarToTrain(new HardSeatCar());
		return train;
	}
	
	public Train createHardSleeperCars(int numOfCars, Train train) {
		for(int i = 0; i < numOfCars; i++)
			train.addCarToTrain(new HardSleeperCar());
		return train;
	}
	
	public Train createLuxuryCars(int numOfCars, Train train) {
		for(int i = 0; i < numOfCars; i++)
			train.addCarToTrain(new LuxurySeatCar());
		return train;
	}
	
	public Train createLuxurySleeperCars(int numOfCars, Train train) {
		for(int i = 0; i < numOfCars; i++)
			train.addCarToTrain(new LuxurySleeperCar());
		return train;
	}
	
	public ArrayList<Route> getTrainRoutes() { return this.trainRoutes; }
}
