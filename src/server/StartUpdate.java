package server;

import java.util.ArrayList;

/**
 * This class represents a StartUpdate. A StartUpdateObject contains all
 * neccessary data for a client that has recently connected.
 * 
 * @author Amir,André,Ruben
 *
 */

public class StartUpdate {

	private ArrayList<User> onlineUsers;
	private ArrayList<User> allUsers;
	private ArrayList<Group> groups;
	private User user;
	/**
	 * Constructs a StartUpdate with online users, all users and all groups
	 * available.
	 * 
	 * @param onlineUsers ArrayList with the online users.
	 * @param allUsers    ArrayList with all users.
	 * @param groups      ArrayList with all the groups
	 */

	public StartUpdate(ArrayList<User> onlineUsers, ArrayList<User> allUsers, ArrayList<Group> groups, User user) {

		this.onlineUsers = onlineUsers;
		this.allUsers = allUsers;
		this.groups = groups;
		this.user = user;
	}

	public ArrayList<User> getOnlineUsers() {

		return onlineUsers;

	}

	public ArrayList<User> getAllUsers() {

		return allUsers;

	}

	public ArrayList<Group> getGroups() {

		return groups;
	}
	
	public User getUser() {
		return user;
	}

}
