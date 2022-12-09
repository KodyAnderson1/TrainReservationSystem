package GUI.CustomerGUI.CarAndSeatPanels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import logic.controller.ReservationController;
import logic.controller.ShoppingCart;
import logic.users.UserArrayList;

@SuppressWarnings("serial")
public class CheckoutConfirmation extends JDialog {

	private ShoppingCart cart;
	private String amenities;
	private String username;
	private String depart;
	private String destination;
	private double totalMiles;
	private double total;

	public CheckoutConfirmation() {
		getContentPane().setBackground(Color.WHITE);
		cart = ShoppingCart.getInstance();
		setAmenities(cart.displayAmenities());
		setUsername(cart.getUserName());
		setDepart(cart.getDepartStation());
		setDestination(cart.getDestination());
		setTotalMiles(cart.getTotalMiles());
		setTotal(cart.getTotal());
		setBounds(0, 0, 600, 300);
		setTitle("Ticket Confirmation");

		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		addStaticFeatures();
		addDynamicFeatures();
	}

	public void addStaticFeatures() {
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(10, 11, 99, 27);
		getContentPane().add(lblUsername);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotal.setBounds(419, 178, 53, 27);
		getContentPane().add(lblTotal);

		JLabel lblDeparture = new JLabel("Departure:");
		lblDeparture.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDeparture.setBounds(10, 49, 99, 27);
		getContentPane().add(lblDeparture);

		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDestination.setBounds(10, 87, 99, 27);
		getContentPane().add(lblDestination);

		JLabel lblTripMiles = new JLabel("Trip Miles:");
		lblTripMiles.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTripMiles.setBounds(10, 125, 99, 27);
		getContentPane().add(lblTripMiles);
		
		JLabel departTime = new JLabel("Departing Time:");
		departTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		departTime.setBounds(10, 163, 130, 27);
		getContentPane().add(departTime);
		
		JLabel arriveTime = new JLabel("Arriving Time:");
		arriveTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		arriveTime.setBounds(10, 201, 130, 27);
		getContentPane().add(arriveTime);
	}

	public void addDynamicFeatures() {
		JTextArea amenitiesCheckoutArea = new JTextArea();
		amenitiesCheckoutArea.setColumns(2);
		amenitiesCheckoutArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		amenitiesCheckoutArea.setEditable(false);
		amenitiesCheckoutArea.setLineWrap(true);
		amenitiesCheckoutArea.setBackground(Color.WHITE);
		amenitiesCheckoutArea.setBounds(419, 11, 155, 166);
		amenitiesCheckoutArea.setText(getAmenities());
		getContentPane().add(amenitiesCheckoutArea);

		JLabel lblUsernameDynamic = new JLabel(getUsername());
		lblUsernameDynamic.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsernameDynamic.setBounds(107, 11, 140, 27);
		getContentPane().add(lblUsernameDynamic);

		JLabel lblDepartureDynamic = new JLabel(getDepart());
		lblDepartureDynamic.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDepartureDynamic.setBounds(107, 49, 140, 27);
		getContentPane().add(lblDepartureDynamic);

		JLabel lblDestinationDynamic = new JLabel(getDestination());
		lblDestinationDynamic.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDestinationDynamic.setBounds(107, 87, 140, 27);
		getContentPane().add(lblDestinationDynamic);

		JLabel lblTripMilesDynamic = new JLabel(String.format("%.2f", getTotalMiles()));
		lblTripMilesDynamic.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTripMilesDynamic.setBounds(107, 125, 140, 27);
		getContentPane().add(lblTripMilesDynamic);
		
		JLabel d_Time = new JLabel(cart.getDepartTime());
		d_Time.setFont(new Font("Tahoma", Font.PLAIN, 18));
		d_Time.setBounds(140, 163, 90, 27);
		getContentPane().add(d_Time);
		
		JLabel a_Time = new JLabel(cart.getArrivalTime());
		a_Time.setFont(new Font("Tahoma", Font.PLAIN, 18));
		a_Time.setBounds(140, 201, 90, 27);
		getContentPane().add(a_Time);

		JLabel lblTotalDynamic = new JLabel(String.format("$ %.2f", getTotal()));
		lblTotalDynamic.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotalDynamic.setBounds(482, 178, 92, 27);
		getContentPane().add(lblTotalDynamic);

		JButton btnConfirm = new JButton("Confirm Purchase");
		btnConfirm.setFocusable(false);
		btnConfirm.setBackground(Color.WHITE);
		btnConfirm.setFocusPainted(false);
		btnConfirm.setBounds(419, 211, 155, 39);
		btnConfirm.addActionListener(e -> {
			dispose();
			cart.addPersonToTrain();
			UserArrayList dillon = new UserArrayList();
			dillon.updateUser(cart.getUserName(), cart.getMembership());
			String total = String.format("%.2f", cart.getTotal());
			ReservationController.getInstance().addReservationToList(new String[] {cart.getUserName(), cart.getReservationID(), total, cart.getDepartTime()});
			ReservationController.getInstance().saveReservationsToFile();
			JOptionPane.showMessageDialog(null,
					String.format("Ticket Purchased!%nReservation ID: %s", cart.getReservationID()));
		});
		getContentPane().add(btnConfirm);
	}

	public String getAmenities() {
		return amenities;
	}

	public String getUsername() {
		return username;
	}

	public String getDepart() {
		return depart;
	}

	public String getDestination() {
		return destination;
	}

	public double getTotalMiles() {
		return totalMiles;
	}

	public double getTotal() {
		return total;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setTotalMiles(double totalMiles) {
		this.totalMiles = totalMiles;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
