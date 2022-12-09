package GUI.CustomerGUI.CarAndSeatPanels;

import java.util.ArrayList;

import javax.swing.JPanel;

import logic.controller.ShoppingCart;

public abstract class CarDetailsGUI {

	
	protected ArrayList<SeatPanel> seatsBottom;
	protected ArrayList<SeatPanel> seatsTop;
	private int seatWidth = 25;
	private int seatHeight = 25;
	private int numOfSeats;
	private int numOfSeatsPerRow;
	
	private int numOfSeatsToBook;
	private int numOfSeatsApart;
	
	CarDetailsGUI(int seatWidth, int seatHeight, int numOfSeats, int numOfSeatsPerRow) {
		seatsBottom = new ArrayList<>();
		seatsTop = new ArrayList<>();
		setSeatWidth(seatWidth);
		setseatHeight(seatHeight);
		setNumOfSeats(numOfSeats);
		setNumOfSeatsPerRow(numOfSeatsPerRow);
		setNumOfSeatsToBook(getRandomNumber(1, 3));
		setNumOfSeatsApart(getRandomNumber(1, 3));
	}
	
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	public abstract void setUpBottomSeats();
	public abstract void setUpTopSeats();
	public abstract JPanel getPanel();
	public abstract void setSeatDistance(int distance);
	public abstract int getSeatDistance();
	public abstract void setRowDistance(int distance);
	public abstract int getRowDistance();
	
	public void setNumOfSeatsToBook(int num) { this.numOfSeatsToBook = num; }
	public void setNumOfSeatsApart(int num) { this.numOfSeatsApart = num; }
	public void setNumOfSeatsPerRow(int num) { this.numOfSeatsPerRow = num; }
	public void setNumOfSeats(int num) { this.numOfSeats = num; }
	public void setSeatWidth(int w) { this.seatWidth = w; }
	public void setseatHeight(int h) { this.seatHeight = h; }
	
	public int getNumOfSeatsPerRow() { return this.numOfSeatsPerRow; }
	public int getNumOfSeats() { return this.numOfSeats; }
	public int getSeatWidth() { return this.seatWidth; }
	public int getseatHeight() { return this.seatHeight; }	
}
