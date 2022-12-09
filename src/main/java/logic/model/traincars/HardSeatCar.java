package logic.model.traincars;

import constants.MaxCarSeats;
import logic.model.seats.HardSeat;

public final class HardSeatCar extends TrainCar {

	public HardSeatCar() {
		super(MaxCarSeats.HardSeatCar, false, false);
		initializeArray();
	}

	@Override
	public void initializeArray() {
		final int MAX_SEATS = super.getMaxNumOfSeats();
		seatsInCar = new HardSeat[MAX_SEATS];
		for (int i = 0; i < MAX_SEATS; i++)
			seatsInCar[i] = new HardSeat(i);
	}

	@Override
	public String toString() {
		return "HardSeatCar";
	}
}
