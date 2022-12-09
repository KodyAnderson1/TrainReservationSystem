package logic.model.seats;

public abstract class Seat {
	protected double ticketPrice;
	protected boolean canBeExpressTicket;
	protected boolean isSleeper;
	protected boolean isBooked;
	protected int seatID;
	
	public abstract void setTicketPrice(double price);
	public abstract void setCanBeExpressTicket(boolean isExpressTicket);
	public abstract void setSleeper(boolean isSleeper);
	public abstract void setIsBooked(boolean isBooked);
	
	public abstract boolean getCanBeExpressTicket();
	public abstract boolean getIsSleeper();
	public abstract double getTicketPrice();
	public abstract boolean getIsBooked();
	public abstract int getSeatID();
	
}

