package application;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import javafx.application.Application;
import javafx.application.Platform;
import entity.*;

/**
 * Controller class
 * 
 * @author Ruben
 *
 */
public class ClientController {
	private LoginUI loginUI = new LoginUI();

	private GroupChatUI groupChatUI;
	private MainUI mainUI;
	private CreateGroupUI createGroupUI;
	private CreateNewUserUI createNewUserUI;
	private PrivateChatUI privateChatUI;
	private User user;
	private BufferedImage bImage;
	private Data data;

	private ObjectOutputStream oos;
	private Socket socket;

	/**
	 * Starts the connection with the server
	 */
	public ClientController() {
		try {
			// String ip = JOptionPane.showInputDialog("Ange IP");
			// int socketNbr = Integer.parseInt(JOptionPane.showInputDialog("Ange socket"));
			System.out.println("Rövhål");
			socket = new Socket(InetAddress.getLocalHost(), 5343);
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			oos.flush();
			data = new Data(loginUI, socket);

		} catch (ConnectException c) {
			c.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Creates an new group object and sends it to the server
	 * 
	 * @param groupName
	 */
	public void createNewGroup(String groupName) {
		ArrayList<User> users = new ArrayList<User>();
		users.add(user);
		ArrayList<GroupMessage> messages = new ArrayList<GroupMessage>();
		ArrayList<String> fileLog = new ArrayList<String>();
		ArrayList<Event> eventObjects = new ArrayList<Event>();
		try {
			oos.writeObject(new Group(users, messages, fileLog, eventObjects, groupName));
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

	public void setPicture(BufferedImage image) {

		this.bImage = image;
	}

	public void createNewUser(String name, String password) {
		try {

			oos.writeObject(new CreateUserRequest(name, password, bImage));
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
	public void createGroupMessage(String message, Group group) {
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
	public void createPrivateMessage(String message, User reciever) {
		try {
			oos.writeObject(new PrivateMessage(message, user, reciever));
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
	public void logIn(String userName, String password) {
		try {
			oos.writeObject(new LoginRequest(userName, password));
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a file to a group
	 * 
	 * @param file
	 * @param groupName
	 * @param filename
	 */
	public void sendFile(File file, String groupName, String filename) {
		String type = "file:" + groupName + ":" + filename;
		try {
			oos.writeObject(new AddObjectRequest(type, file, user));
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
	public void addEvent(Event event) {
		String type = "Event";
		try {
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

}
