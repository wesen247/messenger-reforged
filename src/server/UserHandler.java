package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class UserHandler {
	ConcurrentHashMap<String,String> passwordHashmap;
	ConcurrentHashMap<String,UserClient> connectedUsers;
	ArrayList<User> allUsers;
	ServerController controller;

	public UserHandler(boolean useBackup, ServerController controller) {
		this.controller = controller;
		if(!useBackup) {
			passwordHashmap = new ConcurrentHashMap<String,String>();
			connectedUsers = new ConcurrentHashMap<String,UserClient>();
		}else {
			//här ska en sparad fil på hårddisken läsas som innehåller password hashmap samt allUsers
		}
	}
	
	public boolean newUser(CreateUserRequest user, Socket socket) {
		boolean exi
		
		if()
		
	}

}
