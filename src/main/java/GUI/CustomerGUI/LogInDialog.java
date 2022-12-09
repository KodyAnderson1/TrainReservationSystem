package GUI.CustomerGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.controller.ShoppingCart;
import logic.users.LoadUserFromArrayList;
import logic.users.User;
import logic.users.UserArrayList;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;

public class LogInDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;
	private boolean isLoggedIn;

	public LogInDialog() {
		isLoggedIn = false;
		setTitle("Log In");
		setBounds(100, 100, 450, 249);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(129, 28, 175, 31);
		contentPanel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(129, 88, 175, 31);
		contentPanel.add(passwordField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(129, 70, 78, 14);
		contentPanel.add(lblPassword);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(129, 11, 78, 14);
		contentPanel.add(lblUsername);

		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setFocusPainted(false);
		btnLogIn.setBackground(Color.WHITE);
		btnLogIn.setBounds(167, 130, 100, 31);
		btnLogIn.addActionListener(e -> {
			validateAndLoadUser(textField.getText(), new String(passwordField.getPassword()));
		});
		contentPanel.add(btnLogIn);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFocusPainted(false);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setActionCommand("Cancel");
		btnCancel.addActionListener(e -> {
			dispose();
		});
		btnCancel.setBounds(167, 172, 100, 31);
		contentPanel.add(btnCancel);
	}
	
	public void validateAndLoadUser(String username, String password) {
	
		ShoppingCart cart = ShoppingCart.getInstance();
		LoadUserFromArrayList load = new LoadUserFromArrayList();
		UserArrayList user = new UserArrayList();
		

		if (username.isEmpty() && password.isEmpty())
			JOptionPane.showMessageDialog(null, "Please enter a username and password");
		if (load.loadUserInfo(username, password)) {
			cart.setUserName(username);
			JOptionPane.showMessageDialog(null, "Login Success");
			int index = user.findUser(username);
			User u = user.getUserAtIndex(index);
			setIsLoggedIn(true);
			if(u.getIsBanned())
			{
				JOptionPane.showMessageDialog(null, "Your account is Banned");
				setIsLoggedIn(false);
			}
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "User not found");
		}
	}
	
	public void setIsLoggedIn(boolean flag) { this.isLoggedIn = flag; }
	public boolean getIsLoggedIn() { return this.isLoggedIn; }
}
