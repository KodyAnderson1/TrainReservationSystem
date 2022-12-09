package logic.users;

import logic.controller.ShoppingCart;

public class User {
	private String userName;
	private String userPassword;
	private boolean isBanned;
	private boolean isPremium;

	public User() {

	}

	public User(String userName, String userPassword, String booleanVal, String member) {
		setUserName(userName);
		setUserPassword(userPassword);
		setIsBanned(booleanVal);
		setIsPremium(member);
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setIsBanned(String boolValue) {
		isBanned = Boolean.parseBoolean(boolValue);
	}

	public boolean getIsBanned() {
		return isBanned;
	}
	
	// here is the premium member stuff
	public void setIsPremium(String premBool) {
		isPremium = Boolean.parseBoolean(premBool);
	}

	public boolean getIsPremium() {
		return isPremium;
	}
}
