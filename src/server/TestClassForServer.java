package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TestClassForServer {
	public static void main(String args[]) {
		new ServerController(false, 2);//startar server
		new TestClassForServer();
		
	}
	public TestClassForServer() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new TestLogin();
	}
	
	
	public class TestConnection {
		public Socket socket;
		public TestConnection() {
			try {
				socket = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public class TestLogin {
		public Socket socket;
		public TestLogin() {
			try {
				socket = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				oos.writeObject(new CreateUserRequest("1","2"));//Ändra skapande av användare
				socket.close();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				socket = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream ooss = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream oiss = new ObjectInputStream(socket.getInputStream());
				ooss.writeObject(new LoginRequest("1","2")); //Ändra inlogg
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
