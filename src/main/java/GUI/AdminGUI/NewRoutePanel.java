package GUI.AdminGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import logic.controller.RouteController;

/**
 * inner panel for RouteCreationPanel
 *
 */
public class NewRoutePanel extends JPanel {
	
	private RouteController routeController;
	private JTextField textFieldRouteName;
	private JTextField textFieldRouteId;
	private JTextArea textAreaExistingRoutes;
	private String destination;
	private String departure;
	private String nameOfRoute;
	private String routeId;

	public NewRoutePanel() {
		routeController = RouteController.getInstance();
		destination = "";
		departure = "";
		nameOfRoute = "";
		routeId = "";

		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		
		addAllLabels();
		addAllComboBoxes();
		allAddTextFields();
		allTextAreaAndScrollBar();
		allAllButtons();
	}
	
	public void allAllButtons() {
		JButton btnNewRouteSubmit = new JButton("Submit");
		btnNewRouteSubmit.setBackground(Color.WHITE);
		btnNewRouteSubmit.setBounds(448, 197, 100, 29);
		btnNewRouteSubmit.setFocusable(false);
		btnNewRouteSubmit.addActionListener(e -> {
			nameOfRoute = textFieldRouteName.getText();
			routeId = textFieldRouteId.getText();
			if (departure.equalsIgnoreCase(destination)) {
				JOptionPane.showMessageDialog(null, "Departure and Destination cannot be the same!");
			} else if (routeController.addRoute(routeId, nameOfRoute, departure, destination)) {
				routeController.saveRoutesToFile();
				textFieldRouteName.setText("");
				textFieldRouteId.setText("");
				textAreaExistingRoutes.setText(routeController.getRouteInfoForPanel());
				textAreaExistingRoutes.repaint();
				textAreaExistingRoutes.revalidate();
			} else {
				JOptionPane.showMessageDialog(null, "Fields must be filled out!");
			}
		});
		add(btnNewRouteSubmit);
	}
	
	
	public void allTextAreaAndScrollBar() {
		textAreaExistingRoutes = new JTextArea();
		textAreaExistingRoutes.setFocusable(false);
		textAreaExistingRoutes.setForeground(Color.BLACK);
		textAreaExistingRoutes.setLineWrap(true);
		textAreaExistingRoutes.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textAreaExistingRoutes.setBackground(Color.LIGHT_GRAY);
		textAreaExistingRoutes.setBounds(141, 35, 286, 189);
		textAreaExistingRoutes.setEditable(false);
		textAreaExistingRoutes.setText(routeController.getRouteInfoForPanel());
		
		JScrollPane scrollBar = new JScrollPane(textAreaExistingRoutes);
		scrollBar.setBorder(null);
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollBar.setBounds(141, 35, 286, 189);
		add(scrollBar);
	}
	
	public void allAddTextFields() {
		textFieldRouteName = new JTextField();
		textFieldRouteName.setBackground(Color.WHITE);
		textFieldRouteName.setBounds(10, 155, 117, 20);
		add(textFieldRouteName);
		textFieldRouteName.setColumns(10);	
		
		textFieldRouteId = new JTextField();
		textFieldRouteId.setBackground(Color.WHITE);
		textFieldRouteId.setColumns(10);
		textFieldRouteId.setBounds(10, 206, 117, 20);
		add(textFieldRouteId);
	}
	
	public void addAllLabels() {
		JLabel lblDeparture = new JLabel("Departure");
		lblDeparture.setBounds(10, 11, 84, 22);
		add(lblDeparture);
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setBounds(10, 66, 84, 22);
		add(lblDestination);
		
		JLabel lblNameOfRoute = new JLabel("Name of Route");
		lblNameOfRoute.setBounds(10, 135, 84, 22);
		add(lblNameOfRoute);
		
		JLabel lblRouteId = new JLabel("Route ID");
		lblRouteId.setBounds(10, 186, 84, 22);
		add(lblRouteId);
		
		JLabel lblExistingRoutes = new JLabel("Existing Routes");
		lblExistingRoutes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExistingRoutes.setBounds(142, 11, 130, 22);
		add(lblExistingRoutes);
	}

	public void addAllComboBoxes() {
		JComboBox comboBoxDeparture = new JComboBox(routeController.getTrainStations().toArray());
		comboBoxDeparture.setFocusable(false);
		comboBoxDeparture.setBounds(10, 33, 117, 22);
		comboBoxDeparture.setSelectedIndex(-1);
		comboBoxDeparture.addActionListener(e -> {
			if(e.getSource() == comboBoxDeparture)
				departure = comboBoxDeparture.getSelectedItem().toString();
		});
		add(comboBoxDeparture);
		
		JComboBox comboBoxDestination = new JComboBox(routeController.getTrainStations().toArray());
		comboBoxDestination.setFocusable(false);
		comboBoxDestination.setSelectedIndex(-1);
		comboBoxDestination.setBounds(10, 88, 117, 22);
		comboBoxDestination.addActionListener(e -> {
			if(e.getSource() == comboBoxDestination) {
				destination = comboBoxDestination.getSelectedItem().toString();
			}
		});
		add(comboBoxDestination);
	}
}
