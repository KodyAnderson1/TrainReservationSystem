package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import logic.model.Train;
import logic.model.traincars.HardSeatCar;
import logic.model.traincars.LuxurySeatCar;

class TrainTest {

	@Test
	void DefaultConstructorTest() {
		Train train = new Train();
	
		assertTrue(train.getTrainCars().isEmpty());
		assertTrue(train.getIsExpresssTrain() == true);
		assertTrue(train.getCurrentNumberOfPassengers() == 0);
	}
	
	@Test
	void addCarToTrain() {
		Train train = new Train();
		
		assertTrue(train.addCarToTrain(new LuxurySeatCar()));
		assertTrue(train.getTrainCars().size() == 1);
	}
	
	@Test
	void getSpecificTrainCarAndRemove() {
		Train train = new Train();
		LuxurySeatCar testCar = new LuxurySeatCar();
		train.addCarToTrain(testCar);
		train.getSpecificTrainCar(0);
		assertTrue(train.getSpecificTrainCar(0).toString().equalsIgnoreCase("LuxurySeatCar"));
		train.removeCarFromTrain(0);
		
		assertTrue(train.getTrainCars().size() == 0);
	}
	
	@Test
	void calculateCurrentNumOfPassengers() {
		Train train = new Train();
		LuxurySeatCar testCar1 = new LuxurySeatCar();
		LuxurySeatCar testCar2 = new LuxurySeatCar();
		LuxurySeatCar testCar3 = new LuxurySeatCar();
		HardSeatCar testCar4 = new HardSeatCar();
		
		train.addMultipleCarsToTrain(testCar1, testCar2, testCar3, testCar4);
		
		int testCondition = (testCar1.getMaxNumOfSeats() + 
				testCar2.getMaxNumOfSeats() + testCar3.getMaxNumOfSeats()
				+ testCar4.getMaxNumOfSeats());
		
		assertTrue(train.getMaxPassengers() == testCondition);
		
		train.getTrainCars().get(0).bookASeat(2);

		assertTrue(train.getSeatsRemaining() == testCondition - 1);
		assertTrue(train.getCurrentNumberOfPassengers() == 1);
		
		for(int i = 0; i < 6; i++) {
				train.getTrainCars().get(0).bookASeat(i);
			}
		
		assertTrue(train.getCurrentNumberOfPassengers() == 6);
		
		train.getTrainCars().get(0).freeASeat(2);
		
		assertTrue(train.getCurrentNumberOfPassengers() == 5);
	}
	
	@Test
	void testToString() {
		Train train = new Train();
		assertTrue(train.toString().equalsIgnoreCase("This train is empty!"));
	}
	
	@Test
	void testCheckIfFull() {
		Train train = new Train();
		LuxurySeatCar testCar1 = new LuxurySeatCar();
		train.addCarToTrain(testCar1);
		for(int i = 0; i < 16; i++) {
				train.getTrainCars().get(0).bookASeat(i);
		}
		
		assertTrue(train.getTrainCars().get(0).checkIfFull());
	}
	
}
