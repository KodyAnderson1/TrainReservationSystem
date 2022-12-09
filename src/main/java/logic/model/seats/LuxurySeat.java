package logic.model.seats;

import constants.ETicketInfo;

public class LuxurySeat extends Seat {

	public LuxurySeat() {
		setTicketPrice(ETicketInfo.baseTicketPrice.price);
		setSleeper(false);
		setCanBeExpressTicket(true);
		setIsBooked(false);
	}
	
	public LuxurySeat(int ID) {
		seatID = ID;
		setTicketPrice(ETicketInfo.baseTicketPrice.price);
		setSleeper(false);
		setCanBeExpressTicket(true);
		setIsBooked(false);
	}

	@Override
	public void setTicketPrice(double price) {
		price += (price * ETicketInfo.LuxuryPercentageIncrease.price);
		this.ticketPrice = price;
	}
	
	@Override
	public void setSleeper(boolean isSleeper) { this.isSleeper = isSleeper; }
	@Override
	public void setCanBeExpressTicket(boolean isExpressTicket) { this.canBeExpressTicket = isExpressTicket; }
	@Override
	public void setIsBooked(boolean isBooked) { this.isBooked = isBooked; }
	
	@Override
	public double getTicketPrice() { return this.ticketPrice; }
	@Override
	public boolean getCanBeExpressTicket() { return this.canBeExpressTicket; }
	@Override
	public boolean getIsSleeper() { return this.isSleeper; }
	@Override
	public boolean getIsBooked() { return this.isBooked; }
	@Override
	public String toString() { return "LuxurySeat"; }
	@Override
	public int getSeatID() { return seatID; }
}
