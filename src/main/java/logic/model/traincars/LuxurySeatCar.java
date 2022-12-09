package logic.model.traincars;

import constants.MaxCarSeats;
import logic.model.seats.LuxurySeat;

public final class LuxurySeatCar extends TrainCar {

	public LuxurySeatCar() {
		super(MaxCarSeats.LuxurySeatCar, false, true);
		initializeArray();
	}

	@Override
	public void initializeArray() {
		final int MAX_SEATS = super.getMaxNumOfSeats();
		seatsInCar = new LuxurySeat[MAX_SEATS];
		for (int i = 0; i < MAX_SEATS; i++)
			seatsInCar[i] = new LuxurySeat(i);
	}

	@Override
	public String toString() {
		return "LuxurySeatCar";
	}
}
