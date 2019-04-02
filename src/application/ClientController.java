package application;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.application.Application;

public class ClientController {
	private LoginUI loginUI;
	private	GroupChatUI groupChatUI;
	private MainUI mainUI;
	private CreateGroupUI createGroupUI;
	private CreateNewUserUI createNewUserUI;
	private PrivateChatUI privateChatUI;
	private User user;
	private Data data;
	private ObjectOutputStream oos;
	private Socket socket;

	public void start() {
		try {
			String ip = JOptionPane.showInputDialog("Ange IP");
			int socketNbr = Integer.parseInt(JOptionPane.showInputDialog("Ange socket"));
			socket = new Socket(ip, socketNbr);
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.flush();
			initializeLoginUI();
		} catch(ConnectException c) {
			c.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

	public void initializeMainUI() {
		Application.launch(MainUI.class);
	}

	public void initializeLoginUI() {
		Application.launch(LoginUI.class);
	}

	public void initializePrivateChatUI() {
		Application.launch(PrivateChatUI.class);
	}

	public void initializeCreateNewUserUI() {
		Application.launch(CreateNewUserUI.class);
	}

	public void initializeCreateGroupUI() {
		Application.launch(CreateGroupUI.class);
	}

	public void initializeGroupChatUI() {
		Application.launch(GroupChatUI.class);
	}

	public void createNewGroup(String groupName) {
		ArrayList<User> users = new ArrayList<User>();
		users.add(user);
		ArrayList<GroupMessage> messages = new ArrayList<GroupMessage>();
		ArrayList<String> fileLog = new ArrayList<String>();
		ArrayList<Event> eventObjects = new ArrayList<Event>();
		try {
			oos.writeObject(new Group(users, messages, fileLog, eventObjects));
			oos.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void createNewUser(String name, String password) {
		try {
			oos.writeObject(new CreateUserRequest(name, password));
			oos.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void createGroupMessage(String message, Group group) {
		try {
			oos.writeObject(new GroupMessage(message, user, group));
			oos.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void createPrivateMessage(String message, User reciever) {
		try {
			oos.writeObject(new PrivateMessage(message, user, reciever));
			oos.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void logIn(String userName, String password) {
		try {
			oos.writeObject(new LoginRequest(userName, password));
			oos.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void sendFile(File file, String groupName, String filename) {
		String type = "file:" + groupName + ":" + filename;
		try {
			oos.writeObject(new AddObjectRequest(type, file, user));
			oos.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void addEvent(Event event) {
		String type = "Event";
		try {
			oos.writeObject(new AddObjectRequest(type, event, user));
			oos.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void addGroupMember(String groupName, User userToAdd) {
		String type = "addGroupMember:" + groupName;
		try {
			oos.writeObject(new AddObjectRequest(type, userToAdd, user));
			oos.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ClientController controller = new ClientController();
		controller.start();

	}

}
