package GUI.CustomerGUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import GUI.CustomerGUI.CarAndSeatPanels.OuterCarPanel;
import constants.Amenities;
import constants.TrainMiles;
import logic.controller.ShoppingCart;
import logic.model.seats.Seat;

public class TicketPurchasePanel {
	private JPanel TicketPurchasePanel;
	private ShoppingCart cart = ShoppingCart.getInstance();
	OuterCarPanel ocp;

	private JButton btnRefresh;
	private JButton btnCheckout;

	public TicketPurchasePanel() {
		String[] seatArray = { "HardSeat", "HardSleeperSeat", "LuxurySeat", "LuxurySleeperSeat" };
		String[] ExpressSeatArray = { "HardSeat", "LuxurySeat" };
		ocp = new OuterCarPanel();
		TicketPurchasePanel = new JPanel();
		TicketPurchasePanel.setBackground(Color.GRAY);
		TicketPurchasePanel.setBounds(173, 0, 661, 611);
		TicketPurchasePanel.setLayout(null);
		cart.setIsExpress(false);

		TicketPurchasePanel.add(firstPurchasePanel());
		
		ocp.setSeatArray(ExpressSeatArray);
		TicketPurchasePanel.add(ocp);
		ocp.setVisible(false);

		btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(413, 263, 92, 22);
		TicketPurchasePanel.add(btnRefresh);
		
		btnRefresh.addActionListener(e -> {
			btnCheckout.setEnabled(true);
			TicketPurchasePanel.remove(ocp);
			TicketPurchasePanel.revalidate();
			TicketPurchasePanel.repaint();
		});

		btnRefresh.setFocusPainted(false);
		btnRefresh.setBackground(Color.WHITE);
		btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(515, 263, 92, 22);
		TicketPurchasePanel.add(btnCheckout);
		btnCheckout.setBackground(Color.WHITE);
		btnCheckout.setFocusPainted(false);

		btnCheckout.addActionListener(e -> {
			boolean isSameStation = cart.getDepartStation().equalsIgnoreCase(cart.getDestination());
			boolean noStation = cart.getDepartStation().isBlank() || cart.getDestination().isBlank();

			if (noStation) {
				JOptionPane.showMessageDialog(null, "Must pick a departure AND destination!");
			} else if (isSameStation) {
				JOptionPane.showMessageDialog(null, "Departure and Destination must be different");
			} else {
				btnRefresh.setVisible(true);
				btnCheckout.setEnabled(false);
				
				if (cart.getIsExpress()) {
					ocp = new OuterCarPanel();
					ocp.setSeatArray(ExpressSeatArray);
					TicketPurchasePanel.add(ocp);
				} else {
					ocp = new OuterCarPanel();
					ocp.setSeatArray(seatArray);
					TicketPurchasePanel.add(ocp);
				}
				TicketPurchasePanel.revalidate();
				TicketPurchasePanel.repaint();
			}
		});

		btnRefresh.setVisible(false);
	}

	public JPanel getPanel() {
		return TicketPurchasePanel;
	}

	@SuppressWarnings("unused")
	public JPanel firstPurchasePanel() {
		JPanel FirstPurchasePanel = new JPanel();
		FirstPurchasePanel.setBounds(54, 21, 553, 231);
		FirstPurchasePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		FirstPurchasePanel.setBackground(Color.LIGHT_GRAY);
		FirstPurchasePanel.setLayout(null);
		FirstPurchasePanel.add(amenitiesChoicePanel());

		JPanel MiscPanel = new JPanel();
		MiscPanel.setBounds(338, 143, 205, 77);
		MiscPanel.setLayout(null);
		MiscPanel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0)), "Misc",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		MiscPanel.setBackground(Color.LIGHT_GRAY);
		FirstPurchasePanel.add(MiscPanel);

		JComboBox<String> comboBoxDestination = new JComboBox<String>();
		comboBoxDestination.setEnabled(false);
		comboBoxDestination.setBounds(10, 82, 125, 22);
		comboBoxDestination.addActionListener(e -> {
			try {
				cart.setDestination(comboBoxDestination.getSelectedItem().toString());
			} catch (Exception e1) {
			}
		});
		FirstPurchasePanel.add(comboBoxDestination);

		JComboBox<?> comboBoxDeparture = new JComboBox<Object>(cart.getListOfDepartures().toArray());
		comboBoxDeparture.setSelectedIndex(-1);
		comboBoxDeparture.setBounds(10, 37, 125, 22);
		comboBoxDeparture.addActionListener(e -> {
			try {
				comboBoxDestination.setEnabled(true);
				int index = comboBoxDeparture.getSelectedIndex();
				ArrayList<String> destStringList = cart.getListOfDestinations(index);
				comboBoxDestination.removeAllItems();
				for (String s : destStringList)
					comboBoxDestination.addItem(s);
				cart.setDepartStation(comboBoxDeparture.getSelectedItem().toString());
			} catch (Exception e1) {
			}
		});
		FirstPurchasePanel.add(comboBoxDeparture);

		JLabel lblDeparture = new JLabel("Departure");
		lblDeparture.setBounds(10, 17, 125, 19);
		FirstPurchasePanel.add(lblDeparture);

		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setBounds(10, 62, 125, 19);
		FirstPurchasePanel.add(lblDestination);

		JTextArea textAreaInstructions = new JTextArea();
		textAreaInstructions.setFocusable(false);
		textAreaInstructions.setEditable(false);
		textAreaInstructions.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textAreaInstructions.setText("1) Choose Departure Station\n"
				+ "2) Choose Destination\n"
				+ "3) Choose Express / Not\n"
				+ "4) Choose Departing Time\n"
				+ "5) Choose Amenities & Misc\n"
				+ "6) Checkout\n"
				+ "7) Choose Seat\n"
				+ "8) Confirm Purchase\n\n"
				+ "Note: Express Trips take\n"
				+ "12hrs for arrival,\n"
				+ "Non-Express take 24hrs.");
		textAreaInstructions.setBackground(Color.LIGHT_GRAY);
		textAreaInstructions.setBounds(138, 20, 194, 194);
		FirstPurchasePanel.add(textAreaInstructions);
		ArrayList<Seat> seats = cart.getListOfSeatTypes();

		String[] isExpressStringArr = { "Express", "Regular" };
		JComboBox<String> comboBoxExpress = new JComboBox<String>();
		comboBoxExpress.addItem(isExpressStringArr[0]);
		comboBoxExpress.addItem(isExpressStringArr[1]);
		comboBoxExpress.setSelectedIndex(-1);
		comboBoxExpress.setBounds(10, 132, 125, 22);
		comboBoxExpress.addActionListener(e -> {
			if (comboBoxExpress.getSelectedItem().toString().equalsIgnoreCase("Express"))
				cart.setIsExpress(true);
			if (comboBoxExpress.getSelectedItem().toString().equalsIgnoreCase("Regular"))
				cart.setIsExpress(false);
		});
		FirstPurchasePanel.add(comboBoxExpress);

		JLabel lblExpress = new JLabel("Speed");
		lblExpress.setBounds(10, 112, 125, 19);
		FirstPurchasePanel.add(lblExpress);
		
		JLabel time = new JLabel("Departing Time");
		time.setBounds(10, 162, 125, 19);
		FirstPurchasePanel.add(time);
		
		String[] timeArr = { "0600", "0700", "0800", "0900", "1000", "1100", "1200", "1300", "1400", "1500", "1600", "1700" };
		JComboBox<String> timeExpress = new JComboBox<String>();
		for(int i = 0; i < timeArr.length; i++)
			timeExpress.addItem(timeArr[i]);
		timeExpress.setSelectedIndex(-1);
		timeExpress.setBounds(10, 182, 125, 22);
		timeExpress.addActionListener(e -> {
			for(int i = 0; i < timeArr.length; i++)
				if (timeExpress.getSelectedItem().toString().equalsIgnoreCase(timeArr[i]))
					cart.setTime(timeArr[i]);
		});
		FirstPurchasePanel.add(timeExpress);

		JCheckBox member = new JCheckBox("Premium Membership");
		member.setFocusPainted(false);
		member.setBackground(Color.LIGHT_GRAY);
		member.setBounds(6, 18, 193, 23);
		MiscPanel.add(member);

		JCheckBox insurance = new JCheckBox("Trip Insurance");
		insurance.setBounds(6, 47, 193, 23);
		insurance.setFocusPainted(false);
		insurance.setBackground(Color.LIGHT_GRAY);
		MiscPanel.add(insurance);

		class ButtonListener implements ItemListener {
			@Override
			public void itemStateChanged(ItemEvent event) {
				cart.resetMiscList();
				if (member.isSelected()) {
					cart.addToMiscList(TrainMiles.MEMBERSHIP);
					cart.setMembership(true);
				}
				else
					cart.setMembership(false);
				
				if (insurance.isSelected())
					cart.addToMiscList(TrainMiles.INSURANCE);
			}
		}
		insurance.addItemListener(new ButtonListener());
		member.addItemListener(new ButtonListener());
		FirstPurchasePanel.add(MiscPanel);

		return FirstPurchasePanel;
	}

	public JPanel amenitiesChoicePanel() {
		JPanel AmenitiesPanel = new JPanel();
		AmenitiesPanel.setLayout(null);
		AmenitiesPanel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0)),
						"Amenities", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		AmenitiesPanel.setBackground(Color.LIGHT_GRAY);
		AmenitiesPanel.setBounds(338, 17, 205, 126);

		JCheckBox chckbxBreakfast = new JCheckBox("Breakfast");
		chckbxBreakfast.setFocusPainted(false);
		chckbxBreakfast.setBackground(Color.LIGHT_GRAY);
		chckbxBreakfast.setBounds(6, 17, 99, 23);
		AmenitiesPanel.add(chckbxBreakfast);

		JCheckBox chckbxDinner = new JCheckBox("Dinner");
		chckbxDinner.setFocusPainted(false);
		chckbxDinner.setBackground(Color.LIGHT_GRAY);
		chckbxDinner.setBounds(6, 43, 80, 23);
		AmenitiesPanel.add(chckbxDinner);

		JCheckBox chckbxParking = new JCheckBox("Parking");
		chckbxParking.setFocusPainted(false);
		chckbxParking.setBackground(Color.LIGHT_GRAY);
		chckbxParking.setBounds(6, 68, 80, 23);
		AmenitiesPanel.add(chckbxParking);

		JCheckBox chckbxLunch = new JCheckBox("Lunch");
		chckbxLunch.setFocusPainted(false);
		chckbxLunch.setBackground(Color.LIGHT_GRAY);
		chckbxLunch.setBounds(107, 17, 80, 23);
		AmenitiesPanel.add(chckbxLunch);

		JCheckBox chckbxDrinks = new JCheckBox("Drinks");
		chckbxDrinks.setFocusPainted(false);
		chckbxDrinks.setBackground(Color.LIGHT_GRAY);
		chckbxDrinks.setBounds(107, 43, 80, 23);
		AmenitiesPanel.add(chckbxDrinks);

		JCheckBox chckbxWifi = new JCheckBox("WiFi");
		chckbxWifi.setFocusPainted(false);
		chckbxWifi.setBackground(Color.LIGHT_GRAY);
		chckbxWifi.setBounds(107, 68, 80, 23);
		AmenitiesPanel.add(chckbxWifi);

		JCheckBox chckbxPhoto = new JCheckBox("Photo");
		chckbxPhoto.setFocusPainted(false);
		chckbxPhoto.setBackground(Color.LIGHT_GRAY);
		chckbxPhoto.setBounds(6, 94, 80, 23);
		AmenitiesPanel.add(chckbxPhoto);

		JCheckBox chckbxLaundry = new JCheckBox("Laundry");
		chckbxLaundry.setFocusPainted(false);
		chckbxLaundry.setBackground(Color.LIGHT_GRAY);
		chckbxLaundry.setBounds(107, 94, 80, 23);
		AmenitiesPanel.add(chckbxLaundry);

		class ButtonListener implements ItemListener {
			@Override
			public void itemStateChanged(ItemEvent e) {
				cart.resetAmenitiesList();

				if (chckbxBreakfast.isSelected())
					cart.addToAmenitiesList(Amenities.Breakfast);
				if (chckbxLunch.isSelected())
					cart.addToAmenitiesList(Amenities.Lunch);
				if (chckbxDinner.isSelected())
					cart.addToAmenitiesList(Amenities.Dinner);
				if (chckbxDrinks.isSelected())
					cart.addToAmenitiesList(Amenities.Drinks);
				if (chckbxParking.isSelected())
					cart.addToAmenitiesList(Amenities.Parking);
				if (chckbxWifi.isSelected())
					cart.addToAmenitiesList(Amenities.WiFi);
				if (chckbxPhoto.isSelected())
					cart.addToAmenitiesList(Amenities.Photo);
				if (chckbxLaundry.isSelected())
					cart.addToAmenitiesList(Amenities.Laundry);
			}

		}

		chckbxBreakfast.addItemListener(new ButtonListener());
		chckbxDinner.addItemListener(new ButtonListener());
		chckbxParking.addItemListener(new ButtonListener());
		chckbxLunch.addItemListener(new ButtonListener());
		chckbxDrinks.addItemListener(new ButtonListener());
		chckbxWifi.addItemListener(new ButtonListener());
		chckbxPhoto.addItemListener(new ButtonListener());
		chckbxLaundry.addItemListener(new ButtonListener());

		return AmenitiesPanel;
	}
}