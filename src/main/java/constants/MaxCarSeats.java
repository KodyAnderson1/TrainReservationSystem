package constants;

public enum MaxCarSeats {

	HardSeatCar(36),
	HardSleeperCar(16),
	LuxurySeatCar(16),
	LuxurySleeperCar(8);
	
	final public int NUMBER_OF_SEATS;
	
	MaxCarSeats(int num) { this.NUMBER_OF_SEATS = num; }
}
