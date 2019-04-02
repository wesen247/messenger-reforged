package server;

import java.util.ArrayList;
/**
 * Entity class, update the clients user list
 * @author André Ruben Amir
 *
 */
public class UserUpdate {
	private ArrayList<User> onlineUsers;
	/**
	 * @author André Ruben Amir
	 * @param onlineUser	All Online Users
	 */
	public UserUpdate(ArrayList<User> onlineUser) {
		this.onlineUsers = onlineUser;
	}
	
	public ArrayList<User> getUsers(){
		return onlineUsers;
	}

}
