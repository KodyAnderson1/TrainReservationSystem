package logic.model.traincars;

import constants.MaxCarSeats;
import logic.model.seats.LuxurySleeperSeat;

public class LuxurySleeperCar extends TrainCar {

	public LuxurySleeperCar() {
		super(MaxCarSeats.LuxurySleeperCar, true, true);
		initializeArray();
	}

	@Override
	public void initializeArray() {
		final int MAX_SEATS = super.getMaxNumOfSeats();
		seatsInCar = new LuxurySleeperSeat[MAX_SEATS];
		for (int i = 0; i < MAX_SEATS; i++)
			seatsInCar[i] = new LuxurySleeperSeat(i);
	}

	@Override
	public String toString() {
		return "LuxurySleeperCar";
	}
}
