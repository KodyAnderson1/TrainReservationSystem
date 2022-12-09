package UWF.Group4;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import GUI.AdminGUI.AdminManageReservationsPanel;
import GUI.AdminGUI.AdminUserInfoPanel;
import GUI.AdminGUI.ManageTrainPanel;
import GUI.AdminGUI.RouteCreationPanel;
import GUI.CustomerGUI.CreateAccountDialog;
import GUI.CustomerGUI.FAQPanel;
import GUI.CustomerGUI.HomepagePanel;
import GUI.CustomerGUI.LogInDialog;
import GUI.CustomerGUI.Reservation;
import GUI.CustomerGUI.TicketPurchasePanel;
import logic.controller.ReservationController;

public class Main {

	private static JFrame frame;
	private static boolean isLoggedIn;
	private TicketPurchasePanel ticketPurchasePanel;
	private JPanel PanelHomepage;
	private FAQPanel FAQPanel;
	private Reservation reservationInfo;
	private JPanel sidePanel;
	private LogInDialog logInDialog;
	private AdminMenuPanel adminPanel;

	/**
	 * Launch the application.
	 * 
	 * On log in, check if user is admin and show button if so
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) { e.printStackTrace(); }
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		isLoggedIn = false; 
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		logInDialog = new LogInDialog();
		PanelHomepage = new HomepagePanel();
		adminPanel = new AdminMenuPanel();
		ticketPurchasePanel = new TicketPurchasePanel();
		FAQPanel = new FAQPanel();
		reservationInfo = new Reservation();
		frame.setBounds(100, 100, 834, 611);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		menuPanel();
		
		FAQPanel.setBounds(6, 0, 840, 611);
		ticketPurchasePanel.getPanel().setBounds(173, 0, 661, 611);
		frame.add(FAQPanel);
		frame.add(sidePanel);
		frame.add(PanelHomepage);
		frame.add(ticketPurchasePanel.getPanel());
		frame.add(adminPanel);
		frame.add(reservationInfo);
		
		adminPanel.setVisible(false);
		ticketPurchasePanel.getPanel().setVisible(false);
		FAQPanel.setVisible(false);
		reservationInfo.setVisible(false);
		
	}
	
	public void setBackToHomepageFromTicketPurchase() {
		ticketPurchasePanel.getPanel().setVisible(false);
		PanelHomepage.setVisible(true);
	}
	
	@SuppressWarnings("exports")
	public static JFrame getFrame() { return frame; }
	public void menuPanel() {
		sidePanel = new JPanel();
		sidePanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		sidePanel.setBackground(new Color(0, 0, 51));
		sidePanel.setBounds(0, 0, 175, 611);

		sidePanel.setLayout(null);

		JButton btnPurchaseTicket = new JButton("Purchase Ticket");
		btnPurchaseTicket.setFocusPainted(false);
		btnPurchaseTicket.addActionListener(e -> {
				isLoggedIn = logInDialog.getIsLoggedIn();
			if(! isLoggedIn) {
				JOptionPane.showMessageDialog(null, "You must be logged in to purchase a ticket!");
			} else {
				try {
					reservationInfo.setVisible(false);
					ticketPurchasePanel.getPanel().setVisible(true);
					FAQPanel.setVisible(false);
					PanelHomepage.setVisible(false);
				} catch(Exception e1) { }
			}
		});
		btnPurchaseTicket.setBackground(Color.WHITE);
		btnPurchaseTicket.setBounds(13, 165, 149, 36);
		sidePanel.add(btnPurchaseTicket);
		
		JButton FAQ = new JButton("FAQ");
		FAQ.setFocusPainted(false);
		FAQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				
				ticketPurchasePanel.getPanel().setVisible(false);
				PanelHomepage.setVisible(false);
				reservationInfo.setVisible(false);
				FAQPanel.setVisible(true);
				FAQPanel.setBounds(173, 0, 661, 611);
				frame.getContentPane().add(FAQPanel);
				}
				catch (Exception e1) {}
			}
		});
		FAQ.setBackground(Color.WHITE);
		FAQ.setBounds(13, 210, 149, 30);
		sidePanel.add(FAQ);
		
		JButton btnReservationDetails = new JButton("Reservation Details");
		btnReservationDetails.setFocusPainted(false);
		btnReservationDetails.addActionListener(e -> {
			isLoggedIn = logInDialog.getIsLoggedIn();
			if(! isLoggedIn) {
				JOptionPane.showMessageDialog(null, "You must be logged in to review reservations!");
			} else {
				
				try {
					reservationInfo = new Reservation();
					frame.add(reservationInfo);
					reservationInfo.setVisible(true);
					reservationInfo.setBounds(173, 0, 661, 611);
					frame.getContentPane().add(reservationInfo);
					ticketPurchasePanel.getPanel().setVisible(false);
					PanelHomepage.setVisible(false);
					FAQPanel.setVisible(false);
					
				} catch(Exception e1) {}
			}				
		});
		btnReservationDetails.setBackground(Color.WHITE);
		btnReservationDetails.setBounds(13, 249, 149, 36);
		sidePanel.add(btnReservationDetails);
		
		JButton btnCancelReservation = new JButton("Cancel Reservation");
		btnCancelReservation.setFocusPainted(false);
		btnCancelReservation.setBackground(Color.WHITE);
		btnCancelReservation.setBounds(13, 295, 149, 36);
		sidePanel.add(btnCancelReservation);

		JLabel lblMenuSidePanel = new JLabel("Menu");
		lblMenuSidePanel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblMenuSidePanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuSidePanel.setForeground(Color.WHITE);
		lblMenuSidePanel.setBounds(13, 32, 149, 36);
		sidePanel.add(lblMenuSidePanel);
		
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isLoggedIn) {
					JOptionPane.showMessageDialog(null, "You already have an account!");
				} else {
					CreateAccountDialog newAccnt = new CreateAccountDialog();
					newAccnt.setVisible(true);
				}
			}
		});
		btnCreateAccount.setFocusPainted(false);
		btnCreateAccount.setBackground(Color.WHITE);
		btnCreateAccount.setBounds(13, 474, 149, 30);
		sidePanel.add(btnCreateAccount);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isLoggedIn = logInDialog.getIsLoggedIn();
				if(isLoggedIn) {
					JOptionPane.showMessageDialog(null, "You are already logged in!");
				} else {
					logInDialog.setVisible(true);
					
				}
			}
		});
		btnLogIn.setFocusPainted(false);
		btnLogIn.setBackground(Color.WHITE);
		btnLogIn.setBounds(13, 433, 149, 30);
		sidePanel.add(btnLogIn);

		JButton btnHomepage = new JButton("Homepage");
		btnHomepage.setFocusPainted(false);
		btnHomepage.addActionListener( e -> {
			PanelHomepage.setVisible(true);
			try {
				ticketPurchasePanel.getPanel().setVisible(false);
				reservationInfo.setVisible(false);
				FAQPanel.setVisible(false);
			} catch (Exception e1) {}
		});
		
		btnHomepage.setBackground(Color.WHITE);
		btnHomepage.setBounds(13, 515, 149, 30);
		sidePanel.add(btnHomepage);

		JButton btnExit = new JButton("EXIT");
		btnExit.setFocusPainted(false);
		btnExit.addActionListener( e -> System.exit(0) );

		btnExit.setBackground(new Color(153, 0, 0));
		btnExit.setBounds(11, 555, 151, 45);
		sidePanel.add(btnExit);

		JButton btnAdminView = new JButton("Administrator View");
		btnAdminView.setFocusPainted(false);
		btnAdminView.setBackground(Color.WHITE);
		btnAdminView.setBounds(13, 341, 149, 36);
		btnAdminView.addActionListener(e -> {
			adminPanel.setVisible(true);
			try {
				PanelHomepage.setVisible(false);
				ticketPurchasePanel.getPanel().setVisible(false);
				FAQPanel.setVisible(false);
				reservationInfo.setVisible(false);
				sidePanel.setVisible(false);
			} catch(Exception e1) {
				System.out.println(e1);
				
			}

		});
		sidePanel.add(btnAdminView);
	}
	
	/*
	 * This is the administration panel
	 */
	@SuppressWarnings("serial")
	public class AdminMenuPanel extends JPanel {
		
		private final Color hoverColor = new Color(160, 82, 45);
		private final Color defaultBackgroundColor = new Color(21, 25, 28);
		private RouteCreationPanel routesAdminPnl;
		private JPanel panelMngTickHover;
		private JPanel panelMngRouteHover;
		private JPanel panelMngTrainHover;
		private JPanel panelUserLookUpHover;
		private JPanel panelMngResHover;
		private AdminManageReservationsPanel manageResrv;
		private AdminUserInfoPanel userInfo;
		private ManageTrainPanel trainAdminPanel;

		/**
		 * Create the panel.
		 */
		public AdminMenuPanel() {
			panelMngTickHover = new JPanel();
			panelMngRouteHover = new JPanel();
			panelMngTrainHover = new JPanel();
			panelUserLookUpHover = new JPanel();
			panelMngResHover = new JPanel();
			routesAdminPnl = new RouteCreationPanel();
			trainAdminPanel = new ManageTrainPanel();
			userInfo = new AdminUserInfoPanel();
			manageResrv = new AdminManageReservationsPanel();
			setBounds(0, 0, 175, 611);
			setBackground(defaultBackgroundColor);
			setLayout(null);
			
			frame.add(userInfo);
			frame.add(trainAdminPanel);
			frame.add(manageResrv);
			frame.add(routesAdminPnl);
			
			routesAdminPnl.setVisible(false);
			userInfo.setVisible(false);
			trainAdminPanel.setVisible(false);
			manageResrv.setVisible(false);
			
			
			JLabel lblMenuSidePanel = new JLabel("Admin");
			lblMenuSidePanel.setBackground(defaultBackgroundColor);
			lblMenuSidePanel.setFont(new Font("Tahoma", Font.BOLD, 28));
			lblMenuSidePanel.setHorizontalAlignment(SwingConstants.CENTER);
			lblMenuSidePanel.setForeground(Color.WHITE);
			lblMenuSidePanel.setBounds(13, 32, 149, 36);
			add(lblMenuSidePanel);
			
			JButton btnMngRoutes = new JButton("Manage Routes");
			btnMngRoutes.setFocusPainted(false);
			btnMngRoutes.setBorderPainted(false);
			btnMngRoutes.setBackground(defaultBackgroundColor);
			btnMngRoutes.setForeground(Color.WHITE);
			btnMngRoutes.setBounds(13, 294, 149, 36);
			btnMngRoutes.addActionListener(e -> {
				routesAdminPnl.setVisible(true);	
				try {
					manageResrv.setVisible(false);
					userInfo.setVisible(false);
					PanelHomepage.setVisible(false);
					trainAdminPanel.setVisible(false);
					PanelHomepage.setVisible(false);
					ticketPurchasePanel.getPanel().setVisible(false);
					FAQPanel.setVisible(false);
					reservationInfo.setVisible(false);
				} catch (Exception e1) {}	
			});
			add(btnMngRoutes);
			
			JButton btnUserLookup = new JButton("User Look Up");
			btnUserLookup.setFocusPainted(false);
			btnUserLookup.setBorderPainted(false);
			btnUserLookup.setBackground(defaultBackgroundColor);
			btnUserLookup.setForeground(Color.WHITE);
			btnUserLookup.setBounds(13, 247, 149, 36);
			btnUserLookup.addActionListener( e -> {
				userInfo.setVisible(true);
				try { 
					manageResrv.setVisible(false);
					PanelHomepage.setVisible(false);
					trainAdminPanel.setVisible(false);
					PanelHomepage.setVisible(false);
					routesAdminPnl.setVisible(false);
					ticketPurchasePanel.getPanel().setVisible(false);
					FAQPanel.setVisible(false);
					reservationInfo.setVisible(false);
				} catch (Exception e1) {}
			});
			add(btnUserLookup);
			
			JButton btnMngTrains = new JButton("Manage Trains");
			btnMngTrains.setFocusPainted(false);
			btnMngTrains.setBorderPainted(false);
			btnMngTrains.setBackground(defaultBackgroundColor);
			btnMngTrains.setForeground(Color.WHITE);
			btnMngTrains.setBounds(13, 341, 149, 36);
			btnMngTrains.addActionListener(e -> {
				trainAdminPanel.setVisible(true);
				try { 
					PanelHomepage.setVisible(false);
					routesAdminPnl.setVisible(false);
					userInfo.setVisible(false);
					manageResrv.setVisible(false);
					ticketPurchasePanel.getPanel().setVisible(false);
					FAQPanel.setVisible(false);
					reservationInfo.setVisible(false);
				} catch (Exception e1) {}
			});
			add(btnMngTrains);
			
			JButton btnManageTicketDetails = new JButton("Manage Ticket Details");
			btnManageTicketDetails.setFocusPainted(false);
			btnManageTicketDetails.setBorderPainted(false);
			btnManageTicketDetails.setBackground(defaultBackgroundColor);
			btnManageTicketDetails.setForeground(Color.WHITE);
			btnManageTicketDetails.setBounds(13, 388, 149, 36);
			add(btnManageTicketDetails);
			
			JButton btnManageReservations = new JButton("Manage Reservations");
			btnManageReservations.setFocusPainted(false);
			btnManageReservations.setBorderPainted(false);
			btnManageReservations.setBackground(defaultBackgroundColor);
			btnManageReservations.setForeground(Color.WHITE);
			btnManageReservations.setBounds(13, 435, 149, 36);
			btnManageReservations.addActionListener(e -> {
				try {
					manageResrv.setVisible(true);
					PanelHomepage.setVisible(false);
					trainAdminPanel.setVisible(false);
					PanelHomepage.setVisible(false);
					userInfo.setVisible(false);  
					routesAdminPnl.setVisible(false);
					ticketPurchasePanel.getPanel().setVisible(false);
					FAQPanel.setVisible(false);
					reservationInfo.setVisible(false);
					
				}
				catch (Exception e1) {}
			});
			add(btnManageReservations);
			
			JButton btnHomepage = new JButton("Homepage");
			btnHomepage.setFocusPainted(false);
			btnHomepage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						sidePanel.setVisible(true);
						PanelHomepage.setVisible(true);
						routesAdminPnl.setVisible(false);
						adminPanel.setVisible(false);
						userInfo.setVisible(false);
						reservationInfo.setVisible(false);
						FAQPanel.setVisible(false);
						trainAdminPanel.setVisible(false);
						manageResrv.setVisible(false);
						ticketPurchasePanel.getPanel().setVisible(false);
					} catch(Exception e2) {}
					
				}
			});
			btnHomepage.setBackground(Color.WHITE);
			btnHomepage.setBounds(13, 515, 149, 30);
			add(btnHomepage);
			
			JButton btnExit = new JButton("EXIT");
			btnExit.setFocusPainted(false);
			btnExit.addActionListener( e -> System.exit(0) );

			btnExit.setBackground(new Color(153, 0, 0));
			btnExit.setBounds(11, 555, 151, 45);
			add(btnExit);	
			
			panelHoverSetUp();
			
			btnMngRoutes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) { panelMngRouteHover.setBackground(hoverColor); }
				@Override
				public void mouseExited(MouseEvent e) { panelMngRouteHover.setBackground(defaultBackgroundColor); }
			});
			btnUserLookup.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) { panelUserLookUpHover.setBackground(hoverColor); }
				@Override
				public void mouseExited(MouseEvent e) { panelUserLookUpHover.setBackground(defaultBackgroundColor); }
			});
			btnMngTrains.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) { panelMngTrainHover.setBackground(hoverColor); }
				@Override
				public void mouseExited(MouseEvent e) { panelMngTrainHover.setBackground(defaultBackgroundColor); }
			});
			btnManageReservations.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) { panelMngResHover.setBackground(hoverColor); }
				@Override
				public void mouseExited(MouseEvent e) { panelMngResHover.setBackground(defaultBackgroundColor); }
			});
			btnManageTicketDetails.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) { panelMngTickHover.setBackground(hoverColor); }
				@Override
				public void mouseExited(MouseEvent e) { panelMngTickHover.setBackground(defaultBackgroundColor); }
			});
		}
		
		public void panelHoverSetUp() {

			panelMngRouteHover.setBounds(0, 296, 3, 34);
			panelMngRouteHover.setBackground(defaultBackgroundColor);
			panelMngTrainHover.setBounds(0, 341, 3, 34);
			panelMngTrainHover.setBackground(defaultBackgroundColor);
			panelMngResHover.setBounds(0, 435, 3, 34);
			panelMngResHover.setBackground(defaultBackgroundColor);
			panelMngTickHover.setBounds(0, 388, 3, 34);
			panelMngTickHover.setBackground(defaultBackgroundColor);
			panelUserLookUpHover.setBounds(0, 249, 3, 34);
			panelUserLookUpHover.setBackground(defaultBackgroundColor);
			
			add(panelMngTrainHover);
			add(panelMngRouteHover);
			add(panelMngTickHover);
			add(panelUserLookUpHover);
			add(panelMngResHover);
		}
	}
	
}