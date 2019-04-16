package server;

import java.io.IOException;
import entity.*;
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
	/**
	 * Constructor
	 * @param controller Controller
	 * @param socket Connection with the user
	 * @param userHandler The userhandler
	 * @param user Information about the user
	 * @param oos ObjectOutputStream
	 * @param ois ObjectInputStream
	 * @author André
	 */
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
	/**
	 * Returns the user associated with this UserClient
	 * @return The user
	 * @author André
	 */
	public User getUser() {
		return user;
	}
	/**
	 * Sends object to user
	 * @param sendObject Object to be sent 
	 * @author André
	 */
	public void addToBuffer(Object sendObject) {
		sendBuffer.put(sendObject);
	}
	/**
	 * Listens for objects from users
	 * @author André
	 *
	 */
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
	/**
	 * Sends object to users
	 * @author André
	 *
	 */
	public class UserSender implements Runnable {
		public void run() {
			try {
				while(!Thread.interrupted()) {
					oos.reset();
					oos.writeObject(sendBuffer.get());
					oos.flush();
					System.out.println("Sent");
				}
			} catch (IOException | InterruptedException e) {
				System.err.println(user.getName()+" Socket stängde");
			}
		}
	}
}
