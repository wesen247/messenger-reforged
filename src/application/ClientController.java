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
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;

import entity.*;

/**
 * Controller class
 * @author Ruben, Amir
 *
 */
public class ClientController {
	private LoginController loginController;
	private User user;
	private Data data;
	private ObjectOutputStream outputStream;
	private Socket socket;
	private User receiver;
	private static ClientController controller;
	private Group group;
	private String filename;
	private String groupName;
	private AtomicBoolean darkmode = new AtomicBoolean();

	/**
	 * @author Ruben
	 * @return The controller
	 */
	public static ClientController getClient() {
		return controller;
	}

	/**
	 * Starts the connection with the server
	 * @author Ruben
	 */
	public ClientController(String username, String password, LoginController loginController) {
		this.loginController = loginController;
		try {
			socket = new Socket(InetAddress.getLocalHost(), 5343);
			outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			outputStream.flush();
			data = new Data(loginController, socket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to connect to server");
		}
		logIn(username, password);
		ClientController.controller = this;
	}

	/**
	 * 
	 * @return receiver
	 * @author Ruben
	 */
	public String getReceiver() {
		return receiver.getName();
	}

	/**
	 * Constructor that initializes the program
	 * @param username
	 * @param password
	 * @param userController
	 * @param image
	 * @author Ruben, Amir
	 */
	public ClientController(String username, String password, CreateUserController userController,
			BufferedImage image) {
		try {
			socket = new Socket(InetAddress.getLocalHost(), 5343);
			outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			outputStream.flush();
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
	 * @author Ruben
	 */
	public void createNewGroup(String groupName) {
		this.groupName = groupName;
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<GroupMessage> messages = new ArrayList<GroupMessage>();
		ArrayList<String> fileLog = new ArrayList<String>();
		ArrayList<Event> eventObjects = new ArrayList<Event>();
		try {
			outputStream.writeObject(new Group(users, messages, fileLog, eventObjects, groupName, user.getName()));
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Sends a request to server to add user to group
	 * @author Ruben, Amir
	 */
	public void addToGroup() {
		try {
			outputStream.writeObject(new AddObjectRequest("addGroupMember:" + groupName, user, user));
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Send a request to server to delet a account
	 * @param password
	 * @author Ruben, Amir
	 */
	public void deleteAccount(String password) {

		try {
			outputStream.writeObject(new AddObjectRequest("delUser", password, user));
			outputStream.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Creates an new account
	 * 
	 * @param name
	 * @param password
	 * @author Ruben
	 */
	public void createNewUser(String name, String password, BufferedImage image) {
		try {
			outputStream.writeObject(new CreateUserRequest(name, password, image));
			this.user = new User(name);
			outputStream.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a group message
	 * 
	 * @param message
	 * @param group
	 * @author Ruben
	 */
	public void createGroupMessage(String message) {
		try {
			outputStream.writeObject(new GroupMessage(message, user, group));
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a private message
	 * 
	 * @param message
	 * @param reciever
	 * @author Ruben
	 */
	public void createPrivateMessage(String message) {
		try {
			outputStream.writeObject(new PrivateMessage(message, user, receiver));
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a log in request to the server
	 * 
	 * @param userName
	 * @param password
	 * @author Ruben
	 */
	public void logIn(String username, String password) {
		try {
			outputStream.writeObject(new LoginRequest(username, password));
			this.user = new User(username);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a requst to server to download a file
	 * @author Ruben, Amir
	 * @param file
	 */
	public void download(String file) {

		try {
			outputStream.writeObject(new ObjectRequest("file", file, user));
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a event to a group
	 * 
	 * @param event
	 * @author Ruben
	 */
	public void addEvent(String comment, String date, String location) {
		String type = "event";
		try {
			Event event = new Event(group, user, date, comment, location);
			outputStream.writeObject(new AddObjectRequest(type, event, user));
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a user to the group
	 * 
	 * @param groupName
	 * @param userToAdd
	 * @author Ruben
	 */
	public void addGroupMember(String groupName, User userToAdd) {
		String type = "addGroupMember:" + groupName;
		try {
			outputStream.writeObject(new AddObjectRequest(type, userToAdd, user));
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that creates a socket.
	 * @author Ruben
	 */
	public void createSocket() {
		if (this.socket.isClosed()) {
			try {
				outputStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				this.socket = new Socket(InetAddress.getLocalHost(), 5343);
				outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				outputStream.flush();
				data.createConnection(socket);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Uploads a file to a group
	 * @author Ruben, Amir
	 * @param filename
	 * @param fileToSend
	 */
	public void sendFile(String filename, byte[] fileToSend) {
		try {
			outputStream.writeObject(new AddObjectRequest(
					"file:" + group.getGroupName() + ":" + filename, fileToSend, user));
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that closes the socket.
	 * @author Ruben
	 */
	public void closeSocket() {
		try {
			data.kill();
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Ruben, Amir
	 * @param filename
	 */
	public void setRequestedFile(String filename) {
		this.filename = filename;
	}

	/**
	 * Saves a file locally
	 * @author Ruben, Amir
	 * @param file
	 */
	public void saveToComputer(byte[] file) {
		String username = System.getProperty("user.name");
		try {
			FileOutputStream out = new FileOutputStream(
					"C:\\Users\\" + username + "\\Downloads\\" + this.filename);
			out.write(file);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that sets a reciever.
	 * @author Ruben
	 * @param name Name of the reciever.
	 */
	public void setReciver(String name) {
		this.receiver = new User(name);
	}

	/**
	 * @author Ruben, Amir
	 * @param name
	 */
	public void setGroup(String name) {
		this.group = new Group(name);
	}

	/**
	 * @author Ruben, Amir
	 * @return group
	 */
	public Group getGroup() {
		return this.group;
	}

	/**
	 * @author Ruben, Amir
	 * @return user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * set the darkmode
	 * @param set the boolean
	 * @author Alexander Måbrink
	 */
	public void setDarkMode(boolean set) {
		darkmode.set(set);
	}

	/**
	 * returns darkmode
	 * @return
	 * @author Alexander Måbrink
	 */
	public boolean getDarkMode() {
		return darkmode.get();
	}
}
