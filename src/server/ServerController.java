package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import entity.AddObjectRequest;
import entity.CreateUserRequest;
import entity.Event;
import entity.Group;
import entity.GroupMessage;
import entity.LoginRequest;
import entity.ObjectRequest;
import entity.PrivateMessage;
import entity.Response;
import entity.User;
import logger.Logg;

public class ServerController {
	private Logg logg;
	private UserHandler userHandler;
	private GroupHandler groupHandler;
	private Buffer<Runnable> taskBuffer;
	private IncommningConnections connect;

	/**
	 * Constructor
	 * @param useBackup If true it will load a backup stored locally
	 * @param maximumUsers Number of maximum users. Decides how many threads that will be created.
	 * @author André
	 */
	public ServerController(boolean useBackup, int maximumUsers) {
		String username = System.getProperty("user.name");
		File directory = new File("C:\\users\\"+ username +"\\Messenger-reforged");
		if(!directory.exists()) {
			try {
				directory.mkdir();
			}catch(SecurityException e) {
				e.printStackTrace();
			}
		}
		userHandler = new UserHandler(useBackup,this);
		groupHandler = new GroupHandler(useBackup, this);
		taskBuffer = new Buffer<Runnable>();

		for(int i = 0; i < 5 + maximumUsers * 2; i++) {
			new Worker().start();
			logg.writeToLog("New worker started " + i);
		}
		addTask(connect = new IncommningConnections());
	}

	/**
	 * Returns a group with the same name as the parameter group
	 * @param group The name of the group
	 * @return group the group
	 * @author André
	 */
	public Group getGroup(String group) {
		return groupHandler.getGroup(group);
	}

	/**
	 * Adds a runnable to the taskbuffer to be executed
	 * @param task task to be executed
	 * @author André	
	 */
	public void addTask(Runnable task) {
		logg.writeToLog("Task added to buffer");
		taskBuffer.put(task);
	}

	/**
	 * Kills the server
	 * @author André
	 */
	public void kill() {
		connect.kill();
	}

	/**
	 * Receives a object from a user with instructions 
	 * @param incomming The object
	 * @author André
	 */
	public void newObjectFromUser(Object incomming) {
		try {
			logg.writeToLog("Servercontroller motagit object");
			if(incomming instanceof AddObjectRequest) {
				AddObjectRequest request = (AddObjectRequest) incomming;
				String[] splitType = request.getType().split(":");

				if(splitType[0].equals("file")) {
					groupHandler.addFile(splitType[1], splitType[2], (byte[])((AddObjectRequest) incomming).getObjectToAdd());
				}
				else if(splitType[0].equals( "addGroupMember")) {
					if(userHandler.addMemberOf((User) request.getObjectToAdd(), splitType[1])) {
						groupHandler.addMember(new Group(splitType[1]), (User)request.getObjectToAdd());
					}
					else {
						send(request.getUser(), new Response("addUserFailed","Add user failed"));
					}
				}
				else if(splitType[0].equals("event")) {
					Event event = (Event) request.getObjectToAdd();
					groupHandler.addEvent(event.getGroup().getGroupName(), event);
				}
				else if(splitType[0].equals("delUser")) {
					userHandler.removeUser(request.getUser(), (String) request.getObjectToAdd());
				}
			}

			else if(incomming instanceof Group) {
				groupHandler.addGroup((Group) incomming, new User(((Group) incomming).getCreator()));
			}
			else if(incomming instanceof GroupMessage) {
				groupHandler.newMessage((GroupMessage) incomming);
			}
			else if(incomming instanceof PrivateMessage) {
				PrivateMessage message = (PrivateMessage) incomming;
				send(message.getReceiver(),message);
			}
			else if(incomming instanceof ObjectRequest) {
				groupHandler.sendFile(((ObjectRequest)incomming).getRequest(), ((ObjectRequest)incomming).getUser());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends an object to a user
	 * @param receiver User thats should receive the object
	 * @param sendObject the object
	 * @author André
	 */
	public void send(User receiver, Object sendObject) {
		userHandler.send(receiver, sendObject);
	}

	/**
	 * Worker class. Waits for task in taskBuffer
	 * @author andre
	 *
	 */
	public class Worker extends Thread{
		public void run() {
			while(true) {
				try {
					taskBuffer.get().run();
					logg.writeToLog("tråd klar med task");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Listens for users to connect.
	 * @author andre
	 *
	 */
	public class IncommningConnections implements Runnable{
		ServerSocket serverSocket;

		/**
		 * Kills the server
		 * @author André
		 */
		public void kill() {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void run() {
			try {
				serverSocket = new ServerSocket(5343);
				Runnable runnable;
				while(true) {
					logg.writeToLog("Lyssnar efter användare");
					runnable = new LoginHandler(serverSocket.accept());
					logg.writeToLog("Användare hittad");
					addTask(runnable);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void groupUpdate(Group group) {
		groupHandler.groupUpdate(group);
	}

	/**
	 * Listens for LoginRequest or CreateUserRequest from user.
	 * @author andre
	 *
	 */
	public class LoginHandler implements Runnable{
		private Socket socket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		public LoginHandler(Socket socket) {
			logg.writeToLog("LoginHandler skapad");
			this.socket = socket;		
		}
		public void run() {
			try {
				ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				oos.flush();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			boolean accepted = false;
			logg.writeToLog("Lyssnar på login eller användare från klient");
			Object fromUser;
			try {
				while(!accepted) {
					fromUser = ois.readObject();
					logg.writeToLog("LoginHandler: Objekt Motaget");
					if(fromUser instanceof CreateUserRequest) {
						logg.writeToLog("LoginHandler: Type CreateUserRequest");
						CreateUserRequest createUser = (CreateUserRequest) fromUser;
						if(createUser.getName().length() > 2 && createUser.getPassword().length() > 2 && userHandler.newUser(createUser, socket, oos, ois)) {
							accepted = true;
							logg.writeToLog("LoginHandler: CreateUserRequest lyckad");
						}else {
							logg.writeToLog("LoginHandler: CreateUserRequest misslyckad");
							oos.writeObject(new Response("createUserFailed", "User creation failed"));
							oos.flush();
						}
					}
					else if(fromUser instanceof LoginRequest) {
						logg.writeToLog("LoginHandler: Type LoginRequest");
						if(userHandler.attemptLogin((LoginRequest) fromUser, socket, oos, ois)) {
							accepted = true;
							logg.writeToLog("LoginHandler: LoginRequest godkänd");
						}else {
							logg.writeToLog("LoginHandler: LoginRequest misslyckad");
							oos.writeObject(new Response("loginFailed", "Login failed"));
							oos.flush();
						}
					}
				}
			} catch (ClassNotFoundException | IOException e) {
				logg.writeToLog("Användare avbröt inloggning");
			}
		}
	}
	public static void main(String args[]) {
		new ServerController(true, 100);
	}
}
