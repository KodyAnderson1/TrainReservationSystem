package GUI.AdminGUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import logic.controller.ConvertDataToFile;
import logic.controller.GUIController;
import logic.controller.RouteController;
import logic.controller.TrainAdminController;

import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ManageTrainPanel extends JPanel {
	
	private final Color defaultBackgroundColor = new Color(34, 40, 44);
	private final int mainPanelX = 173;
	private final int mainPanelY = 0;
	private final int mainPanelWidth = 661;
	private final int mainPanelHeight = 611;
	private TrainAdminController trainController;
	private JTextField textFieldTrainId;
	private JTextField textFieldNumTrainsOnRoute;
	private JTextField textFieldExistingTrainId;
	private JPanel TrainCreationPanel;
	private JPanel AddTrainCarsPanel;
	private JPanel DetailsPanel;
	private JPanel ItemsAddedPanel;
	private boolean isExpress;

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ManageTrainPanel() {
		trainController = TrainAdminController.getInstance();
		isExpress = false;
		TrainCreationPanel = new JPanel();
		AddTrainCarsPanel = new JPanel();
		DetailsPanel = new JPanel();
		ItemsAddedPanel = new JPanel();
		
		setBackground(defaultBackgroundColor);
		setBounds(mainPanelX, mainPanelY, mainPanelWidth, mainPanelHeight);
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 325, 262);
		add(tabbedPane);
		addAllLabels();
		
		TrainCreationPanel.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("New Train", null, TrainCreationPanel, null);
		TrainCreationPanel.setLayout(null);

		JCheckBox chckbxIsExpress = new JCheckBox("Express");
		chckbxIsExpress.setFocusPainted(false);
		chckbxIsExpress.setBackground(Color.LIGHT_GRAY);
		chckbxIsExpress.setBounds(82, 79, 97, 23);
		chckbxIsExpress.addActionListener(e -> {
			isExpress = false;
			if(chckbxIsExpress.isSelected())
				isExpress = true;
		});
		TrainCreationPanel.add(chckbxIsExpress);
		
		textFieldTrainId = new JTextField();
		textFieldTrainId.setBackground(Color.WHITE);
		textFieldTrainId.setBounds(10, 80, 66, 20);
		TrainCreationPanel.add(textFieldTrainId);
		textFieldTrainId.setColumns(10);

		textFieldNumTrainsOnRoute = new JTextField();
		textFieldNumTrainsOnRoute.setFont(new Font("Tahoma", Font.BOLD, 16));
		textFieldNumTrainsOnRoute.setFocusable(false);
		textFieldNumTrainsOnRoute.setEditable(false);
		textFieldNumTrainsOnRoute.setColumns(10);
		textFieldNumTrainsOnRoute.setBorder(null);
		textFieldNumTrainsOnRoute.setBackground(Color.LIGHT_GRAY);
		textFieldNumTrainsOnRoute.setBounds(287, 8, 24, 20);
		TrainCreationPanel.add(textFieldNumTrainsOnRoute);
		
		AddTrainCarsPanel.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Existing Train", null, AddTrainCarsPanel, null);
		AddTrainCarsPanel.setLayout(null);
		
		JComboBox comboBoxCarToAdd = new JComboBox(trainController.getCarTypes());
		comboBoxCarToAdd.setSelectedIndex(-1);
		comboBoxCarToAdd.setBounds(10, 72, 118, 22);
		AddTrainCarsPanel.add(comboBoxCarToAdd);
		
		Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		JComboBox comboBoxNumToAdd = new JComboBox(numbers);
		comboBoxNumToAdd.setBounds(10, 119, 40, 22);
		AddTrainCarsPanel.add(comboBoxNumToAdd);

		textFieldExistingTrainId = new JTextField();
		textFieldExistingTrainId.setBounds(10, 27, 57, 20);
		AddTrainCarsPanel.add(textFieldExistingTrainId);
		textFieldExistingTrainId.setColumns(10);
		
		DetailsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		DetailsPanel.setBackground(Color.LIGHT_GRAY);
		DetailsPanel.setBounds(344, 31, 307, 570);
		add(DetailsPanel);
		DetailsPanel.setLayout(null);

		JTextArea textAreaAllDetails = new JTextArea();
		textAreaAllDetails.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textAreaAllDetails.setEditable(false);
		textAreaAllDetails.setFocusable(false);
		textAreaAllDetails.setWrapStyleWord(true);
		textAreaAllDetails.setLineWrap(true);
		textAreaAllDetails.setBackground(Color.LIGHT_GRAY);
		textAreaAllDetails.setBounds(10, 40, 287, 519);
		DetailsPanel.add(textAreaAllDetails);
		
		ItemsAddedPanel.setBackground(Color.LIGHT_GRAY);
		ItemsAddedPanel.setBounds(10, 284, 325, 316);
		add(ItemsAddedPanel);
		ItemsAddedPanel.setLayout(null);
		
		JTextArea textAreaTrainsAdded = new JTextArea();
		textAreaTrainsAdded.setLineWrap(true);
		textAreaTrainsAdded.setFocusable(false);
		textAreaTrainsAdded.setEditable(false);
		textAreaTrainsAdded.setBackground(Color.LIGHT_GRAY);
		textAreaTrainsAdded.setBounds(10, 40, 305, 265);
		ItemsAddedPanel.add(textAreaTrainsAdded);
		
		JButton btnAddTrainCars = new JButton("Add"); 
		btnAddTrainCars.setFocusable(false);
		btnAddTrainCars.setBackground(Color.WHITE);
		btnAddTrainCars.setBounds(210, 194, 100, 29);
		btnAddTrainCars.addActionListener(e -> {
			String trainId = textFieldExistingTrainId.getText();
			if(!trainController.checkIfTrainIdIsAvailable(trainId)) {
				String carType = comboBoxCarToAdd.getSelectedItem().toString();
				int numToAdd = Integer.valueOf(comboBoxNumToAdd.getSelectedItem().toString());
				trainController.addTrainCarsToTrain(carType, trainId, numToAdd);
				trainController.saveToFile();
				textAreaAllDetails.setText(trainController.getTrainToString(trainId));
			} else { JOptionPane.showMessageDialog(null, "Train not found. Try Again!"); }
		});
		AddTrainCarsPanel.add(btnAddTrainCars);
		
		JButton btnLookUp = new JButton("Look Up");
		btnLookUp.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnLookUp.setFocusable(false);
		btnLookUp.setBackground(Color.WHITE);
		btnLookUp.setBounds(77, 27, 70, 20);
		btnLookUp.addActionListener(e -> {
			String trainId = textFieldExistingTrainId.getText();
			String displayText = trainController.getTrainToString(trainId);
			if((!trainId.isBlank()) && (displayText != null)) {
				textAreaAllDetails.setText(displayText);
			} else { JOptionPane.showMessageDialog(null, "Train not found. Try Again!"); }
		});
		AddTrainCarsPanel.add(btnLookUp);
		
		JComboBox comboBoxChooseRoute = new JComboBox(trainController.getListOfRouteNames());
		comboBoxChooseRoute.setSelectedIndex(-1);
		comboBoxChooseRoute.setFocusable(false);
		comboBoxChooseRoute.setBounds(10, 29, 169, 22);
		comboBoxChooseRoute.addActionListener(e -> {
			int index = comboBoxChooseRoute.getSelectedIndex();
			String numOfTrains = String.valueOf(trainController.getNumberOfTrainsOnRoute(index));
			textFieldNumTrainsOnRoute.setText(numOfTrains);
			textAreaAllDetails.setText(trainController.getDetailsForRouteAndTrain(index));
		});
		TrainCreationPanel.add(comboBoxChooseRoute);

		JButton btnAddTrain = new JButton("Add");
		btnAddTrain.setFocusable(false);
		btnAddTrain.setBackground(Color.WHITE);
		btnAddTrain.setBounds(210, 194, 100, 29);
		btnAddTrain.addActionListener(e -> {
			boolean isValidTrain = true;
			String trainId = textFieldTrainId.getText();
			int routeIndex = comboBoxChooseRoute.getSelectedIndex();

			if(routeIndex >= trainController.getNumberOfRoutes() || routeIndex < 0) {
				JOptionPane.showMessageDialog(null, "Route invalid. Try again!");
				isValidTrain = false;
			}
			if(trainController.checkIfTrainIdIsAvailable(trainId) && isValidTrain) {
				trainController.addTrainToSpecificRoute(trainId, routeIndex, isExpress);
				trainController.saveToFile();
				JOptionPane.showMessageDialog(null, "Train Added!");
			} else {
				JOptionPane.showMessageDialog(null, "Train ID taken. Try again");
				isValidTrain = false;
			}
		});
		TrainCreationPanel.add(btnAddTrain);
	}

	public void addAllLabels() {
		JLabel lblChooseRoute = new JLabel("Choose Route");
		lblChooseRoute.setBounds(10, 11, 100, 14);
		TrainCreationPanel.add(lblChooseRoute);
		
		JLabel lblTrainID = new JLabel("Train ID");
		lblTrainID.setBounds(10, 62, 66, 14);
		TrainCreationPanel.add(lblTrainID);
		
		JLabel lblTrainsOnRoute = new JLabel("Trains on Route:");
		lblTrainsOnRoute.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTrainsOnRoute.setBounds(158, 11, 119, 14);
		TrainCreationPanel.add(lblTrainsOnRoute);
		
		JLabel lblTrains = new JLabel("Train Id:");
		lblTrains.setBounds(10, 11, 57, 14);
		AddTrainCarsPanel.add(lblTrains);
		
		JLabel lblCarToAdd = new JLabel("Train Car to Add");
		lblCarToAdd.setBounds(10, 58, 100, 14);
		AddTrainCarsPanel.add(lblCarToAdd);
		
		JLabel lblNumberOfCars = new JLabel("Number of Cars to Add");
		lblNumberOfCars.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNumberOfCars.setBounds(10, 105, 137, 14);
		AddTrainCarsPanel.add(lblNumberOfCars);
		
		JLabel lblExistingDetails = new JLabel("Details");
		lblExistingDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblExistingDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblExistingDetails.setBounds(10, 11, 287, 36);
		DetailsPanel.add(lblExistingDetails);
		
		JLabel lblNewLabel = new JLabel("Items Added");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 305, 33);
		ItemsAddedPanel.add(lblNewLabel);
	}
}
