package GUI.CustomerGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import logic.users.UserArrayList;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;

public class CreateAccountDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the dialog.
	 */
	public CreateAccountDialog() {
		setTitle("Create Account");
		setBounds(100, 100, 450, 249);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(null);
		textField = new JTextField("");
		textField.setBounds(129, 28, 175, 31);
		contentPanel.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(129, 88, 175, 31);
		contentPanel.add(passwordField);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(129, 70, 100, 14);
		contentPanel.add(lblPassword);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(129, 11, 78, 14);
		contentPanel.add(lblUsername);

		JButton btnLogIn = new JButton("Create");
		btnLogIn.setFocusPainted(false);
		btnLogIn.setBackground(Color.WHITE);
		btnLogIn.setBounds(167, 130, 100, 31);
		btnLogIn.addActionListener(e -> {
			validateAndSaveUser(textField.getText(), new String(passwordField.getPassword()));
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
	
	public void validateAndSaveUser(String username, String password) {

		UserArrayList save = new UserArrayList();
		save.saveNewUser(username, password);
		
		if(save.getSuccess()) {
			JOptionPane.showMessageDialog(null, "Save Successful");
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Save Failed: " + save.getError());
		}

	}
	
}
