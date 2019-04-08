package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserClient {
	private ServerController controller;
	private Buffer<Object> sendBuffer;
	private Socket socket;
	private UserHandler userHandler;
	private User user;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public UserClient(ServerController controller, Socket socket, UserHandler userHandler, User user, ObjectOutputStream oos, ObjectInputStream ois) {
		this.controller = controller;
		this.sendBuffer = new Buffer<Object>();
		this.socket = socket;
		this.userHandler = userHandler;
		this.user = user;

			this.oos = oos;
			this.ois = ois;
	
		controller.addTask(new UserListener());
		controller.addTask(new UserSender());
	}

	public User getUser() {
		return user;
	}

	public void addToBuffer(Object sendObject) {
		sendBuffer.put(sendObject);
	}
	public class UserListener implements Runnable{
		public void run() {
			Object incomming;
			try {
				while(!Thread.interrupted()) {
					incomming = ois.readObject();
					controller.newObjectFromUser(incomming);
				}
			} catch (ClassNotFoundException | IOException e) {
				System.err.println(user.getName()+" Disconnected");
				userHandler.disconnect(user.getName());
				
				
			}
		}
	}
	public class UserSender implements Runnable {
		public void run() {
			try {
				while(!Thread.interrupted()) {
					oos.reset();
					oos.writeObject(sendBuffer.get());
					oos.flush();
				}
			} catch (IOException | InterruptedException e) {
				System.err.println(user.getName()+" Socket stängde");
			}
		}
	}
}
