package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import logic.users.UserArrayList;

class SaveUserToArrayTest {

	UserArrayList users = new UserArrayList();

	@Test
	void saveNewUser() {
		String name = "newUser";
		String pw = "testPass";

		users.saveNewUser(name, pw);
		users.findUser(name);
		
		assertTrue(users.Found());
		users.deleteUser(name);
	}

	@Test
	void saveTakenUserNameFail() {
		String name = "Chase";
		String pw = "password";

		users.saveNewUser(name, pw);

		assertFalse(users.getSuccess());
	}

	@Test
	void saveShortPasswordFail() {
		String name = "Fail";
		String pw = "2short";

		users.saveNewUser(name, pw);

		assertFalse(users.getSuccess());
	}

	@Test
	void saveNewUserandFind() {
		String name = "findMe";
		String pw = "userPassword";

		users.saveNewUser(name, pw);
		users.findUser(name);
		
		assertTrue(users.Found());
		users.deleteUser(name);

	}

	@Test
	void saveNewUserandEdit() {
		String name = "user11";
		String pw = "userMan11";

		users.saveNewUser(name, pw);;
		int index = users.findUser(name);

		users.saveOverUser("newEdit", "newEditPassword", "false", index);
		users.findUser("newEdit");
		
		assertTrue(users.Found());
		users.deleteUser("newEdit");

	}

	@Test
	void createAndDeleteUser() {
		String name = "Delete_Me";
		String pw = "delete_me";

		users.saveNewUser(name, pw);
		users.deleteUser(name);
		users.findUser(name);
		
		assertFalse(users.Found());

	}
}
