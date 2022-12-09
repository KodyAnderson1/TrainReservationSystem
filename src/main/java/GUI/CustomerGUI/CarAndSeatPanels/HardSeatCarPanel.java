package GUI.CustomerGUI.CarAndSeatPanels;

import javax.swing.JPanel;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import constants.MaxCarSeats;



public class HardSeatCarPanel extends CarDetailsGUI {

	JPanel seatPanel = new JPanel();
	private int seatDistance;
	private int rowDistance;

	public HardSeatCarPanel() {
		super(25, 25, MaxCarSeats.HardSeatCar.NUMBER_OF_SEATS, 3);
		
		seatPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		seatPanel.setBounds(27, 30, 498, 205);
		seatPanel.setLayout(null);
		
		setRowDistance(22);
		setSeatDistance(2);

		setUpBottomSeats();
		setUpTopSeats();
	}

	@Override
	public JPanel getPanel() { return seatPanel; }

	@Override
	public void setUpBottomSeats() {
		final int NUM_OF_SEATS = super.getNumOfSeats() / 2;
		int x = 92;
		int y = 169;
		
		for (int i = 1; i <= NUM_OF_SEATS; i++) {
			super.seatsBottom.add(new SeatPanel(x, y, super.getSeatWidth(), super.getseatHeight(), i));
			y -= getSeatDistance();
			if (i % super.getNumOfSeatsPerRow() == 0) {
				x += getRowDistance();
				y = 169;
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
		final int INDEX_OFFSET = 18;
		
		int x = 92;
		int y = 65;

		for (int i = 1; i <= NUM_OF_SEATS; i++) {
			super.seatsTop.add(new SeatPanel(x, y, super.getSeatWidth(), super.getseatHeight(), i + INDEX_OFFSET));
			y -= getSeatDistance();
			if (i % super.getNumOfSeatsPerRow() == 0) {
				x += getRowDistance();
				y = 65;
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
