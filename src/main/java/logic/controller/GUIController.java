package logic.controller;

import java.util.ArrayList;

import constants.FilePaths;
import logic.model.Route;
import logic.model.Train;
import logic.model.seats.Seat;
import logic.model.traincars.TrainCar;

public class GUIController {
	private static GUIController obj = new GUIController();
	
	private DataCreationFromFile readInFile;
	private ConvertDataToFile trainStringConverter;
	private String userRouteChoosen;
	
	private static ArrayList<Route> listOfRoutes; // BIG CHEESE
	
	public static GUIController getInstance() { return obj; }
	
	private GUIController() {
		userRouteChoosen = "";
		trainStringConverter = new ConvertDataToFile();
		readInFile = new DataCreationFromFile(FilePaths.ROUTES.toString(), FilePaths.TRAINS.toString(), FilePaths.SEATS.toString());
		listOfRoutes = readInFile.getTrainRoutes();
		
	}
	
	public void saveRoutesToFile() {
		new ConvertDataToFile().recordAllToFile(listOfRoutes);
	}
	
	/**
	 * Called when user completes purchase, generates 
	 * tracker ID for storage or cancellation later
	 * 
	 * FIXME Route needs to get passed in as a param to avoid weirdness
	 * 
	 * @param isExpress
	 * @param seatType
	 * @return tracker ID
	 */
	public String AddPersonToTrain(boolean isExpress, Seat seatType) {
		int routeIndex = findSpecificRoute(getRoute());
		Route route = listOfRoutes.get(routeIndex);
		int trainIndex = findTrainIndex(isExpress, seatType, 0);
		Train train = route.getSpecificTrain(trainIndex);
		int trainCarIndex = findNextFreeTrainCar(train, seatType);
		TrainCar trainCar = train.getSpecificTrainCar(trainCarIndex);
		String seatString = getInitialForSeat(ShoppingCart.getInstance().getSeatType());

		int seatIndex = findNextFreeSeatIndex(trainCar);
		listOfRoutes.get(routeIndex).bookSeatOnTrain(trainIndex, trainCarIndex, seatIndex);
		
		return String.format("%sR%s#%d%s%d", trainStringConverter.trainData(train), route.getRouteID(), trainCarIndex, seatString, seatIndex);
	}
	
	private String getInitialForSeat(Seat seatType) {
		StringBuilder seatTypeBuilder = new StringBuilder();
		String seatTypeString = seatType.toString();
		
		if(seatTypeString.equalsIgnoreCase("HardSeat")) seatTypeBuilder.append("H");
		if(seatTypeString.equalsIgnoreCase("HardSleeperSeat")) seatTypeBuilder.append("S");
		if(seatTypeString.equalsIgnoreCase("LuxurySeat")) seatTypeBuilder.append("L");
		if(seatTypeString.equalsIgnoreCase("LuxurySleeperSeat")) seatTypeBuilder.append("X");
		
		return seatTypeBuilder.toString();
	}
	
	/**
	 * DOES NOT currently check if a train is FULL or not
	 */
	public int findTrainIndex(boolean isExpress, Seat seatType, int routeIndex) {
		ArrayList<Train> trains = listOfRoutes.get(routeIndex).getTrainsInRoute();
		int i;
			for(i = 0; i < trains.size(); i++) {
			if(isExpress) {
				if(trainStringConverter.trainData(trains.get(i)).contains("E")) return i;
			} else
				if(trainStringConverter.trainData(trains.get(i)).contains("N")) return i;
		}
			return -1;
	}
	
	/**
	 * Finds next free trainCar
	 */
	public int findNextFreeTrainCar(Train train, Seat seatType) {
		String seatTypeString = transformSeatTypeToCar(seatType);
		
		ArrayList<TrainCar> trainCars = train.getTrainCars();
		for(int i = 0; i < trainCars.size(); i++) {
			if(trainCars.get(i).checkIfFull()) continue;
			if(trainCars.get(i).toString().equalsIgnoreCase(seatTypeString)) return i;
		}
		return -1;		
	}
	
	// Find the next free seat within specified trainCar
	public int findNextFreeSeatIndex(TrainCar trainCar) {
		Seat[] tempSeatarr = trainCar.getSeatsInCarArray();
		for(int i = 0; i < tempSeatarr.length; i++) {
			if(! tempSeatarr[i].getIsBooked()) return i;
		}

		return -1;
	}
	
	private String transformSeatTypeToCar(Seat seatType) {
		StringBuilder seatTypeBuilder = new StringBuilder();
		String seatTypeString = seatType.toString();
		
		if(seatTypeString.equalsIgnoreCase("HardSeat")) seatTypeBuilder.append("HardSeatCar");
		if(seatTypeString.equalsIgnoreCase("HardSleeperSeat")) seatTypeBuilder.append("HardSleeperCar");
		if(seatTypeString.equalsIgnoreCase("LuxurySeat")) seatTypeBuilder.append("LuxurySeatCar");
		if(seatTypeString.equalsIgnoreCase("LuxurySleeperSeat")) seatTypeBuilder.append("LuxurySleeperCar");
		
		return seatTypeBuilder.toString();
	}
	
	
	public int findSpecificRoute(String id) {
		for(int i = 0; i < listOfRoutes.size(); i++) {
			String routeid = listOfRoutes.get(i).getRouteID();
			routeid = "R" + routeid;
			if(routeid.equalsIgnoreCase(id)) return i;
		}
		return -1;
	}
	

	public void setRoute(String route) { this.userRouteChoosen = route; }
	public String getRoute() { return this.userRouteChoosen; }
	public ArrayList<Route> getListOfRoutes() { return listOfRoutes; }

}
