package GUI.CustomerGUI.CarAndSeatPanels;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import constants.MaxCarSeats;

public class LuxuryCarPanel extends CarDetailsGUI {

	JPanel seatPanel = new JPanel();
	private int seatDistance;
	private int rowDistance;

	public LuxuryCarPanel() {
		super(40, 40, MaxCarSeats.LuxurySeatCar.NUMBER_OF_SEATS, 2);
		
		seatPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		seatPanel.setBounds(27, 30, 498, 205);
		seatPanel.setLayout(null);
		
		setRowDistance(50);
		setSeatDistance(2); // distance between seats in same row
		
		setUpBottomSeats();
		setUpTopSeats();

		addBathroomComponents();
	}

	@Override
	public void setUpBottomSeats() {
		final int NUM_OF_SEATS = super.getNumOfSeats() / 2;
		final int startingPosY = 159;
		
		int x = 52;
		int y = startingPosY;
		
		for (int i = 1; i <= NUM_OF_SEATS; i++) {
			super.seatsBottom.add(new SeatPanel(x, y, super.getSeatWidth(), super.getseatHeight(), i));
			y -= getSeatDistance();
			if (i % super.getNumOfSeatsPerRow() == 0) {
				x += getRowDistance();
				y = startingPosY;
			}
		}
		for (SeatPanel p : super.seatsBottom)
			seatPanel.add(p);
		
		ArrayList<Integer> alreadyBooked = new ArrayList<>();
		int endNum = getRandomNumber(1, NUM_OF_SEATS);
		
		for(int i = 0; i < endNum; i++) {
			int seatToBook = getRandomNumber(1, NUM_OF_SEATS);
			if(!alreadyBooked.contains(seatToBook)) {
				alreadyBooked.add(seatToBook);
				super.seatsBottom.get(seatToBook).bookSeat();
			}
		}
	}

	@Override
	public void setUpTopSeats() {
		final int NUM_OF_SEATS = super.getNumOfSeats() / 2;
		final int INDEX_OFFSET = MaxCarSeats.LuxurySeatCar.NUMBER_OF_SEATS / 2;
		final int startingPosY = 49;
		
		int x = 52;
		int y = startingPosY;

		for (int i = 1; i <= NUM_OF_SEATS; i++) {
			super.seatsTop.add(new SeatPanel(x, y, super.getSeatWidth(), super.getseatHeight(), i + INDEX_OFFSET));
			y -= getSeatDistance();
			if (i % super.getNumOfSeatsPerRow() == 0) {
				x += getRowDistance();
				y = startingPosY;
			}
		}
		for (SeatPanel p : super.seatsTop)
			seatPanel.add(p);
		
		ArrayList<Integer> alreadyBooked = new ArrayList<>();
		int endNum = getRandomNumber(1, NUM_OF_SEATS);
		
		for(int i = 0; i < endNum; i++) {
			int seatToBook = getRandomNumber(1, NUM_OF_SEATS);
			if(!alreadyBooked.contains(seatToBook)) {
				alreadyBooked.add(seatToBook);
				super.seatsTop.get(seatToBook).bookSeat();
			}
		}
	}
	
	@Override
	public void setSeatDistance(int distance) {
		this.seatDistance = super.getseatHeight() + distance;
	}

	@Override
	public void setRowDistance(int distance) { 
		this.rowDistance = super.getSeatWidth() + distance;
	}
	
	@Override
	public int getSeatDistance() { return seatDistance; }
	@Override
	public int getRowDistance() { return this.rowDistance; }
	@Override
	public JPanel getPanel() { return seatPanel; }

	public void addBathroomComponents() {
		JPanel BathoomPanel = new JPanel();
		BathoomPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		BathoomPanel.setBackground(Color.GRAY);
		BathoomPanel.setBounds(411, 0, 87, 85);
		seatPanel.add(BathoomPanel);

		JLabel lblBathroom = new JLabel("Bathroom");
		BathoomPanel.add(lblBathroom);
	}
}
