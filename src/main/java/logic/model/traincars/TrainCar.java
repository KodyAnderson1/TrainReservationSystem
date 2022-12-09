package logic.model.traincars;

import constants.MaxCarSeats;
import logic.model.seats.Seat;


public abstract class TrainCar {
	protected Seat[] seatsInCar;
	protected String carID;
	protected int seatsTaken;
	private final int MAX_NUM_OF_SEATS;
	private boolean isSleeper;
	private boolean isLuxury;
	
	public TrainCar(MaxCarSeats seats, boolean isSleeper, boolean isLuxury) {
		MAX_NUM_OF_SEATS = seats.NUMBER_OF_SEATS;
		setSeatsTaken(0);
		setSleeper(isSleeper);
		setIsLuxury(isLuxury);
	}
	
	public abstract void initializeArray();
	
	public final boolean bookASeat(int index) {
		if(index < 0 || index > getMaxNumOfSeats()) return false;
		if(seatsInCar[index].getIsBooked()) return false;
		
		seatsInCar[index].setIsBooked(true);
		seatsTaken++;
		return true;
	}
	
	public final boolean freeASeat(int index) { 
		if(index < 0 || index > getMaxNumOfSeats()) return false;
		if(!seatsInCar[index].getIsBooked()) return false;
		
		seatsInCar[index].setIsBooked(false);
		seatsTaken--;
		return true;
	}
	
	public final boolean checkIfFull() {
		if (getSeatsRemaining() == 0) return true;
		return false;
	}
	

	public final boolean getIsLuxury() { return isLuxury; }
	public final int getSeatsRemaining() { return MAX_NUM_OF_SEATS - seatsTaken; }
	public final int getSeatsTaken() { return seatsTaken; }
	public final boolean getIsSleeper() { return isSleeper; }
	public final Seat[] getSeatsInCarArray() { return seatsInCar; }
	public final int getMaxNumOfSeats() { return MAX_NUM_OF_SEATS; }
	public final String getCarID() { return carID; }
	
	public final void setSeatsTaken(int num) { this.seatsTaken = num; }
	public final void setSleeper(boolean b) { this.isSleeper = b; }
	public final void setIsLuxury(boolean b) { this.isLuxury = b; }
	public final void setCarID(String id) { this.carID = id; }
}
