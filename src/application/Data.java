package application;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import entity.*;
import javafx.application.Platform;

public class Data {
	private UIController loginUI;
	private ObjectInputStream ois;
	private Socket socket;
	private ArrayList<Response> listResponse = new ArrayList<Response>();
	private ArrayList<ObjectRequest> listObjectRequest = new ArrayList<ObjectRequest>();
	private ArrayList<Group> listGroup = new ArrayList<Group>();
	private ArrayList<PrivateMessage> listPM = new ArrayList<PrivateMessage>();
	private ArrayList<GroupMessage> listGM = new ArrayList<GroupMessage>();
	private ArrayList<UserUpdate> listUserUpdate = new ArrayList<UserUpdate>();
	private boolean alive = false;
	
	public Data(UIController loginUI, Socket socket) {

		this.loginUI = loginUI;

		try {
			this.socket = socket;

			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

		} catch (IOException e) {

			e.printStackTrace();
		}
		alive = true;
		new ServerListener().start();
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
		listGroup.add(group);
	}

	public ArrayList<PrivateMessage> getListPM() {
		return listPM;
	}

	public void setListPM(PrivateMessage pm) {
		listPM.add(pm);
	}

	public ArrayList<GroupMessage> getListGM() {
		return listGM;
	}

	public void setListGM(GroupMessage gm) {
		listGM.add(gm);
	}

	public ArrayList<UserUpdate> getListUserUpdate() {
		return listUserUpdate;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

							String res = response.getType();

							loginUI.loginFalse(res);

						}

					}

					else if (object instanceof Group) {

						Group group = (Group) object;
						setListGroup(group);
					}

					else if (object instanceof PrivateMessage) {

						PrivateMessage pm = (PrivateMessage) object;
						setListPM(pm);
					}

					else if (object instanceof GroupMessage) {

						GroupMessage gm = (GroupMessage) object;
						setListGM(gm);
					}

					else if (object instanceof UserUpdate) {
						UserUpdate userUpdate = (UserUpdate) object;
						setListUserUpdate(userUpdate);

					}

					else if (object instanceof StartUpdate) {
						
						StartUpdate startUpdate = (StartUpdate) object;

						loginUI.loginTrue();
						
					}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}

			}

		}

	}

}
