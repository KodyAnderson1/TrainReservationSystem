package GUI.AdminGUI;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import logic.controller.RouteController;

import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.DropMode;
import java.awt.ComponentOrientation;

public class RouteCreationPanel extends JPanel {
	
	private final Color defaultBackgroundColor = new Color(34, 40, 44);
	private final int mainPanelX = 173;
	private final int mainPanelY = 0;
	private final int mainPanelWidth = 661;
	private final int mainPanelHeight = 611;
	
	private RouteController routeController;

	/**
	 * Create the panel.
	 */
	public RouteCreationPanel() {
		routeController = RouteController.getInstance();

		setBackground(defaultBackgroundColor);
		setBounds(mainPanelX, mainPanelY, mainPanelWidth, mainPanelHeight);
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(49, 54, 563, 265);
		add(tabbedPane);
		
		tabbedPane.addTab("New Route", null, new NewRoutePanel(), null);
		tabbedPane.addTab("Existing", null, new ExistingRoutePanel(), null);	
		
		
		JPanel detailsPanel = new JPanel();
		detailsPanel.setBounds(49, 330, 563, 217);
		add(detailsPanel);
		detailsPanel.setLayout(null);
		
		JTextArea textAreaDetails = new JTextArea();
		textAreaDetails.setBackground(Color.LIGHT_GRAY);
		textAreaDetails.setBounds(0, 0, 563, 217);
		detailsPanel.add(textAreaDetails);
	}
}