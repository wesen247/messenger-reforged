package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

import entity.*;

public class UserHandler extends Thread {
	private ConcurrentHashMap<String,String> passwordHashmap;
	private ConcurrentHashMap<String,UserClient> connectedUsers;
	private ConcurrentHashMap<String,User> allUsers;
	private ServerController serverController;
	private HashMapHandler hashMapHandler;

	/**
	 * Contructor
	 * @param useBackup True if backup locally stored should be used
	 * @param controller Controller
	 * @author André
	 */
	public UserHandler(boolean useBackup, ServerController controller) {
		this.serverController = controller;
		hashMapHandler = new HashMapHandler();
		if(!useBackup) {
			passwordHashmap = new ConcurrentHashMap<String,String>();
			connectedUsers = new ConcurrentHashMap<String,UserClient>();
			allUsers = new ConcurrentHashMap<String,User>();
			start();
		}else {
			passwordHashmap = hashMapHandler.loadPasswordHashMap();
			connectedUsers = new ConcurrentHashMap<String,UserClient>();
			allUsers = hashMapHandler.loadAllUsers();
			System.out.println("Loaded backup");
			start();
		}
	}

	public void run() {
		try {
			while(true) {
				Thread.sleep(60000);
				hashMapHandler.savePasswordHashMap(passwordHashmap);
				hashMapHandler.saveAllUsers(allUsers);
				System.out.println("Backup users");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds new user in server 
	 * @param user User to be added
	 * @param socket Connection with user
	 * @param oos Stream
	 * @param ois Stream
	 * @return true if user added successfully
	 * @author André
	 */
	public boolean newUser(CreateUserRequest user, Socket socket, ObjectOutputStream oos, ObjectInputStream ois) {

		if (allUsers.containsKey(user.getName())) {
			return false;
		}

		passwordHashmap.put(user.getName(), user.getPassword());
		allUsers.put(user.getName(),new User(user.getName(), new ArrayList<String>(),new ArrayList<User>(),user.getImage()));
		connectedUsers.put(user.getName(), new UserClient(serverController, socket, this, allUsers.get(user.getName()), oos, ois));
		createStartUpdate(user.getName());
		sendUserUpdate();
		return true;
	}
	
	/**
	 * Attempts to login user
	 * @param login Information from user
	 * @param socket Connection
	 * @param oos stream
	 * @param ois stream
	 * @return True if login was successful
	 * @author André
	 */
	public boolean attemptLogin(LoginRequest login, Socket socket, ObjectOutputStream oos, ObjectInputStream ois) {//Fixa try catch
		try {
			if(passwordHashmap.get(login.getName()).equals(login.getPassword())) {
				System.out.println(login.getPassword());
				connectedUsers.put(login.getName(), new UserClient(serverController, socket, this, allUsers.get(login.getName()), oos, ois));
				createStartUpdate(login.getName());
				sendUserUpdate();
				return true;
			}
		}
		catch(NullPointerException e) {
			System.err.println("Expected");
		}
		return false;
	} 	

	/**
	 * Removes a user from the server.
	 * @param user The user to be removed
	 * @param password The users password
	 * @return true if the removal was successful
	 * @author André
	 */
	public boolean removeUser(User user, String password) {
		if(password.equals(passwordHashmap.get(user.getName()))) {
			for(int i = 0; allUsers.get(user.getName()).getGroups().size() >0;i++) {
				ArrayList<User> members = serverController.getGroup(allUsers.get(user.getName()).getGroups().get(i)).getGroupMembers();

				for(int l = 0; members.size()>0 ; i++) {
					if (members.get(i).getName().equals(user.getName())) {
						System.out.println("Removed from group");
						members.remove(i);
						break;
					}
				}
			}

			passwordHashmap.remove(user.getName());
			allUsers.remove(user.getName());
			System.out.println("User removed");
			return true;
		}
		System.out.println("User remove failed");
		return false;
	}
	
	/**
	 * Creates a startupdate that is sent to user
	 * @param name Name of user it is created for
	 * @author André
	 */
	public void createStartUpdate(String name) {
		Iterator<UserClient> tempIterator = connectedUsers.values().iterator();
		ArrayList<User> onlineUsers = new ArrayList<User>();
		for(int i = 0; i<connectedUsers.size();i++) {
			onlineUsers.add(tempIterator.next().getUser());
		}

		ArrayList<Group> groups = new ArrayList<Group>();
		User user = allUsers.get(name);
		for(int i = 0; i<user.getGroups().size();i++) {
			groups.add(serverController.getGroup(user.getGroups().get(i)));
		}

		StartUpdate tempStartUpdate = new StartUpdate(onlineUsers, 	allUsers, groups, user);
		send(user, tempStartUpdate);
	}
	
	/**
	 * Sends an userupdate to all connected users
	 * @author André
	 */
	public void sendUserUpdate() {
		ArrayList<User> onlineUsers = new ArrayList<User>();
		Iterator<String> onlineUserIterator = connectedUsers.keySet().iterator();
		try {
			while(connectedUsers.keySet().iterator().hasNext()) {
				onlineUsers.add(allUsers.get(onlineUserIterator.next()));
			}
		}catch(NoSuchElementException e) {
			System.err.println("Expected");
		}
		UserUpdate tempUserUpdate = new UserUpdate(onlineUsers);
		for(int i = 0; i<connectedUsers.keySet().size();i++) {
			send(new User(onlineUsers.get(i).getName()), tempUserUpdate);
			System.out.println("UserUpdate sent");
		}
	}
	
	/**
	 * Sends object of user is online. Otherwise nothing happens
	 * @param receiver Receiver of object
	 * @param sendObject Object to be sent
	 * @author André
	 */
	public void send(User receiver, Object sendObject) {
		if(connectedUsers.containsKey(receiver.getName())) {
			connectedUsers.get(receiver.getName()).addToBuffer(sendObject);	
		}
	}
	
	/**
	 * Disconnects user
	 * @param user User to be disconnected
	 * @author André
	 */
	public void disconnect(String user) {
		connectedUsers.remove(user);
		sendUserUpdate();
	}
	
	/**
	 * Adds user to a group
	 * @param user user to be added
	 * @param group group to add to
	 * @author André
	 */
	public boolean addMemberOf(User user, String group) {
		ArrayList<User> members = serverController.getGroup(group).getGroupMembers();
		boolean exist = false;
		for(int i = 0; members.size() > i; i++) {
			if(members.get(i).getName().equals(user.getName())) {
				exist = true;
			}
		}
		
		if(allUsers.containsKey(user.getName()) && !exist) {
			allUsers.get(user.getName()).addGroup(group);
			return true;
		}
		return false;
	}
}
