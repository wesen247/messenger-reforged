package application;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Entity class, Class with user information
 * @author Zacharias André Ruben Amir
 *
 */
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4193667861507882544L;
	private String name;
	private String password;
	private ArrayList<User> friendList;
	private ArrayList<String> memberOfGroups;
	
	public User(String name) {
		this.name = name;
	}
	
	public User(String name, ArrayList<String> memberOfGroups, ArrayList<User> friendList) {
		this.name = name;
		this.memberOfGroups = memberOfGroups;
		this.friendList = friendList;
	}

	public String getName() {
		return name;
	}
	
	public ArrayList<String> getGroups(){
		return memberOfGroups;
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
	
	public void addGroup(String groupName) {
		memberOfGroups.add(groupName);
	}
}
