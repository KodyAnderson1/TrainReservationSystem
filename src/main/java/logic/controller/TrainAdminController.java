package logic.controller;

import java.util.ArrayList;

import logic.model.Route;
import logic.model.Train;
import logic.model.traincars.HardSeatCar;
import logic.model.traincars.HardSleeperCar;
import logic.model.traincars.LuxurySeatCar;
import logic.model.traincars.LuxurySleeperCar;

public class TrainAdminController {
	
	private static TrainAdminController obj = new TrainAdminController();
	private ArrayList<Route> routes;
	
	public static TrainAdminController getInstance() { return obj; }
	
	private TrainAdminController() {
		routes = GUIController.getInstance().getListOfRoutes();
	}
	
	public void saveToFile() {
		new ConvertDataToFile().recordAllToFile(routes);
	}
	
	/**
	 * @return Returns false if ID already exists, true otherwise
	 */
	public boolean checkIfTrainIdIsAvailable(String id) {
		for(Route r : routes) {
			for(Train t : r.getTrainsInRoute()) {
				if(t.getTrainID().equalsIgnoreCase(id))
					return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @return list of car types for GUI
	 */
	public String[] getCarTypes() {
		String[] carTypeArrTemp = {"HardSeatCar", "HardSleeperCar", "LuxurySeatCar", "LuxurySleeperCar"};
		return carTypeArrTemp;
	}
	
	/**
	 * 
	 * @return Ex. => 101, NOLA to DC
	 */
	public ArrayList<String> getTrainAndRoute() {
		ArrayList<String> trainAndRouteDetails = new ArrayList<>();
		for(Route r : routes) {
			for(Train t : r.getTrainsInRoute()) {
				trainAndRouteDetails.add(String.format("%s, %s", t.getTrainID(),r.getNameOfRoute()));
			}
		}
		return trainAndRouteDetails;
	}
	
	public String[] getListOfRouteNames() {
		String[] listOfRouteNames = new String[routes.size()];
		for(int i = 0; i < listOfRouteNames.length; i++) {
			listOfRouteNames[i] = String.format("%s, %s", routes.get(i).getRouteID(), routes.get(i).getNameOfRoute());
		}
		return listOfRouteNames;
	}
	
	public String getDetailsOfTrainById(String id) {
		for(Route r : routes) {
			for(Train t : r.getTrainsInRoute()) {
				if(t.getTrainID().equalsIgnoreCase(id))
					return String.format("%s, %s", t.getTrainID(),r.getNameOfRoute());
			}
		}
		return null;
	}
	
	public int getNumberOfTrainsOnRoute(int index) {
		if(index >= routes.size() || index < 0) return -1;
		
		return routes.get(index).getNumOfTrainsInRoute();
	}
	
	public int getNumberOfRoutes() { return routes.size(); }
	
	/**
	 * 
	 * @param id of train being added
	 * @param routeIndex index in array
	 * @param isExpress
	 */
	public boolean addTrainToSpecificRoute(String id, int routeIndex, boolean isExpress) {
		return routes.get(routeIndex).addTrainToRoute(new Train(id, isExpress));
	}
	
	/**
	 * Gets details for specific route (and all trains on that route)
	 * @param routeId
	 * @return
	 */
	public String getDetailsForRouteAndTrain(int routeId) {
		StringBuilder details = new StringBuilder();
		
		details.append(routes.get(routeId).toString());
		details.append('\n').append("Train Ids:\n");
		routes.get(routeId).getTrainsInRoute().forEach(train -> details.append(train.getTrainID()).append('\n'));
		return details.toString();
	}
	
	public int getTrainIndexById(String trainId) {
		for(Route r : routes) {
			ArrayList<Train> tempArr = r.getTrainsInRoute();
			for(int i = 0; i < tempArr.size(); i++) {
				if(tempArr.get(i).getTrainID().equalsIgnoreCase(trainId))
					return i;
			}
		}
		return -1;
	}
	
	public int getRouteIndexByTrainId(String trainId) {
		for(int i = 0; i < routes.size(); i++) {
			Route r = routes.get(i);
			for(Train t : r.getTrainsInRoute()) {
				if(t.getTrainID().equalsIgnoreCase(trainId))
					return i;
			}
		}
		return -1;
	}
	
	public String getTrainToString(String trainId) {
		for(int i = 0; i < routes.size(); i++) {
			Route r = routes.get(i);
			for(Train t : r.getTrainsInRoute()) {
				if(t.getTrainID().equalsIgnoreCase(trainId))
					return t.toString();
			}
		}
		return null;
	}
	
	public void addTrainCarsToTrain(String carToAdd, String trainId, int amount) {
		int trainIndex = getTrainIndexById(trainId);
		if(trainIndex == -1) return;
		Train train = routes.get(getRouteIndexByTrainId(trainId)).getSpecificTrain(trainIndex);
		if(carToAdd.equalsIgnoreCase("HardSeatCar"))
			train = addHardSeatCar(amount, train);
		if(carToAdd.equalsIgnoreCase("HardSleeperCar"))
			train = addHardSleeperCar(amount, train);
		if(carToAdd.equalsIgnoreCase("LuxurySeatCar"))
			train = addLuxurySeatCar(amount, train);
		if(carToAdd.equalsIgnoreCase("LuxurySleeperCar"))
			train = addLuxurySleeperCar(amount, train);
	}

	public Train addHardSeatCar(int amount, Train train) {
		for(int i = 0; i < amount; i++)
			train.addCarToTrain(new HardSeatCar());
		return train;
	}
	
	public Train addHardSleeperCar(int amount, Train train) {
		for(int i = 0; i < amount; i++)
			train.addCarToTrain(new HardSleeperCar());
		return train;
	}
	
	public Train addLuxurySeatCar(int amount, Train train) {
		for(int i = 0; i < amount; i++)
			train.addCarToTrain(new LuxurySeatCar());
		return train;
	}
	
	public Train addLuxurySleeperCar(int amount, Train train) {
		for(int i = 0; i < amount; i++) 
			train.addCarToTrain(new LuxurySleeperCar());
		return train;
	}
	

}
