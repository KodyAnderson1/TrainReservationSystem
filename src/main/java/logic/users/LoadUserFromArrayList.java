package logic.users;

public class LoadUserFromArrayList {

	private UserArrayList users = new UserArrayList();
	private boolean userLoaded;
	private boolean passwordMatches;
	private String userName;
	private String password;
	private boolean isBanned;

	public LoadUserFromArrayList() {

		userLoaded = false;
		passwordMatches = false;
	}

	// used for user login
	public boolean loadUserInfo(String name, String pw) {
		int index = users.findUser(name);
		if (users.Found() == true) {
			User loaded = users.getUserAtIndex(index);
			userName = loaded.getUserName();
			password = loaded.getUserPassword();
			isBanned = loaded.getIsBanned();
			if (passwordMatch(pw) == true) {
				userLoaded = true;
			}
		}
		return userLoaded;
	}

	public boolean passwordMatch(String pw) {
		if (password.equals(pw)) {
			passwordMatches = true;
		}
		return passwordMatches;
	}

	// used for admin purposes
	public void findUserByIndex(int index) {
		User loaded = users.getUserAtIndex(index);
		userName = loaded.getUserName();
		password = loaded.getUserPassword();
		isBanned = loaded.getIsBanned();
	}

	public String getUsername() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public boolean getIsBanned() {
		return isBanned;
	}

}
