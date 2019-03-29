package application;

import java.util.ArrayList;

public class CreateUserRequest {
	private User user;
	private String name;
	private boolean userExists;
	private ArrayList<User> allUsers = new ArrayList<User>();

	public CreateUserRequest(String user) {
		if(userExists(user)==false) {
			allUsers.add(new User(user));
		}
	}

	
	public boolean userExists(String user) {
		String existingUser;
		for(int i = 0; i < allUsers.size(); i++) {
			existingUser = allUsers.get(i).getName();
			if(user==existingUser) {
				return true;
			}
		}
		return false;
	}
}

