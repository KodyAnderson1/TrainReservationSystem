package GUI.AdminGUI;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import logic.controller.ReservationController;
import logic.users.UserReservation;

public class AdminManageReservationsPanel extends JPanel {
	 
	private final Color manageReservationBackroundColor = new Color(34, 40, 44);
	private JPanel manageReservations;
	private ReservationController resCon = ReservationController.getInstance();
	JPanel ReservationPanel = new JPanel();
	UserReservation uRes = resCon.getreservationList().get(1);
	
	public AdminManageReservationsPanel() {
		setBackground(manageReservationBackroundColor);
		setBounds(173, 0, 661, 611);
		setLayout(null);
		manageReservations = new JPanel();
		manageReservations.setBounds(49, 32, 563, 550);
		manageReservations.setBackground(Color.LIGHT_GRAY);
		manageReservations.setLayout(null);
		add(manageReservations);
		manageReservations.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0)), 
				"Manage Reservations", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblReservation = new JLabel("Reservation ID:");
		lblReservation.setBounds(140, 55, 100, 22);
		manageReservations.add(lblReservation);
		
		JTextField txtReservation = new JTextField();
		txtReservation.setBackground(Color.WHITE);
		txtReservation.setBounds(230, 55, 100, 22);
		txtReservation.setText(uRes.getReservationID());
		manageReservations.add(txtReservation);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(225, 90, 82, 29);
		btnSearch.addActionListener(e -> {
			if(manageReservations.isVisible() == true) {
				manageReservations.setVisible(false);
				add(ReservationPanel());
			}
			
		});
		manageReservations.add(btnSearch);
	}
		public JPanel ReservationPanel() {
			JPanel resPanel = new JPanel();
			resPanel.setLayout(null);
			resPanel.setBackground(Color.LIGHT_GRAY);
			resPanel.setBounds(49, 32, 563, 550);
			
			resPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0)), 
					"Reservation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

			JLabel lblUsername = new JLabel("Username:");
			lblUsername.setBounds(100, 40, 70, 22);
			resPanel.add(lblUsername);
			
			JTextField txtUsername = new JTextField();
			txtUsername.setBackground(Color.WHITE);
			txtUsername.setBounds(230, 40, 115, 22);
			txtUsername.setText(uRes.getUserName());
			resPanel.add(txtUsername);
			
			//JLabel lblname = new JLabel(getUsername(getIndex()));
			//lblname.setBounds(170, 40, 70, 22);
			//resPanel.add(lblname);
			
			JLabel lblResID = new JLabel("Reservation ID:");
			lblResID.setBounds(100, 80, 85, 22);
			resPanel.add(lblResID);
			
			JTextField txtResID = new JTextField();
			txtResID.setBackground(Color.WHITE);
			txtResID.setBounds(230, 80, 115, 22);
			txtResID.setText(uRes.getReservationID());
			resPanel.add(txtResID);
			
			JLabel lblDest = new JLabel("Destination:");
			lblDest.setBounds(100, 120, 85, 22);
			resPanel.add(lblDest);
			
			JTextField txtDest = new JTextField();
			txtDest.setBackground(Color.WHITE);
			txtDest.setBounds(230, 120, 115, 22);
			txtDest.setText(uRes.getTripDestination());
			resPanel.add(txtDest);
			
			JLabel lblMiles = new JLabel("Departure:");
			lblMiles.setBounds(100, 160, 85, 22);
			resPanel.add(lblMiles);
			
			JTextField txtMiles = new JTextField();
			txtMiles.setBackground(Color.WHITE);
			txtMiles.setBounds(230, 160, 115, 22);
			txtMiles.setText(uRes.getDeparture());
			resPanel.add(txtMiles);
			
			JLabel lblMilesToDest = new JLabel("Express:");
			lblMilesToDest.setBounds(100, 200, 115, 22);
			resPanel.add(lblMilesToDest);
			
			JTextField txtMilesToDest = new JTextField();
			txtMilesToDest.setBackground(Color.WHITE);
			txtMilesToDest.setBounds(230, 200, 115, 22);
			txtMilesToDest.setText(String.format("%B", uRes.isExpress()));
			resPanel.add(txtMilesToDest);
			
			JLabel lblDepartTime = new JLabel("Depart Time:");
			lblDepartTime.setBounds(100, 240, 115, 22);
			resPanel.add(lblDepartTime);
			
			JTextField txtDepartTime = new JTextField();
			txtDepartTime.setBackground(Color.WHITE);
			txtDepartTime.setBounds(230, 240, 115, 22);
			txtDepartTime.setText(uRes.getTime());
			resPanel.add(txtDepartTime);
			
			JLabel lblAmenities = new JLabel("Seat:");
			lblAmenities.setBounds(100, 280, 85, 22);
			resPanel.add(lblAmenities);
			
			JTextField txtAmenities = new JTextField();
			txtAmenities.setBackground(Color.WHITE);
			txtAmenities.setBounds(230, 280, 115, 22);
			txtAmenities.setText(uRes.getSeat());
			resPanel.add(txtAmenities);
			
			JLabel lblCost = new JLabel("Cost:");
			lblCost.setBounds(100, 320, 85, 22);
			resPanel.add(lblCost);
			
			JTextField txtCost = new JTextField();
			txtCost.setBackground(Color.WHITE);
			txtCost.setBounds(230, 320, 115, 22);
			txtCost.setText(String.format("%.2f",uRes.getCost()));
			resPanel.add(txtCost);
			
			JButton btnEditRes = new JButton("Edit Reservation");
			btnEditRes.setBackground(Color.WHITE);
			btnEditRes.setBounds(50, 500, 160, 29);
			btnEditRes.addActionListener(e -> {
					resPanel.setVisible(false);
					add(EditReservationPanel());
				
			});
			resPanel.add(btnEditRes);
			
			JButton btnDeleteRes = new JButton("Delete Reservation");
			btnDeleteRes.setBackground(Color.WHITE);
			btnDeleteRes.setBounds(235, 500, 160, 29);
			resPanel.add(btnDeleteRes);
			
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setBackground(Color.WHITE);
			btnCancel.setBounds(420, 500, 82, 29);
			btnCancel.addActionListener(e -> {
				resPanel.setVisible(false); 
				manageReservations.setVisible(true);
				
			});
			resPanel.add(btnCancel);
			return resPanel;
	}
		
		public JPanel EditReservationPanel() {
			JPanel editPanel = new JPanel();
			editPanel.setLayout(null);
			editPanel.setBackground(Color.LIGHT_GRAY);
			editPanel.setBounds(49, 32, 563, 550);
			
			editPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0)), 
					"Edit Reservation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

			JLabel lblDest = new JLabel("Username:");
			lblDest.setBounds(100, 40, 85, 22);
			editPanel.add(lblDest);
			
			JTextField txtDest = new JTextField();
			txtDest.setBackground(Color.WHITE);
			txtDest.setBounds(170, 40, 100, 22);
			editPanel.add(txtDest);
			
			JLabel lblMiles = new JLabel("Departure:");
			lblMiles.setBounds(100, 70, 85, 22);
			editPanel.add(lblMiles);
			
			JTextField txtMilage = new JTextField();
			txtMilage.setBackground(Color.WHITE);
			txtMilage.setBounds(170, 70, 100, 22);
			editPanel.add(txtMilage);
			
			JLabel lblMilesToDest = new JLabel("Destination:");
			lblMilesToDest.setBounds(100, 100, 115, 22);
			editPanel.add(lblMilesToDest);
			
			JTextField txtMilesToDest = new JTextField();
			txtMilesToDest.setBackground(Color.WHITE);
			txtMilesToDest.setBounds(170, 100, 100, 22);
			editPanel.add(txtMilesToDest);
			
			JLabel lblAmenities = new JLabel("Express:");
			lblAmenities.setBounds(100, 130, 85, 22);
			editPanel.add(lblAmenities);
			
			JTextField txtAmenities = new JTextField();
			txtAmenities.setBackground(Color.WHITE);
			txtAmenities.setBounds(170, 130, 100, 22);
			editPanel.add(txtAmenities);
			
			JLabel lblCost = new JLabel("Seat type:");
			lblCost.setBounds(100, 160, 85, 22);
			editPanel.add(lblCost);
			
			JTextField txtCost = new JTextField();
			txtCost.setBackground(Color.WHITE);
			txtCost.setBounds(170, 160, 100, 22);
			editPanel.add(txtCost);
			
			JButton btnSaveChanges = new JButton("Save Changes");
			btnSaveChanges.setBackground(Color.WHITE);
			btnSaveChanges.setBounds(100, 250, 120, 29);
			editPanel.add(btnSaveChanges);
			
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setBackground(Color.WHITE);
			btnCancel.setBounds(260, 250, 120, 29);
			btnCancel.addActionListener(e -> {
				editPanel.setVisible(false);
				add(ReservationPanel());
			
			});
			editPanel.add(btnCancel);
			return editPanel;
			
		}
}
