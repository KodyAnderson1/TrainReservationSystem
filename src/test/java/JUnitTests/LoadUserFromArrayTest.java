package JUnitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import logic.users.LoadUserFromArrayList;
import logic.users.UserArrayList;

class LoadUserFromArrayTest {

	UserArrayList myArray = new UserArrayList();
	LoadUserFromArrayList load = new LoadUserFromArrayList();
	
	@Test
	void loadUserThatExists() {
	
		String userName = "Cheeze";
		int index = myArray.findUser(userName);
		
		assertTrue(myArray.Found());
	}
	
	@Test
	void loadUserThatDoesNotExist() {
	
		String userName = "Fail";
		String password = "password";
		int index = myArray.findUser(userName);
	
		assertFalse(myArray.Found());
		
	}
	@Test
	void loadUserTest() {
	
		String userName = "Cheeze";
		String password = "password";
		
		assertTrue(load.loadUserInfo(userName, password));
		
	}
	@Test
	void loadUserTestFail() {
	
		String userName = "Cheeze";
		String password = "paellamash";
		
		assertFalse(load.loadUserInfo(userName, password));
		
	}
	
	
}
