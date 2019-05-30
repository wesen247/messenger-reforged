package application;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import entity.*;
import javafx.application.Platform;

/**
 * Handles all input from the server
 * @author Ruben, Amir, Andre
 *
 */
public class Data {
	private LoginController loginController;
	private ObjectInputStream ois;
	private Socket socket;
	private ArrayList<Response> listResponse = new ArrayList<Response>();
	private ArrayList<ObjectRequest> listObjectRequest = new ArrayList<ObjectRequest>();
	private ArrayList<Group> listGroup = new ArrayList<Group>();
	private ArrayList<PrivateMessage> listPM = new ArrayList<PrivateMessage>();
	private ArrayList<UserUpdate> listUserUpdate = new ArrayList<UserUpdate>();
	private boolean alive = false;
	private static Data data;
	private HashMap<String, Buffer<String>> pmBuffer = new HashMap<String, Buffer<String>>();
	private static HashMap<String, ArrayList<GroupMessage>> groupMessageHashMap;
	private static HashMap<String, Group> hashMapGroups = new HashMap<String, Group>();
	private static ArrayList<User> listUsers;
	private HashMap<String, GroupMessageController> arrayListController;

	/**
	 * Constructor that initializes the class and starts the ServerListener thread
	 * @param loginUI LoginController
	 * @param socket
	 * @author Ruben, Amir, Andre
	 */
	public Data(LoginController loginUI, Socket socket) {
		groupMessageHashMap = new HashMap<String, ArrayList<GroupMessage>>();
		arrayListController = new HashMap<String, GroupMessageController>();
		this.loginController = loginUI;
		try {
			this.socket = socket;
			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		alive = true;
		new ServerListener().start();
		Data.data = this;
	}

	/**
	 * Constructor that initializes the class and starts the ServerListener thread
	 * @param userController CreateUserController
	 * @param socket
	 * @author Ruben, Amir
	 */
	public Data(CreateUserController userController, Socket socket) {
		groupMessageHashMap = new HashMap<String, ArrayList<GroupMessage>>();
		arrayListController = new HashMap<String, GroupMessageController>();
		try {
			this.socket = socket;
			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		alive = true;
		new ServerListener().start();
		Data.data = this;
	}

	/**
	 * 
	 * @return Data
	 * @author Ruben, Amir
	 */
	public static Data getData() {
		return data;
	}

	/**
	 * 
	 * @return ArrayList of responses
	 * @author Ruben, Amir
	 */
	public ArrayList<Response> getListResponse() {
		return listResponse;
	}

	/**
	 * 
	 * @param response Response object from the server
	 * @author Ruben, Amir
	 */
	public void setListResponse(Response response) {
		listResponse.add(response);
	}

	/**
	 * 
	 * @return ArrayList of ObjectRequests
	 * @author Ruben, Amir
	 */
	public ArrayList<ObjectRequest> getListObjectRequest() {
		return listObjectRequest;
	}

	/**
	 * Adds a objectRequest to listObjectRequest
	 * @param objectRequest
	 * @author Ruben, Amir
	 */
	public void setListObjectRequest(ObjectRequest objectRequest) {
		listObjectRequest.add(objectRequest);
	}

	/**
	 * 
	 * @return An ArrayList of Groups
	 * @author Ruben, Amir
	 */
	public ArrayList<Group> getListGroup() {
		return listGroup;
	}
	
	/**
	 * 
	 * @return ArrayList of users
	 * @author Ruben, Amir
	 */
	public static ArrayList<User> getUsers(){
		return listUsers;
	}
	
	/**
	 * Adds a group to the listGroup
	 * @param group
	 * @author Ruben, Amir
	 */
	public void setListGroup(Group group) {
		for (int i = 0; i < listGroup.size(); i++) {
			if (listGroup.get(i).getGroupName().equals(group.getGroupName())) {
				listGroup.remove(i);
				listGroup.add(group);
			} else if (i == listGroup.size() - 1) {
				listGroup.add(group);
			}
		}
		if (listGroup.size() == 0) {
			listGroup.add(group);
		}
	}

	/**
	 * 
	 * @return ArrayList of PrivateMessages
	 * @author Ruben, Amir
	 */
	public ArrayList<PrivateMessage> getListPM() {
		return listPM;
	}

	/**
	 * 
	 * @param pm adds a private message to the pm list
	 * @author Ruben, Amir
	 */
	public void setListPM(PrivateMessage pm) {
		listPM.add(pm);
	}

	/**
	 * 
	 * @return ArrayList of UserUpdates
	 * @author Ruben, Amir
	 */
	public ArrayList<UserUpdate> getListUserUpdate() {
		return listUserUpdate;
	}

	/**
	 * 
	 * @return ArrayList of Users
	 * @author Ruben, Amir
	 */
	public ArrayList<User> getUser() {
		return listUsers;
	}

	/**
	 * 
	 * @return ArrayList of Groups
	 * @author Ruben, Amir
	 */
	public ArrayList<Group> getGroup() {
		return listGroup;
	}

	/**
	 * 
	 * @param userUpdate
	 * @author Ruben, Amir
	 */
	public void setListUserUpdate(UserUpdate userUpdate) {
		listUserUpdate.add(userUpdate);
	}

	/**
	 * 
	 * @param socket
	 * @author Ruben, Amir
	 */
	public void createConnection(Socket socket) {
		this.socket = socket;
		try {
			this.ois = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		alive = true;
		new ServerListener().start();
	}

	/**
	 * Closes the input stream and kills the thread
	 * @author Ruben, Amir
	 */
	public void kill() {
		alive = false;
		try {
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param name
	 * @return Returns a name from the private message buffer
	 * @author Ruben, Amir, Andre
	 */
	public Buffer<String> getMessageBuffer(String name) {
		if (pmBuffer.containsKey(name)) {
			return pmBuffer.get(name);
		} else {
			pmBuffer.put(name, new Buffer<String>());
			return pmBuffer.get(name);
		}
	}

	/**
	 * 
	 * @return a groupMessage HashMap
	 * @author Ruben, Amir, Andre
	 */
	public static HashMap<String, ArrayList<GroupMessage>> getGroupMessage() {
		return groupMessageHashMap;
	}

	/**
	 * 
	 * @return a group HashMap
	 * @author Ruben, Amir, Andre
	 */
	public static HashMap<String, Group> getGroups() {
		return hashMapGroups;
	}

	/**
	 * 
	 * @param controller GroupMessageController
	 * @author Ruben, Amir
	 */
	public void addGroupListener(GroupMessageController controller) {
		arrayListController.put(ClientController.getClient().getGroup().getGroupName(), controller);
	}

	/**
	 * Thread that listens to the server and has a different response
	 * depending on the object that it receives
	 * @author Ruben, Amir
	 *
	 */
	private class ServerListener extends Thread {
		public void run() {
			try {
				while (alive) {
					Object object = ois.readObject();
					
					if (object instanceof ObjectRequest) {
						ObjectRequest objectRequest = (ObjectRequest) object;
						setListObjectRequest(objectRequest);
						
					} else if (object instanceof Response) {
						Response response = (Response) object;
						setListResponse(response);
						System.out.println(response.getType());
						
						if (response.getType().equals("loginFailed")) {
							String res = (String) response.getResponse();
							loginController.loginFailed(res);
						} else if (response.getType().equals("file")) {
							ClientController.getClient().saveToComputer((byte[]) response.getResponse());
						} else if (response.getType().equals("createGroupSuccessful")) {
							ClientController.getClient().addToGroup();
						} else if (response.getType().equals("groupCreateFailed")) {
							JOptionPane.showMessageDialog(null, "Failed to create group");
						} else if (response.getType().equals("createUserFailed")) {
							JOptionPane.showMessageDialog(null, "Failed to create user");
						} else if (response.getType().equals("addUserFailed")) {
							JOptionPane.showMessageDialog(null, "Failed to add user");
						} else if(response.getType().equals("delUserFailed")) {
							JOptionPane.showMessageDialog(null, "Wrong password");
						}
					}
					else if (object instanceof Group) {
						Group group = (Group) object;
						setListGroup(group);
						groupMessageHashMap.put(group.getGroupName(), group.getGroupMessages());
						hashMapGroups.put(group.getGroupName(), group);
						StartMenuController.getStartMenuController().setGroupList();
						if (GroupMessageController.getGroupMessageController() == null) {
						} else {
							GroupMessageController.getGroupMessageController().update();
						}
					}
					else if (object instanceof PrivateMessage) {
						PrivateMessage pm = (PrivateMessage) object;
						if (pmBuffer.containsKey(pm.getSender().getName())) {
							pmBuffer.get(pm.getSender().getName()).put(pm.getSender() + ": " + pm.getMessage());
						} else {
							pmBuffer.put(pm.getSender().getName(), new Buffer<String>());
							pmBuffer.get(pm.getSender().getName()).put(pm.getSender() + ": " + pm.getMessage());
						}
					}
					else if (object instanceof GroupMessage) {
						GroupMessage gm = (GroupMessage) object;
						if (groupMessageHashMap.containsKey(gm.getReceiver().getGroupName())) {
							groupMessageHashMap.get(gm.getReceiver().getGroupName()).add(gm);
						} else {
							groupMessageHashMap.put(gm.getReceiver().getGroupName(), new ArrayList<GroupMessage>());
							groupMessageHashMap.get(gm.getReceiver().getGroupName()).add(gm);
						}
						Platform.runLater(new Runnable() {
							public void run() {
								arrayListController.get(gm.getReceiver().getGroupName()).update();
							}
						});
					}
					else if (object instanceof UserUpdate) {
						UserUpdate userUpdate = (UserUpdate) object;
						listUsers = userUpdate.getUsers();
						try {
							StartMenuController.getStartMenuController().setOnlineList();
						} catch (Exception e) {
							System.err.println(e);
						}
					}
					else if (object instanceof StartUpdate) {
						StartUpdate startUpdate = (StartUpdate) object;
						listUsers = startUpdate.getOnlineUsers();
						listGroup = startUpdate.getGroups();
						for (int i = 0; i < startUpdate.getGroups().size(); i++) {
							groupMessageHashMap.put(startUpdate.getGroups().get(i).getGroupName(),
									startUpdate.getGroups().get(i).getGroupMessages());
							hashMapGroups.put(startUpdate.getGroups().get(i).getGroupName(),
									startUpdate.getGroups().get(i));
						}
						Platform.runLater(new Runnable() {
							public void run() {
								try {
									Main.showMainMenu();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						});
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				Platform.runLater(new Runnable() {
					public void run() {
						try {
							Main.showLogin();
							Main.getDeleteUserStage().close();
						} catch (IOException | NullPointerException e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
	}
}
