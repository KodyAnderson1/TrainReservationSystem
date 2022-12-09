package constants;

public enum Amenities {
	Breakfast(9.99),
	Lunch(11.99),
	Dinner(15.99),
	Drinks(6.50),
	Parking(75.00),
	WiFi(19.99),
	Photo(25.00),
	Laundry(15.00);
	
	public final double extras;
	
	Amenities(double extras) {
		this.extras = extras;
	}
}
