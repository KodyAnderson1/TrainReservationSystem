package GUI.CustomerGUI.CarAndSeatPanels;

import java.awt.Color;

import javax.swing.JPanel;

import logic.controller.ShoppingCart;
import logic.model.seats.HardSeat;
import logic.model.seats.HardSleeperSeat;
import logic.model.seats.LuxurySeat;
import logic.model.seats.LuxurySleeperSeat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class OuterCarPanel extends JPanel {

	@SuppressWarnings("rawtypes")
	JComboBox comboBox;
	private ShoppingCart cart = ShoppingCart.getInstance();
	JPanel pnlHardSeat = new HardSeatCarPanel().getPanel();
	JPanel pnlHardSleeperSeat = new HardSleeperCarPanel().getPanel();
	JPanel pnlLuxurySeat = new LuxuryCarPanel().getPanel();
	JPanel pnlLuxurySleeperSeat = new LuxurySleeperPanel().getPanel();
	String[] seatArray;

	@SuppressWarnings("unchecked")
	public void setSeatArray(String[] arr) {
		seatArray = arr.clone();
		for (String s : getSeatArray())
			comboBox.addItem(s);
	}

	public String[] getSeatArray() {
		return this.seatArray;
	}

	@SuppressWarnings("rawtypes")
	public OuterCarPanel() {
		setBackground(Color.LIGHT_GRAY);
		setBounds(54, 297, 553, 291);
		setLayout(null);

		pnlHardSeat.setBounds(27, 30, 498, 205);
		add(pnlHardSeat);
		pnlHardSleeperSeat.setBounds(27, 30, 498, 205);
		add(pnlHardSleeperSeat);
		pnlLuxurySeat.setBounds(27, 30, 498, 205);
		add(pnlLuxurySeat);
		pnlLuxurySleeperSeat.setBounds(27, 30, 498, 205);
		add(pnlLuxurySleeperSeat);

		pnlHardSleeperSeat.setVisible(false);
		pnlLuxurySeat.setVisible(false);
		pnlLuxurySleeperSeat.setVisible(false);
		pnlHardSeat.setVisible(false);

		comboBox = new JComboBox();
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(178, 0, 196, 31);
		comboBox.addActionListener(e -> {
			if (comboBox.getSelectedItem().toString().equalsIgnoreCase("HardSeat"))
				seatSelectionPanel("HardSeat");
			if (comboBox.getSelectedItem().toString().equalsIgnoreCase("HardSleeperSeat"))
				seatSelectionPanel("HardSleeperSeat");
			if (comboBox.getSelectedItem().toString().equalsIgnoreCase("LuxurySeat"))
				seatSelectionPanel("LuxurySeat");
			if (comboBox.getSelectedItem().toString().equalsIgnoreCase("LuxurySleeperSeat"))
				seatSelectionPanel("LuxurySleeperSeat");
		});
		add(comboBox);
		addButtons();
	}

	public void seatSelectionPanel(String carType) {

		switch (carType) {
		case "HardSeat":
			try {
				cart.setSeatType(new HardSeat());
				pnlHardSleeperSeat.setVisible(false);
				pnlLuxurySeat.setVisible(false);
				pnlLuxurySleeperSeat.setVisible(false);
				pnlHardSeat.setVisible(true);
				repaint();
				revalidate();
			} catch (Exception e) {
			}

			break;
		case "HardSleeperSeat":
			try {
				cart.setSeatType(new HardSleeperSeat());
				pnlHardSeat.setVisible(false);
				pnlLuxurySeat.setVisible(false);
				pnlLuxurySleeperSeat.setVisible(false);
				pnlHardSleeperSeat.setVisible(true);
				repaint();
				revalidate();
			} catch (Exception e) {
			}
			repaint();
			revalidate();
			break;
		case "LuxurySeat":
			try {
				cart.setSeatType(new LuxurySeat());
				pnlHardSeat.setVisible(false);
				pnlHardSleeperSeat.setVisible(false);
				pnlLuxurySeat.setVisible(true);
				pnlLuxurySleeperSeat.setVisible(false);
			} catch (Exception e) {
			}
			repaint();
			revalidate();
			break;
		case "LuxurySleeperSeat":
			try {
				cart.setSeatType(new LuxurySleeperSeat());
				pnlHardSeat.setVisible(false);
				pnlHardSleeperSeat.setVisible(false);
				pnlLuxurySeat.setVisible(false);
				pnlLuxurySleeperSeat.setVisible(true);
			} catch (Exception e) {
			}
			repaint();
			revalidate();
			break;
		default:
			System.out.println("Error in seat selection panel switch");
			break;

		}
	}

	public void addButtons() {
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(410, 246, 115, 34);
		btnNewButton.addActionListener(e -> {
			cart.calculateTotal();
			try {
				if (!cart.getHasSelectedSeat()) {
					JOptionPane.showMessageDialog(null, "You need to select a seat!");
				} else {
					CheckoutConfirmation chck = new CheckoutConfirmation();
					chck.setVisible(true);
					pnlHardSeat.setVisible(false);
					pnlHardSleeperSeat.setVisible(false);
					pnlLuxurySeat.setVisible(false);
					pnlLuxurySleeperSeat.setVisible(false);
					cart.setHasSelectedSeat(false);
				}

			} catch (Exception e1) {
			}
			repaint();
			revalidate();
		});
		add(btnNewButton);
	}
}
