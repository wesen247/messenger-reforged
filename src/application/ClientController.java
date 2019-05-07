package application;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import entity.*;

/**
 * Controller class
 * 
 * @author Ruben
 *
 */
public class ClientController {
	private LoginController LoginController;
	private User user;
	private Data data;
	private ObjectOutputStream oos;
	private Socket socket;
	private User reciver;
	private static ClientController controller;
	private Group group;
	private String filename;
	private String groupName;

	/**
	 * Starts the connection with the server
	 */
	public static ClientController getClient() {
		return controller;
	}

	public ClientController(String username, String password, LoginController loginController) {
		this.LoginController = loginController;
		try {
			socket = new Socket(InetAddress.getLocalHost(), 5343);
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			oos.flush();
			data = new Data(LoginController, socket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		logIn(username, password);
		ClientController.controller = this;
	}

	public String getReceiver() {
		return reciver.getName();
	}

	public ClientController(String username, String password, CreateUserController userController,
			BufferedImage image) {
		try {
			socket = new Socket(InetAddress.getLocalHost(), 5343);
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			oos.flush();
			data = new Data(userController, socket);

		} catch (IOException e) {
			e.printStackTrace();
		}
		createNewUser(username, password, image);
		ClientController.controller = this;
	}

	/**
	 * Creates an new group object and sends it to the server
	 * 
	 * @param groupName
	 */
	public void createNewGroup(String groupName) {
		this.groupName = groupName;
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<GroupMessage> messages = new ArrayList<GroupMessage>();
		ArrayList<String> fileLog = new ArrayList<String>();
		ArrayList<Event> eventObjects = new ArrayList<Event>();
		try {
			oos.writeObject(new Group(users, messages, fileLog, eventObjects, groupName, user.getName()));
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void addToGroup() {
		try {
			oos.writeObject(new AddObjectRequest("addGroupMember:" + groupName, user, user));
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void deleteAccount(String password) {

		try {
			oos.writeObject(new AddObjectRequest("delUser", password, user));
			oos.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Creates an new account
	 * 
	 * @param name
	 * @param password
	 */
	public void createNewUser(String name, String password, BufferedImage image) {
		try {
			oos.writeObject(new CreateUserRequest(name, password, image));
			this.user = new User(name);
			oos.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a group message
	 * 
	 * @param message
	 * @param group
	 */
	public void createGroupMessage(String message) {
		try {
			oos.writeObject(new GroupMessage(message, user, group));
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a private message
	 * 
	 * @param message
	 * @param reciever
	 */
	public void createPrivateMessage(String message) {
		try {
			oos.writeObject(new PrivateMessage(message, user, reciver));
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a log in request to the server
	 * 
	 * @param userName
	 * @param password
	 */
	public void logIn(String username, String password) {
		try {
			oos.writeObject(new LoginRequest(username, password));
			this.user = new User(username);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void download(String file) {

		try {
			oos.writeObject(new ObjectRequest("file", file, user));
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a event to a group
	 * 
	 * @param event
	 */
	public void addEvent(String comment, String date, String location) {
		String type = "event";
		try {
			Event event = new Event(group, user, date, comment, location);
			oos.writeObject(new AddObjectRequest(type, event, user));
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a user to the group
	 * 
	 * @param groupName
	 * @param userToAdd
	 */
	public void addGroupMember(String groupName, User userToAdd) {
		String type = "addGroupMember:" + groupName;
		try {
			oos.writeObject(new AddObjectRequest(type, userToAdd, user));
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that creates a socket.
	 */
	public void createSocket() {
		if (this.socket.isClosed()) {
			try {
				oos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				this.socket = new Socket(InetAddress.getLocalHost(), 5343);
				oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				oos.flush();
				data.createConnection(socket);
			} catch (UnknownHostException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	public void sendFile(String filename, byte[] fileToSend) {

		try {
			System.out.println("HejAAA");
			oos.writeObject(new AddObjectRequest("file:" + group.getGroupName() + ":" + filename, fileToSend, user));
			oos.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Method that closes the socket.
	 */
	public void closeSocket() {
		try {
			data.kill();
			this.socket.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void setRequestedFile(String filename) {
		this.filename = filename;
	}

	public void saveToComputer(byte[] file) {

		String username = System.getProperty("user.name");
		try {
			FileOutputStream out = new FileOutputStream("C:\\Users\\" + username + "\\Downloads\\" + this.filename);
			out.write(file);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that sets a reciever.
	 * 
	 * @param name Name of the reciever.
	 */
	public void setReciver(String name) {
		this.reciver = new User(name);
	}

	public void setGroup(String name) {
		this.group = new Group(name);
	}

	public Group getGroup() {
		return this.group;
	}

	public User getUser() {
		return user;
	}

}
