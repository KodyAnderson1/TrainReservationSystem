package logic.model;

import java.util.ArrayList;

import logic.model.traincars.TrainCar;

public class Train {
	private String trainID;
	private ArrayList<TrainCar> trainCars;
	private boolean isExpressTrain;
	
	public Train(String ID) {
		setTrainID(ID);
		trainCars = new ArrayList<>();
		calculateMaxPassengers();
		setIsExpressTrain(true);
	}
	
	public Train(String ID, boolean isExpress) {
		setTrainID(ID);
		trainCars = new ArrayList<>();
		calculateMaxPassengers();
		setIsExpressTrain(isExpress);
	}
	
	public Train() {
		trainCars = new ArrayList<>();
		calculateMaxPassengers();
		setIsExpressTrain(true);
}

	public boolean addCarToTrain(TrainCar typeOfCar) { 
		trainCars.add(typeOfCar);
		return true;
	}
	
	/**
	 * Allows an unspecified amount of params into this method and 
	 * uses a for each loop to add them to the trainCars array
	 */
	public boolean addMultipleCarsToTrain(TrainCar ...typeOfCar) {
		for(TrainCar car : typeOfCar) 
			trainCars.add(car);
		return true;
	}
	
	/**
	 * Retrieves information about a car at a specific index.
	 * DOES NOT add or remove cars.
	 * @param index of car wanted
	 * @return trainCar 
	 */
	public TrainCar getSpecificTrainCar(int index) {
		if (index < 0 || index > trainCars.size()) return null;
		
		return trainCars.get(index);
	}
	
	/**
	 * Puts car in specific location on train
	 * @param typeOfCar - Car being added
	 * @param index - location to put in train
	 * @return true if successful, false if index is out of bounds.
	 */
	public boolean addCarInSpecificLocation(TrainCar typeOfCar, int index) {
		if (index < 0 || index > trainCars.size()) return false;
		
		trainCars.add(index, typeOfCar);
		return true;
	}
	
	public boolean bookASeat(int locationOnTrain, int seatNum) {
		if(trainCars.get(locationOnTrain).bookASeat(seatNum)) return true;
		return false;
	}
	
	public boolean freeASeat(int locationOnTrain, int seatNum) {
		if(trainCars.get(locationOnTrain).freeASeat(seatNum)) return true;
		return false;
	}
	
	/**
	 * @param index - location of car being removed
	 * @return true if successful, false if index out of bounds.
	 */
	public boolean removeCarFromTrain(int index) {
		if(index >= trainCars.size()) return false;
		trainCars.remove(index);
		return true;
	}
	
	private int calculateMaxPassengers() {
		int maxPassengerCount = 0;
		for(TrainCar car : trainCars) 
			maxPassengerCount += car.getMaxNumOfSeats();
		return maxPassengerCount;
	}
	
	private int calculateCurrentNumOfPassengers() {
		int currentPassengerCount = 0;
		for(TrainCar car : trainCars)
			currentPassengerCount += car.getSeatsTaken();
		return currentPassengerCount;
	}
	
	public void setIsExpressTrain(boolean b) { isExpressTrain = b; }
	public void setTrainID(String id) { this.trainID = id; }
	
	public String getTrainID() { return this.trainID; }
	public int getMaxPassengers() { return calculateMaxPassengers(); }
	public int getCurrentNumberOfPassengers() { return calculateCurrentNumOfPassengers(); }
	public boolean getIsExpresssTrain() { return isExpressTrain; }
	public int getSeatsRemaining() { return getMaxPassengers() - getCurrentNumberOfPassengers(); }
	public ArrayList<TrainCar> getTrainCars() { return trainCars; }
	public int getNumOfCarsInTrain() { return trainCars.size(); }
	
	@Override
	public String toString() {
		if(trainCars.isEmpty()) return "This train is empty!";
		int i = 0;
		
		String returnString = String.format("This train contains %d car(s) "
				+ "with a max passenger count of %d.%nIt currently has %d seats full, and %d seats remaining."
				+ "%nTrain ID: %s%nCars Listed Below: %n",
				trainCars.size(), getMaxPassengers(), 
				getCurrentNumberOfPassengers(), getSeatsRemaining(), getTrainID());

		for(TrainCar car : trainCars) {
			returnString += String.format("%d: %s: Currently has %d passengers with %d seats remaining.%n", 
					i, car.toString(), car.getSeatsTaken(), car.getSeatsRemaining());
			i++;
		}
		return returnString;
	}
}
