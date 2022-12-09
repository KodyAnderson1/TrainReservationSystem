package logic.model.traincars;

import constants.MaxCarSeats;
import logic.model.seats.HardSleeperSeat;

public final class HardSleeperCar extends TrainCar {

	public HardSleeperCar() {
		super(MaxCarSeats.HardSleeperCar, true, false);
		initializeArray();
	}

	@Override
	public void initializeArray() {
		final int MAX_SEATS = super.getMaxNumOfSeats();
		seatsInCar = new HardSleeperSeat[MAX_SEATS];
		for (int i = 0; i < MAX_SEATS; i++)
			seatsInCar[i] = new HardSleeperSeat(i);
	}

	@Override
	public String toString() {
		return "HardSleeperCar";
	}
}
