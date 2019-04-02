package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class UserHandler {
	ConcurrentHashMap<String,String> passwordHashmap;
	ConcurrentHashMap<String,UserClient> connectedUsers;
	ConcurrentHashMap<String,User> allUsers;
	ServerController controller;

	public UserHandler(boolean useBackup, ServerController controller) {
		this.controller = controller;
		if(!useBackup) {
			passwordHashmap = new ConcurrentHashMap<String,String>();
			connectedUsers = new ConcurrentHashMap<String,UserClient>();
			allUsers = new ConcurrentHashMap<String,User>();
		}else {
			//här ska en sparad fil på hårddisken läsas som innehåller password hashmap samt allUsers
		}
	}

	public boolean newUser(CreateUserRequest user, Socket socket) {

		if (allUsers.containsKey(user.getName())) {
			return false;
		}

		passwordHashmap.put(user.getName(), user.getPassword());
		allUsers.put(user.getName(),new User(user.getName(), new ArrayList<String>(),new ArrayList<User>()));
		connectedUsers.put(user.getName(), new UserClient(controller, socket, this, allUsers.get(user.getName())));
		return true;
	}
	public boolean attemptLogin(LoginRequest login, Socket socket) {
		if(passwordHashmap.get(login.getName()) == login.getPassword()) {
			connectedUsers.put(login.getName(), new UserClient(controller, socket, this, allUsers.get(login.getName())));
			
			Iterator<UserClient> tempIterator = connectedUsers.values().iterator();
			ArrayList<User> onlineUsers = new ArrayList<User>();
			for(int i = 0; i<connectedUsers.size();i++) {
				onlineUsers.add(tempIterator.next().getUser());
			}
			
			ArrayList<Group> groups = new ArrayList<Group>();
			User user = allUsers.get(login.getName());
			for(int i = 0; i<user.getGroups().size();i++) {
				groups.add(controller.getGroup(user.getGroups().get(i)));
			}
			
			StartUpdate tempStartUpdate = new StartUpdate(onlineUsers, 	allUsers, groups, user);
			send(user, tempStartUpdate);
			return true;
		}
		return false;
	} 	
	
	public void send(User receiver, Object sendObject) {
		connectedUsers.get(receiver.getName()).addToBuffer(sendObject);	
	}
	public void disconnect(String user) {
		connectedUsers.remove(user);
	}
	public void addMemberOf(User user, String group) {
		allUsers.get(user.getName()).addGroup(group);
	}
}
