package GUI.CustomerGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class FAQPanel extends JPanel {
	
	private JTextArea info;
	private JLabel title;
	private JScrollPane sPane;
	private static final long serialVersionUID = 1L;
	
	public FAQPanel() {
		
		setBackground(Color.WHITE);
		setLayout(null);
		
		title = new JLabel();
		info = new JTextArea();
		
		title.setText("Frequently Asked Questions");
		title.setFont(new Font("Times", 1, 40));

		title.setBounds(10, 5, 641, 50);
		title.setBorder(new LineBorder(new Color(0, 0, 0)));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.BLACK);
		
		info.setText("1. Do I have to create an account to purchase a ticket?\n"
				+ "Yes, customers must create an account before purchasing a ticket.\n\n"
				+ "2. What do I do if I miss my connecting train?\n"
				+ "Speak to a staff member at the station to book next available train.\n\n"
				+ "3. What benefits does the premium membership provide?\n"
				+ "By purchasing an annual premium membership, customers will earn points for every "
				+ "mile \ntaveled to be used as a discount towards their next trip.\n\n"
				+ "4. Am I able to cancel my ticket at anytime?\n"
				+ "Customers may cancel there ticket up to 24hrs before departure, under the \"Cancel "
				+ "\nReservations\" tab.\n\n"
				+ "5. Am I able to upgrade my ticket to a higher tier?\n"
				+ "Not at this present time through the website. Arrive early for your trip and ask available \n"
				+ "staff at the station for assistance and the potential to upgrade your travel experience.\n\n"
				+ "6. Are transfers included with my ticket purchase?\n"
				+ "Yes, everything necessary for you to get to your selected destination is included with \nticket purchase.\n\n"
				+ "7. How much luggage can I bring on my trip?\n"
				+ "Passengers are allowed two carry-on-bags each, either not to exceed 50lbs.\n\n"
				+ "8. Do Rooms have bathrooms and showers?\n"
				+ "Yes, all rooms have in-room bathrooms and showers. All customers have access to \nrestrooms and showers.\n\n"
				+ "9. Are meals included?\n"
				+ "No, snacks and water are provided, but meals and assorted drinks are available as \nan additional service when "
				+ " your ticket.\n\n"
				+ "10. When will I recieve my travel information?\n"
				+ "Customers that complete the purchase of their ticket may login in at anytime and \ncheck their trip details under "
				+ "the \"Reservation Details\" tab. Otherwise, upon checking in at \nthe station before depature you will recieve your "
				+ "booklet containing all of your travel details, \ntimes, stops and any other related information.\n\n"
				+ "11. What ID requirements are required for travel?\n"
				+ "One form of valid legal state, federal or passport ID is required before being allowed to \nboard the train.\n\n"
				+ "12. How far in advance can I book my trip?\n"
				+ "Passengers are capable of booking up to 24 months in advance.\n\n"
				+ "\tAny additional questions do not hesitate to call customer service "
				+ "\n\t            or ask available staff upon your arrival at the station.");
		
		info.setFont(new Font("Times", 6, 16));
		info.setEditable(false);
		
		sPane = new JScrollPane(info);
		sPane.setBounds(5, 60, 645, 550);
		sPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(title);
		add(sPane);
		
		setVisible(true);
		
	}

}
