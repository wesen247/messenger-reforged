package server;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Entity class, update the clients user list
 * @author André Ruben Amir
 *
 */
public class UserUpdate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8773089714932468315L;
	private ArrayList<String> onlineUsers;
	/**
	 * @author André Ruben Amir
	 * @param onlineUser	All Online Users
	 */
	public UserUpdate(ArrayList<String> onlineUser) {
		this.onlineUsers = onlineUser;
	}
	
	public ArrayList<String> getUsers(){
		return onlineUsers;
	}

}
