package GUI.AdminGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.controller.RouteController;
import logic.model.Route;

/**
 * inner panel for RouteCreationPanel
 *
 */
public class ExistingRoutePanel extends JPanel {
	
	private RouteController routeController;
	private JTextField textFieldRouteName;
	private JTextField textFieldRouteId;
	private JTextArea textAreaRouteDetails;
	private JComboBox comboBoxChooseRoute;
	private JComboBox comboBoxDeparture;
	private JComboBox comboBoxDestination;
	private Route route;
	
	

	public ExistingRoutePanel() {
		routeController = RouteController.getInstance();
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		addAllLabels();
		addAllTextFields();
		addAllComboBoxes();
		addAllTextAreas();
		addAllButtons();
	}
	
	public void addAllButtons() {
		JButton btnExistingRouteSubmit = new JButton("Change");
		btnExistingRouteSubmit.setFocusable(false);
		btnExistingRouteSubmit.setBounds(448, 187, 100, 29);
		btnExistingRouteSubmit.setBackground(Color.WHITE);
		btnExistingRouteSubmit.addActionListener(e -> {
			int index = comboBoxChooseRoute.getSelectedIndex();
			routeController.changeExistingRoute(
					index, 
					textFieldRouteId.getText().toString(), 
					textFieldRouteName.getText().toString(), 
					comboBoxDeparture.getSelectedItem().toString(), 
					comboBoxDestination.getSelectedItem().toString());
			routeController.saveRoutesToFile();
			textAreaRouteDetails.setText(routeController.getSpecificRoute(index).toString());
		});
		add(btnExistingRouteSubmit);
		
//		JButton btnRefresh = new JButton("Refresh");
//		btnRefresh.setFocusable(false);
//		btnRefresh.setBounds(357, 187, 82, 29);
//		btnRefresh.setBackground(Color.WHITE);
//		btnRefresh.addActionListener(e -> {
//			//
//		});
//		add(btnRefresh);
	}
	
	public void addAllTextAreas() {
		textAreaRouteDetails = new JTextArea();
		textAreaRouteDetails.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textAreaRouteDetails.setLineWrap(true);
		textAreaRouteDetails.setEditable(false);
		textAreaRouteDetails.setBackground(Color.LIGHT_GRAY);
		textAreaRouteDetails.setBounds(278, 32, 270, 144);
		add(textAreaRouteDetails);
	}
	
	public void addAllComboBoxes() {
		comboBoxChooseRoute = new JComboBox(routeController.getListOfRouteNames());
		comboBoxChooseRoute.setBounds(10, 33, 248, 22);
		comboBoxChooseRoute.setSelectedIndex(-1);
		comboBoxChooseRoute.addActionListener(e -> {
			if(e.getSource() == comboBoxChooseRoute) {
				route = routeController.getSpecificRoute(comboBoxChooseRoute.getSelectedIndex());
				textFieldRouteName.setText(route.getNameOfRoute());
				textFieldRouteId.setText(route.getRouteID());
				textAreaRouteDetails.setText(route.toString());
			}
		});
		add(comboBoxChooseRoute);

		comboBoxDeparture = new JComboBox(routeController.getTrainStations().toArray());
		comboBoxDeparture.setBounds(10, 88, 119, 22);
		comboBoxDeparture.setSelectedIndex(-1);
		add(comboBoxDeparture);

		comboBoxDestination = new JComboBox(routeController.getTrainStations().toArray());
		comboBoxDestination.setBounds(139, 88, 119, 22);
		comboBoxDestination.setSelectedIndex(-1);
		add(comboBoxDestination);
	}
	
	public void addAllTextFields() {
		textFieldRouteName = new JTextField();
		textFieldRouteName.setBounds(110, 154, 148, 22);
		add(textFieldRouteName);
		textFieldRouteName.setColumns(10);
		
		textFieldRouteId = new JTextField();
		textFieldRouteId.setColumns(10);
		textFieldRouteId.setBounds(110, 121, 74, 22);
		add(textFieldRouteId);
	}
	
	public void addAllLabels() {
		JLabel lblRouteDetails = new JLabel("Route Details");
		lblRouteDetails.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRouteDetails.setBounds(278, 11, 100, 22);
		add(lblRouteDetails);
		
		JLabel lblChooseRoute = new JLabel("Choose Route");
		lblChooseRoute.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChooseRoute.setBounds(10, 11, 248, 22);
		add(lblChooseRoute);
		
		JLabel lblChangeDeparture = new JLabel("Change Departure");
		lblChangeDeparture.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChangeDeparture.setBounds(10, 66, 119, 22);
		add(lblChangeDeparture);
		
		JLabel lblChangeDestination = new JLabel("Change Destination");
		lblChangeDestination.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChangeDestination.setBounds(139, 66, 119, 22);
		add(lblChangeDestination);

		JLabel lblChangeRouteName = new JLabel("Name of Route:");
		lblChangeRouteName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChangeRouteName.setBounds(10, 154, 90, 22);
		add(lblChangeRouteName);
		
		JLabel lblRouteId = new JLabel("Route Id:");
		lblRouteId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRouteId.setBounds(10, 121, 90, 22);
		add(lblRouteId);
	}

	
}
