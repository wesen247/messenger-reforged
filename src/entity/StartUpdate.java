package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class represents a StartUpdate. A StartUpdateObject contains all
 * neccessary data for a client that has recently connected.
 * 
 * @author Amir,André,Ruben
 *
 */

public class StartUpdate implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1821886971134191409L;
	private ArrayList<User> onlineUsers;
	private ConcurrentHashMap<String,User> allUsers;
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

	public StartUpdate(ArrayList<User> onlineUsers, ConcurrentHashMap<String,User> allUsers, ArrayList<Group> groups, User user) {
		this.onlineUsers = onlineUsers;
		this.allUsers = allUsers;
		this.groups = groups;
		this.user = user;
	}

	public ArrayList<User> getOnlineUsers() {
		return onlineUsers;
	}

	public ArrayList<User> getAllUsers() {
		return new ArrayList<User>(allUsers.values());
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}
	
	public User getUser() {
		return user;
	}
}
