package GUI.CustomerGUI.CarAndSeatPanels;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import logic.controller.ShoppingCart;

@SuppressWarnings("serial")
public class SeatPanel extends JPanel {

	ShoppingCart cart;
	boolean isBooked;
	boolean isSelected;
	int index;
	
	public SeatPanel(int x, int y, int width, int height, int index) {
		cart = ShoppingCart.getInstance();
		isSelected = false;
		isBooked = false;
		setIndex(index);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isBooked) JOptionPane.showMessageDialog(null, "This seat is taken, try another!");
				
				if(isSelected && !isBooked) {
					setBackground(Color.WHITE); 
					cart.setHasSelectedSeat(false);
				}
				if (!isSelected && !isBooked){
					setBackground(Color.green);
					cart.setHasSelectedSeat(true);		
				}
				
				setSelected();
			}
		});
		
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setBounds(x, y, width, height);

	}
	
	public boolean bookSeat() { 
		if(this.isBooked) return false;
		this.isBooked = true; 
		setBackground(Color.black);
		return true;
	}
	
	public boolean freeSeat() {
		if(!this.isBooked) return false;
		this.isBooked = false;
		setBackground(Color.white);
		return true;
	}

	public void setIndex(int ind) { this.index = ind; }
	public void setSelected() {  isSelected = isSelected ? false : true; }

	public boolean getBooked() { return this.isBooked; }
	public int getIndex() { return this.index; }
	public boolean getSelected() { return this.isSelected; }
}
