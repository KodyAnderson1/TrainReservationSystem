package GUI.CustomerGUI;

import logic.controller.ReservationController;
import logic.controller.ShoppingCart;
import logic.users.User;
import logic.users.UserReservation;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.swing.*;

public class Reservation extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JScrollPane resPane;
	private JTextArea resDetails;
	private ReservationController newResCon;
	private final ArrayList<UserReservation> arrayRes;
	private StringBuilder allReservationDetails;
	
	public Reservation () {
		
		setLayout(null);
		
		newResCon = ReservationController.getInstance();
		arrayRes = newResCon.getreservationList();
		allReservationDetails = new StringBuilder();
		for (UserReservation ur : arrayRes) {
			if (ur.getUserName().equalsIgnoreCase(ShoppingCart.getInstance().getUserName())) {
				allReservationDetails.append(ur);
				allReservationDetails.append("\n");
			}
		}
		
		resDetails = new JTextArea();
		
		setBackground(Color.LIGHT_GRAY);
		
		setBorder(BorderFactory.createTitledBorder("Reservation Details"));
		
		resDetails.setText(allReservationDetails.toString());
		resDetails.setEditable(false);
		
		resPane = new JScrollPane(resDetails);
		resPane.setBounds(8, 20, 645, 580);
		resPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(resPane);
		
		setVisible(true);
	}
	
}
