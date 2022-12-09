package logic.users;

public class UserReservation {
	private double cost;
	private String seat;
	private String userName;
	private boolean isExpress;
	private String reservationID;
	private String tripDestination;
	private String departure;
	private double milage;
	private String time;
	private String amenitites;
	private double milesToDestination;
	
	public UserReservation () {}
	
	public UserReservation (String userName, String resID, boolean express, String amenitites, double cost, String seat, String destination, String departure, String time) {
		setUserName(userName);
		setTripDestination(tripDestination);
		setMilage(milage);
		setAmenitites(amenitites);
		setCost(cost);
		setReservationID(resID);
		setExpress(express);
		setSeat(seat);
		setDepartTime(time);
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public boolean isExpress() {
		return isExpress;
	}

	public void setDepartTime(String time) {
		this.time = time;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setExpress(boolean isExpress) {
		this.isExpress = isExpress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTripDestination() {
		return tripDestination;
	}

	public void setTripDestination(String tripDestination) {
		this.tripDestination = tripDestination;
	}

	public double getMilage() {
		return milage;
	}

	public void setMilage(double milage) {
		this.milage = milage;
	}

	public String getAmenitites() {
		return amenitites;
	}

	public void setAmenitites(String amenitites) {
		this.amenitites = amenitites;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getReservationID() {
		return reservationID;
	}

	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}

	public double getMilesToDestination() {
		return milesToDestination;
	}

	public void setMilesToDestination(double milesToDestination) {
		this.milesToDestination = milesToDestination;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Username: ").append(getUserName()).append('\n');
        sb.append("Reservation ID: ").append(getReservationID()).append('\n');
        sb.append("Destination: ").append(getTripDestination()).append('\n');
        sb.append("Departure: ").append(getDeparture()).append('\n');
        sb.append("Express: ").append(isExpress()).append('\n');
        sb.append("Depart Time: ").append(getTime()).append('\n');
        sb.append("Seat: ").append(getSeat()).append('\n');
        sb.append("Cost: ").append(getCost()).append('\n');
        return sb.toString();
    }

}
