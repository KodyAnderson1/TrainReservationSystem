package GUI.AdminGUI;

import java.awt.Color;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import logic.users.LoadUserFromArrayList;
import logic.users.User;
import logic.users.UserArrayList;

public class AdminUserInfoPanel extends JPanel {

	private final Color adminMenuBackgroundColor = new Color(21, 25, 28);
	private final Color adminPanelBackgroundColor = new Color(34, 40, 44);
	private int userIndex;
	private JPanel userInfo;
	private String username;
	private String password;
	public String banStat;
	public String banChoice;
	private LoadUserFromArrayList userArray = new LoadUserFromArrayList();
	private boolean found;
	UserArrayList findUser = new UserArrayList();

	JPanel loadUserInfoPanel = new JPanel();

	public AdminUserInfoPanel() {
		setBackground(adminPanelBackgroundColor);
		setBounds(173, 0, 661, 611);
		setLayout(null);

		userInfo = new JPanel();
		userInfo.setBounds(49, 54, 563, 265);
		userInfo.setBackground(Color.LIGHT_GRAY);
		userInfo.setLayout(null);
		add(userInfo);
		userInfo.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0)),
						"User Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(140, 55, 100, 22);
		userInfo.add(lblUsername);

		JTextField txtUsername = new JTextField();
		txtUsername.setBackground(Color.WHITE);
		txtUsername.setBounds(215, 55, 100, 22);
		userInfo.add(txtUsername);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(225, 90, 82, 29);
		btnSearch.addActionListener(e -> {

			loadUsername(txtUsername.getText());
			if (userFound() == true) {
				userInfo.setVisible(false);
				add(LoadUserInfoPanel());
				txtUsername.setText("");
			}
		});
		userInfo.add(btnSearch);
	}

	public JPanel LoadUserInfoPanel() {
		JPanel loadUserInfoPanel = new JPanel();

		loadUserInfoPanel.setLayout(null);
		loadUserInfoPanel.setBackground(Color.LIGHT_GRAY);
		loadUserInfoPanel.setBounds(49, 54, 563, 265);

		loadUserInfoPanel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0)),
						"User Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(100, 40, 70, 22);
		loadUserInfoPanel.add(lblUsername);

		JLabel lblname = new JLabel(getUsername(getIndex()));
		lblname.setBounds(170, 40, 70, 22);
		loadUserInfoPanel.add(lblname);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(100, 80, 100, 22);
		loadUserInfoPanel.add(lblPassword);

		JLabel lblPw = new JLabel(getPassword(getIndex()));
		lblPw.setBounds(170, 80, 100, 22);
		loadUserInfoPanel.add(lblPw);

		JLabel lblUserNumber = new JLabel("User Number:");
		lblUserNumber.setBounds(100, 120, 120, 22);
		loadUserInfoPanel.add(lblUserNumber);

		String num = getIndex() + "";

		JLabel lblnum = new JLabel(num);
		lblnum.setBounds(200, 120, 30, 22);
		loadUserInfoPanel.add(lblnum);

		JLabel lblBanStat = new JLabel("Ban Status:");
		lblBanStat.setBounds(100, 160, 100, 22);
		loadUserInfoPanel.add(lblBanStat);

		JLabel lblBan = new JLabel(getBanStat(getIndex()));
		lblBan.setBounds(170, 160, 100, 22);
		loadUserInfoPanel.add(lblBan);

		JButton btnEditUser = new JButton("Edit User");
		btnEditUser.setBackground(Color.WHITE);
		btnEditUser.setBounds(100, 210, 90, 29);
		btnEditUser.addActionListener(e -> {
			loadUserInfoPanel.setVisible(false);
			add(EditUserInfoPanel());

		});
		loadUserInfoPanel.add(btnEditUser);

		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.setBackground(Color.WHITE);
		btnDeleteUser.setBounds(210, 210, 110, 29);
		btnDeleteUser.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "User Deleted");
			findUser.deleteUser(getUsername(getIndex()));
			loadUserInfoPanel.setVisible(false);
			userInfo.setVisible(true);

		});
		loadUserInfoPanel.add(btnDeleteUser);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(340, 210, 90, 29);
		btnCancel.addActionListener(e -> {
			loadUserInfoPanel.setVisible(false);
			userInfo.setVisible(true);

		});
		loadUserInfoPanel.add(btnCancel);

		return loadUserInfoPanel;
	}

	public JPanel EditUserInfoPanel() {
		
		JPanel editUserInfoPanel = new JPanel();

		editUserInfoPanel.setLayout(null);
		editUserInfoPanel.setBackground(Color.LIGHT_GRAY);
		editUserInfoPanel.setBounds(49, 54, 563, 265);

		editUserInfoPanel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), new Color(0, 0, 0)),
						"Edit User Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(100, 40, 100, 22);
		editUserInfoPanel.add(lblUsername);

		JTextField txtUsername = new JTextField();
		txtUsername.setBackground(Color.WHITE);
		txtUsername.setBounds(170, 40, 100, 22);
		txtUsername.setText(username);
		editUserInfoPanel.add(txtUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(100, 80, 100, 22);
		editUserInfoPanel.add(lblPassword);

		JTextField txtPassword = new JTextField();
		txtPassword.setBackground(Color.WHITE);
		txtPassword.setBounds(170, 80, 100, 22);
		txtPassword.setText(password);
		editUserInfoPanel.add(txtPassword);
		
		JLabel lblBanStat = new JLabel("Ban Status:");
		lblBanStat.setBounds(100, 120, 100, 22);
		editUserInfoPanel.add(lblBanStat);
		
		
		String tf[] = {"False","True"};
		JComboBox comboBoxBan = new JComboBox(tf);
		//comboBoxBan.setFocusable(false);
		comboBoxBan.setBounds(170, 120, 100, 22);
		comboBoxBan.addActionListener(e -> {
			String choice = (String) comboBoxBan.getSelectedItem();
			passChoice(choice);
		});
		editUserInfoPanel.add(comboBoxBan);

		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setBackground(Color.WHITE);
		btnSaveChanges.setBounds(100, 210, 120, 29);
		btnSaveChanges.addActionListener(e -> {

			String newName = txtUsername.getText();
			String newPW = txtPassword.getText();
			String newBan = getChoice();

			findUser.saveOverUser(newName, newPW, newBan, userIndex);
				editUserInfoPanel.setVisible(false);
				add(LoadUserInfoPanel());
				if(findUser.getError().equals(""))
				{
					
				}else {JOptionPane.showMessageDialog(null, findUser.getError());}
		});
		editUserInfoPanel.add(btnSaveChanges);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setBounds(340, 210, 90, 29);
		btnCancel.addActionListener(e -> {
			editUserInfoPanel.setVisible(false);
			add(LoadUserInfoPanel());

		});
		editUserInfoPanel.add(btnCancel);
		return editUserInfoPanel;
	}

	public void setIndex(int index) {
		userIndex = index;
	}

	public int getIndex() {
		return userIndex;
	}

	public void loadUsername(String username) {

		int userNum = findUser.findUser(username);

		if (username.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a Username");
			setUserFound(false);
		} else if (findUser.Found() == true) {
			setUserFound(true);
			setIndex(userNum);
			userArray.findUserByIndex(getIndex());
		} else {
			setUserFound(false);
			JOptionPane.showMessageDialog(null, "User not found");
		}
	}

	public void setUserFound(boolean bool) {
		found = bool;
	}

	public boolean userFound() {
		return found;
	}

	public String getUsername(int index) {
		userArray.findUserByIndex(index);
		username = userArray.getUsername();
		return username;
	}

	public String getPassword(int index) {
		userArray.findUserByIndex(index);
		password = userArray.getPassword();
		return password;
	}
	public String getBanStat(int index) {
		userArray.findUserByIndex(index);
		banStat = userArray.getIsBanned() + "";
		return banStat;
	}
	public void passChoice(String c)
	{
		banChoice = c;
	}
	public String getChoice()
	{
		return banChoice;
	}
}
