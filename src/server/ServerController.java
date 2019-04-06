package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import server.TestClassForServer.TestConnection;

public class ServerController {
	private UserHandler userHandler;
	private GroupHandler groupHandler;
	private Buffer<Runnable> taskBuffer;

	public ServerController(boolean useBackup, int maximumUsers) {
		userHandler = new UserHandler(useBackup,this);
		groupHandler = new GroupHandler(useBackup, this);
		taskBuffer = new Buffer<Runnable>();
		for(int i = 0;i<5+maximumUsers*2;i++) {
			new Worker().start();;
			System.out.println("New worker started "+i);
		}
		System.out.println("----------------------------------------------------");
		addTask(new IncommningConnections());
	}
	public Group getGroup(String Group) {
		return groupHandler.getGroup(Group);
	}

	public void addTask(Runnable task) {
		taskBuffer.put(task);
	}

	public void newObjectFromUser(Object incomming) {

		if(incomming instanceof AddObjectRequest) {
			AddObjectRequest request = (AddObjectRequest) incomming;
			String[] splitType = request.getType().split(":");
			if(splitType[0] == "file") {
				//Måste ta reda på hur skicka filer ska ske

			}
			else if(splitType[0] == "addGroupMember") {
				groupHandler.addMember(new Group(splitType[1]), (User)request.getObjectToAdd());
				userHandler.addMemberOf((User) request.getObjectToAdd(), splitType[1]);
			}
			else if(splitType[0] == "event") {
				Event event = (Event) request.getObjectToAdd();
				groupHandler.addEvent(event.getGroup().getGroupName(), event);
			}
		}
		else if(incomming instanceof Group) {
			groupHandler.addGroup((Group) incomming);
		}
		else if(incomming instanceof GroupMessage) {
			groupHandler.newMessage((GroupMessage) incomming);
		}
		else if(incomming instanceof PrivateMessage) {
			PrivateMessage message = (PrivateMessage) incomming;
			send(message.getReceiver(),message);
		}

	}

	public void send(User receiver, Object sendObject) {
		userHandler.send(receiver, sendObject);
	}

	public class Worker extends Thread{
		public void run() {
			while(true) {
				try {
					System.out.println("tråd klar med task");
					taskBuffer.get().run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public class IncommningConnections implements Runnable{
		public void run() {
			try {
				ServerSocket serverSocket = new ServerSocket(5434);
				Runnable runnable;
				while(true) {
					System.out.println("Lyssnar efter användare");
					runnable = new LoginHandler(serverSocket.accept());
					addTask(runnable);
					System.out.println("Task added");
				}
			} catch (IOException e) {
				e.printStackTrace();
				//gör backup
				System.exit(0);
			}
		}
	}
	public class LoginHandler implements Runnable{
		private Socket socket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		public LoginHandler(Socket socket) {
			System.out.println("LoginHandler skapad");
			this.socket = socket;		
		}
		public void run() {
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			boolean accepted = false;
			System.out.println("Lyssnar på login eller användare från klient");
			Object fromUser;
			while(!accepted) {
				try {
					fromUser = ois.readObject();
					System.out.println("LoginHandler: Objekt Motaget");
					if(fromUser instanceof CreateUserRequest) {
						System.out.println("LoginHandler: Type CreateUserRequest");
						if(userHandler.newUser((CreateUserRequest) fromUser, socket, oos, ois)) {
							accepted = true;
							System.out.println("LoginHandler: CreateUserRequest lyckad");
						}else {
							System.out.println("LoginHandler: CreateUserRequest misslyckad");
							oos.writeObject(new Response("createUserFailed", "User creation failed"));
							oos.flush();
						}
					}
					else if(fromUser instanceof LoginRequest) {
						System.out.println("LoginHandler: Type LoginRequest");
						if(userHandler.attemptLogin((LoginRequest) fromUser, socket, oos, ois)) {
							accepted = true;
							System.out.println("LoginHandler: LoginRequest godkänd");
						}else {
							System.out.println("LoginHandler: LoginRequest misslyckad");
							oos.writeObject(new Response("loginFailed", "Login failed"));
							oos.flush();
						}
					}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
