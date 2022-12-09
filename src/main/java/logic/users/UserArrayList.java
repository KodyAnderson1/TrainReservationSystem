package logic.users;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import constants.FilePaths;

public class UserArrayList {

	private ArrayList<String> lines;
	private static ArrayList<User> usersList = new ArrayList<User>();
	private String fileName = FilePaths.USERS.toString();
	private int position;
	private boolean found;
	private boolean success;
	private String errorMessage;

	public UserArrayList() {
		position = 0;
		found = false;
	}
	
	public User updateUser(String username, boolean member) {
		User thisPerson = usersList.get(findUser(username));
		thisPerson.setIsPremium(String.format("%b", member));
		writeOverFile();
		return thisPerson;
	}

	public void saveUserList() {
		saveUsers();
		for (String us : lines) {
			User userHolder = new User();
			String info[] = us.split(",");
			userHolder.setUserName(info[0]);
			userHolder.setUserPassword(info[1]);
			userHolder.setIsBanned(info[2]);
			userHolder.setIsPremium(info[3]);
			usersList.add(userHolder);
		}
	}

	public void printUsersList() {
		resetUserList();
		for (int i = 0; i <= usersList.size() - 1; i++) {
			System.out.print(usersList.get(i).getUserName() + ",");
			System.out.print(usersList.get(i).getUserPassword() + ",");
			System.out.println(usersList.get(i).getIsBanned());
			System.out.println(usersList.get(i).getIsPremium());
		}
	}

	public void saveUsers() {
		try {
			lines = new ArrayList<>(Files.readAllLines(Paths.get(fileName)));
		} catch (IOException e) {
			System.out.print("SavetoArray fail");
		}
	}

	public void printUsers() {
		saveUsers();
		for (String us : lines) {
			System.out.print(us);
			System.out.println();
		}
	}

	public int findUser(String username) {
		resetUserList();
		position = 0;
		for (User hold : usersList) {
			if (hold.getUserName().equals(username)) {
				found = true;
				break;
			}
			found = false;
			position++;
		}
		return position;
	}

	public User getUserAtIndex(int index) {
		resetUserList();
		return usersList.get(index);
	}

	public boolean Found() {
		return found;
	}

	public void saveOverUser(String name, String pw, String boolVal, int index) {
		User editUser = getUserAtIndex(index);
		if (passwordCheck(pw) == true) {
			editUser.setUserPassword(pw);
		} else if (passwordCheck(pw) == false) {
			errorMessage += " Password invalid";
		}
		editUser.setIsBanned(boolVal);

		int f = findUser(name);

		if (Found() == false) {
			editUser.setUserName(name);
		} else if (f == index) {
			editUser.setUserName(name);
		} else if ((Found() == true) && (f != index)) {
			errorMessage += "Username taken";
		}
		usersList.set(index, editUser);

		writeOverFile();
		resetUserList();
	}

	public void saveNewUser(String name, String pw) {
		findUser(name);

		if (Found() == false && passwordCheck(pw) == true) {
			User user = new User(name, pw, "false", "false");
			usersList.add(user);
			success = true;
			writeOverFile();
			resetUserList();
		}
		if (Found() == true) {
			errorMessage += "Username taken";
			success = false;
		}
		if (passwordCheck(pw) == false) {
			errorMessage += " Password invalid";
			success = false;
		}
	}

	public void deleteUser(String name) {
		int index = findUser(name);

		if (Found() == true) {
			usersList.remove(index);

			writeOverFile();
			resetUserList();

		}
	}
	//
	public void writeOverFile() {
		try {
			ArrayList<String> theLines = new ArrayList<String>();
			for (int i = 0; i <= usersList.size() - 1; i++) {
				String t = usersList.get(i).getUserName() + "," + usersList.get(i).getUserPassword() + ","
						+ usersList.get(i).getIsBanned() + "," + usersList.get(i).getIsPremium();
				theLines.add(t);

			}
			Files.write(Paths.get(fileName), theLines);
			theLines.clear();
		} catch (IOException e) {
		}
	}

	public void resetUserList() {
		usersList.clear();
		saveUserList();
	}

	private boolean passwordCheck(String pw) {
		boolean pwCheck;

		if (pw.length() >= 8) {
			pwCheck = true;
		} else {
			pwCheck = false;
		}

		return pwCheck;
	}

	public String getError() {
		return errorMessage;
	}

	public boolean getSuccess() {
		return success;
	}
}
