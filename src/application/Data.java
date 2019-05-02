package application;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

import entity.*;
import javafx.application.Platform;

public class Data {
	private Main main;
	private LoginController loginUI;
	private ObjectInputStream ois;
	private Socket socket;
	private ArrayList<Response> listResponse = new ArrayList<Response>();
	private ArrayList<ObjectRequest> listObjectRequest = new ArrayList<ObjectRequest>();
	private ArrayList<Group> listGroup = new ArrayList<Group>();
	private ArrayList<PrivateMessage> listPM = new ArrayList<PrivateMessage>();

	private ArrayList<UserUpdate> listUserUpdate = new ArrayList<UserUpdate>();
	private boolean alive = false;
	private CreateUserController userController;
	private StartMenuController mainMenuController;
	private static Data data;

	private HashMap<String, Buffer<String>> pmBuffer = new HashMap<String, Buffer<String>>();
	private static HashMap<String, ArrayList<GroupMessage>> groupMessageHashMap = new HashMap<String, ArrayList<GroupMessage>>();
	private static HashMap<String, Group> hashMapGroups = new HashMap<String, Group>();

	private ArrayList<User> list;
	private HashMap<String, ChatWindowGroupMessageController> arrayListController = new HashMap<String, ChatWindowGroupMessageController>();

	public Data(LoginController loginUI, Socket socket) {

		this.loginUI = loginUI;

		try {
			this.socket = socket;

			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

		} catch (IOException e) {

			e.printStackTrace();
		}
		alive = true;
		new ServerListener().start();
		this.data = this;
	}

	public Data(CreateUserController userController, Socket socket) {

		this.userController = userController;
		try {

			this.socket = socket;
			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

		} catch (IOException e) {

			e.printStackTrace();
		}
		alive = true;
		new ServerListener().start();
		this.data = this;
	}

	public void addMenuController(StartMenuController menuController) {
		this.mainMenuController = menuController;
	}

	public static Data getData() {
		return data;
	}

	public ArrayList<Response> getListResponse() {
		return listResponse;
	}

	public void setListResponse(Response response) {
		listResponse.add(response);
	}

	public ArrayList<ObjectRequest> getListObjectRequest() {
		return listObjectRequest;
	}

	public void setListObjectRequest(ObjectRequest objectRequest) {
		listObjectRequest.add(objectRequest);
	}

	public ArrayList<Group> getListGroup() {
		return listGroup;
	}

	public void setListGroup(Group group) {

		for (int i = 0; i < listGroup.size(); i++) {

			if (listGroup.get(i).getGroupName().equals(group.getGroupName())) {
				listGroup.remove(i);
				listGroup.add(group);
				
			} else if(i == listGroup.size() - 1) {
				listGroup.add(group);
			}
		}
		if( listGroup.size() == 0) {
			listGroup.add(group);
		}
		

	}

	public ArrayList<PrivateMessage> getListPM() {
		return listPM;
	}

	public void setListPM(PrivateMessage pm) {
		listPM.add(pm);
	}

	public ArrayList<UserUpdate> getListUserUpdate() {
		return listUserUpdate;
	}

	public ArrayList<User> getUser() {
		return list;
	}

	public ArrayList<Group> getGroup() {
		return listGroup;
	}

	public void setListUserUpdate(UserUpdate userUpdate) {
		listUserUpdate.add(userUpdate);
	}

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

	public void kill() {
		alive = false;
		try {
			ois.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public Buffer<String> getMessageBuffer(String name) {
		if (pmBuffer.containsKey(name)) {
			return pmBuffer.get(name);

		} else {
			pmBuffer.put(name, new Buffer<String>());
			return pmBuffer.get(name);
		}
	}

	public static HashMap<String, ArrayList<GroupMessage>> getGroupMessage() {
		return groupMessageHashMap;
	}

	public static HashMap<String, Group> getGroups() {
		return hashMapGroups;
	}

	public void addGroupListener(ChatWindowGroupMessageController controller) {

		arrayListController.put(ClientController.getClient().getGroup().getGroupName(), controller);

	}

	private class ServerListener extends Thread {

		public void run() {

			while (alive) {

				try {

					Object object = ois.readObject();

					if (object instanceof ObjectRequest) {

						ObjectRequest objectRequest = (ObjectRequest) object;
						setListObjectRequest(objectRequest);

					} else if (object instanceof Response) {

						Response response = (Response) object;
						setListResponse(response);

						if (response.getType().equals("loginFailed")) {

							String res = (String) response.getResponse();
							loginUI.loginFailed(res);

						}

					}

					else if (object instanceof Group) {

						Group group = (Group) object;
						setListGroup(group);
						groupMessageHashMap.put(group.getGroupName(), group.getGroupMessages());
						hashMapGroups.put(group.getGroupName(), group);
						StartMenuController.getStartMenuController().setGroupList();
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
						System.out.println(gm.getReceiver().getGroupName());

						Platform.runLater(new Runnable() {

							public void run() {
								arrayListController.get(gm.getReceiver().getGroupName()).update();

							}
						});

					}

					else if (object instanceof UserUpdate) {
						UserUpdate userUpdate = (UserUpdate) object;
						list = userUpdate.getUsers();

						try {
							StartMenuController.getStartMenuController().setOnlineList();

						} catch (Exception e) {
							System.err.println(e);
						}

					}

					else if (object instanceof StartUpdate) {

						StartUpdate startUpdate = (StartUpdate) object;
						list = startUpdate.getOnlineUsers();

						System.out.println(startUpdate.getGroups().size());

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

									main.showMainMenu();

								} catch (IOException e) {
									e.printStackTrace();
								}

							}
						});

					}
				} catch (ClassNotFoundException | IOException e) {
					System.err.println("Disconnected");
				}
			}
		}
	}
}
