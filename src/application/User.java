package application;

import java.util.ArrayList;

public class User {
	private String name;
	private String password;
	private ArrayList<User> friendList;
	
	public User(String name) {
		this.name = name;
	}

	
	public String getName() {
		return name;
	}
	
	
	public String getPassword() {
		return password;
	}

	
	public boolean passwordMatch(String enteredPassword) {
		if(enteredPassword==password) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public ArrayList<User> getFriends() {
		return friendList;
	}
}
