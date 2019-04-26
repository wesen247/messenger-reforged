package server;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import entity.*;
/**
 * Class that tests the server
 * @author andre
 *
 */
public class TestClassForServer {
	private ServerController controller;
	private BufferedImage bImage;
	public static void main(String args[]) {
		new TestClassForServer();

	}
	/**
	 * Constructor
	 */
	public TestClassForServer() {
		try {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			j.showOpenDialog(new JFrame());
			bImage = ImageIO.read(j.getSelectedFile());

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//		controller = new ServerController(false, 20);//startar server
		//		new AddEventInGroup();
		//		controller.kill();
		//		
		//		controller = new ServerController(false, 20);
		//		new TestConnection();
		//		controller.kill();
		//		
		//controller = new ServerController(false, 20);
		//	new TestLogin();
		//		controller.kill();

//		controller = new ServerController(false, 20);
//		new LoginMany();
//		controller.kill();
		////		
		////		controller = new ServerController(false, 20);
		//		new SendPm();
		//		controller.kill();
		//		
		//		controller = new ServerController(false, 20);
		//		new AddGroupMemberRequest();
		//		controller.kill();
//		//		
//				controller = new ServerController(false, 20);
//				new SendGroupMessage();
//				controller.kill();
//		//		
		//		controller = new ServerController(false, 20);
		//		new CreateGroup();
				
				controller = new ServerController(false, 20);
				new SendFileInGroup();
			//	controller.kill();
		
	}

	/**
	 * Tries to connect to the server
	 * @author andre
	 *
	 */
	public class TestConnection {
		public Socket socket;
		public TestConnection() {
			try {
				socket = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Tries to connect to server and login
	 * @author andre
	 *
	 */
	public class TestLogin {
		public Socket socket;
		public TestLogin() {
			try {
				socket = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2",bImage));//Ändra skapande av användare
				oos.flush();
				StartUpdate d = (StartUpdate)ois.readObject();
				socket.close();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				socket = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream ooss = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				ooss.flush();
				ObjectInputStream oiss = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				ooss.writeObject(new LoginRequest("1","2")); //Ändra inlogg
				ooss.flush();
				StartUpdate d1 = (StartUpdate)oiss.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

	}
	/**
	 * Logins with many users
	 * @author andre
	 *
	 */
	public class LoginMany{
		public Socket socket;
		public LoginMany() {
			try {
				Socket socket1 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket1.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2",bImage));//Ändra skapande av användare
				oos.flush();


				Thread.sleep(1000);

				Socket socket2 = new Socket(InetAddress.getLocalHost(), 5434);
				oos = new ObjectOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
				oos.flush();
				ois = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
				oos.writeObject(new CreateUserRequest("2","2",bImage));//Ändra skapande av användare
				oos.flush();


				Thread.sleep(1000);

				Socket 	socket3 = new Socket(InetAddress.getLocalHost(), 5434);
				oos = new ObjectOutputStream(new BufferedOutputStream(socket3.getOutputStream()));
				oos.flush();
				ois = new ObjectInputStream(new BufferedInputStream(socket3.getInputStream()));
				oos.writeObject(new CreateUserRequest("3","2",bImage));//Ändra skapande av användare
				oos.flush();


				Thread.sleep(1000);

				Socket socket4 = new Socket(InetAddress.getLocalHost(), 5434);
				oos = new ObjectOutputStream(new BufferedOutputStream(socket4.getOutputStream()));
				oos.flush();
				ois = new ObjectInputStream(new BufferedInputStream(socket4.getInputStream()));
				oos.writeObject(new CreateUserRequest("4","2",bImage));//Ändra skapande av användare
				oos.flush();


				Thread.sleep(1000);

				Socket socket5 = new Socket(InetAddress.getLocalHost(), 5434);
				oos = new ObjectOutputStream(new BufferedOutputStream(socket5.getOutputStream()));
				oos.flush();
				ois = new ObjectInputStream(new BufferedInputStream(socket5.getInputStream()));
				oos.writeObject(new CreateUserRequest("5","2",bImage));//Ändra skapande av användare
				oos.flush();


				Thread.sleep(1000);

				Socket socket6 = new Socket(InetAddress.getLocalHost(), 5434);
				oos = new ObjectOutputStream(new BufferedOutputStream(socket6.getOutputStream()));
				oos.flush();
				ois = new ObjectInputStream(new BufferedInputStream(socket6.getInputStream()));
				oos.writeObject(new CreateUserRequest("6","2",bImage));//Ändra skapande av användare
				oos.flush();
				StartUpdate s = (StartUpdate) ois.readObject();

				//skriver ut innehåll i StartUpdate
				System.out.println(s.getOnlineUsers().size()+"");
				System.out.println(s.getAllUsers().toString()+"");

				//disconnectar en användare
				socket1.close();

				Thread.sleep(1000);


				//skriver ut innehåll i UserUpdates
				UserUpdate s1 = (UserUpdate) ois.readObject();
				System.out.println(s1.getUsers());
				UserUpdate s2 = (UserUpdate) ois.readObject();
				System.out.println(s2.getUsers());


			} catch (IOException | InterruptedException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

	}
	/**
	 * Logins with two users and send a privateMessage
	 * @author andre
	 *
	 */
	public class SendPm{

		public SendPm(){
			try {
				//Skapar användare
				Socket socket1 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket1.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2",bImage));//Ändra skapande av användare
				oos.flush();
				Thread.sleep(1000);

				Socket socket2 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos1 = new ObjectOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
				oos1.flush();
				ObjectInputStream ois1 = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
				oos1.writeObject(new CreateUserRequest("2","2",bImage));//Ändra skapande av användare
				oos1.flush();
				Thread.sleep(1000);

				//Läser userUpdates och StartUpdates
				ois1.readObject();
				ois1.readObject();
				ois.readObject();
				ois.readObject();
				ois.readObject();
				System.out.println("Read klart");

				//skickar PrivateMessage
				oos1.writeObject(new PrivateMessage("hejh ej", new User("2"), new User("1")));
				oos1.flush();
				PrivateMessage message = (PrivateMessage) ois.readObject();
				//Skriver ut meddelande
				System.out.println(message.getMessage());

			}catch(Exception e) {

			}

		}

	}
	/**
	 * Adds an event in a group
	 * @author andre
	 *
	 */
	public class AddEventInGroup{
		public AddEventInGroup() {

			try {
				//Skapar användare
				Socket socket1 = new Socket(InetAddress.getLocalHost(), 5434);

				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream((socket1.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2",bImage));//Ändra skapande av användare
				oos.flush();
				Thread.sleep(1000);

				Socket socket2 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos1 = new ObjectOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
				oos1.flush();
				ObjectInputStream ois1 = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
				oos1.writeObject(new CreateUserRequest("2","2",bImage));//Ändra skapande av användare
				oos1.flush();
				Thread.sleep(1000);

				//Lägger till användare i grupp
				ArrayList<User> members = new ArrayList<User>();
				members.add(new User("2"));
				members.add(new User("1"));
				//skapar grupp
				oos.writeObject(new Group(members, new ArrayList<GroupMessage>(), new ArrayList<String>(),new ArrayList<Event>(), "grupp1"));
				oos.flush();

				//Skriver vad Användare får tillbaka från server
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois1.readObject());
				System.out.println(ois1.readObject());
				System.out.println(ois1.readObject());
				//Skapar event och skickar
				Group g = new Group(new ArrayList<User>(), new ArrayList<GroupMessage>(), new ArrayList<String>(),new ArrayList<Event>(), "grupp1");
				oos.writeObject(new AddObjectRequest("event", new Event(g,new User("1"),1,"hejhej"),new User("1")));
				oos.flush();
				//skriver vad användare får tillbaka
				g = (Group) ois.readObject();
				System.out.println(g.getEvents().size());
				System.out.println(ois1.readObject());

			}catch(Exception e) {

			}

		}
	}
	/**
	 * Adds a User in a group
	 * @author andre
	 *
	 */
	public class AddGroupMemberRequest{

		public AddGroupMemberRequest() {
			try {
				//Skapar användare
				Socket socket1 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket1.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2",bImage));//Ändra skapande av användare
				oos.flush();
				Thread.sleep(1000);

				Socket socket2 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos1 = new ObjectOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
				oos1.flush();
				ObjectInputStream ois1 = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
				oos1.writeObject(new CreateUserRequest("2","2",bImage));//Ändra skapande av användare
				oos1.flush();
				Thread.sleep(1000);

				//Lägger till användare i grupp
				ArrayList<User> members = new ArrayList<User>();
				members.add(new User("20"));
				members.add(new User("1"));
				//skapar grupp
				oos.writeObject(new Group(members, new ArrayList<GroupMessage>(), new ArrayList<String>(),new ArrayList<Event>(), "grupp1"));
				oos.flush();

				//Skriver vad Användare får tillbaka från server
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois1.readObject());
				System.out.println(ois1.readObject());


				//skickar en request att lägga till medlem
				oos.writeObject(new AddObjectRequest("addGroupMember:grupp1",new User("2"),new User("1")));
				oos.flush();
				System.out.println("AddObjectRequest skickad");
				//skriver ut om användaren får svar
				System.out.println(ois1.readObject());
				System.out.println(ois.readObject());
			}catch(Exception e) {

			}
		}
	}
	/**
	 * Sends a message in a group
	 * @author andre
	 *
	 */
	public class SendGroupMessage{
		public SendGroupMessage() {
			try {
				//Skapar användare
				Socket socket1 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket1.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2",bImage));//Ändra skapande av användare
				oos.flush();
				Thread.sleep(1000);

				Socket socket2 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos1 = new ObjectOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
				oos1.flush();
				ObjectInputStream ois1 = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
				oos1.writeObject(new CreateUserRequest("2","2",bImage));//Ändra skapande av användare
				oos1.flush();
				Thread.sleep(1000);

				//Lägger till användare i grupp
				ArrayList<User> members = new ArrayList<User>();
				members.add(new User("20"));
				members.add(new User("1"));
				//skapar grupp
				Group g = new Group(members, new ArrayList<GroupMessage>(), new ArrayList<String>(),new ArrayList<Event>(), "grupp1");
				oos.writeObject(new Group(members, new ArrayList<GroupMessage>(), new ArrayList<String>(),new ArrayList<Event>(), "grupp1"));
				oos.flush();

				//Skriver vad Användare får tillbaka från server
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois1.readObject());
				System.out.println(ois1.readObject());

				//lägger till medlem i gruppen och skickar ett gruppmeddelande 
				oos.writeObject(new AddObjectRequest("addGroupMember:grupp1",new User("2"),new User("1")));
				oos.flush();
				oos.writeObject(new GroupMessage("hejhej",new User("1"), g));
				oos.writeObject(new GroupMessage("hejhej",new User("1"), g));
				oos.writeObject(new GroupMessage("hejhej",new User("1"), g));
				oos.flush();
				System.out.println(ois.readObject());
				System.out.println(ois1.readObject());

				System.out.println(ois1.readObject());
				GroupMessage message = (GroupMessage)ois.readObject();
				System.out.println(message.getMessage());
				
				
				


			}catch(Exception e) {

			}


		}

	}
	/**
	 * Creates a group
	 * @author andre
	 *
	 */
	public class CreateGroup {
		public CreateGroup() {
			try {
				//Skapar användare
				Socket socket1 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket1.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2",bImage));//Ändra skapande av användare
				oos.flush();
				Thread.sleep(1000);

				Socket socket2 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos1 = new ObjectOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
				oos1.flush();
				ObjectInputStream ois1 = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
				oos1.writeObject(new CreateUserRequest("2","2",bImage));//Ändra skapande av användare
				oos1.flush();
				Thread.sleep(1000);

				//Lägger till användare i grupp
				ArrayList<User> members = new ArrayList<User>();
				members.add(new User("2"));
				members.add(new User("1"));
				//skapar grupp
				oos.writeObject(new Group(members, new ArrayList<GroupMessage>(), new ArrayList<String>(),new ArrayList<Event>(), "grupp1"));
				oos.flush();

				//Skriver vad Användare får tillbaka från server
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois1.readObject());
				System.out.println(ois1.readObject());
				System.out.println(ois1.readObject());


			}catch(Exception e) {

			}
		}
	}
	public class SendFileInGroup{
		public SendFileInGroup() {
			try {
				//Skapar användare
				Socket socket1 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket1.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2",bImage));//Ändra skapande av användare
				oos.flush();
				Thread.sleep(1000);

				Socket socket2 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos1 = new ObjectOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
				oos1.flush();
				ObjectInputStream ois1 = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
				oos1.writeObject(new CreateUserRequest("2","2",bImage));//Ändra skapande av användare
				oos1.flush();
				Thread.sleep(1000);

				//Lägger till användare i grupp
				ArrayList<User> members = new ArrayList<User>();
				members.add(new User("2"));
				members.add(new User("1"));
				//skapar grupp
				Group g = new Group(members, new ArrayList<GroupMessage>(), new ArrayList<String>(),new ArrayList<Event>(), "grupp1");
				oos.writeObject(new Group(members, new ArrayList<GroupMessage>(), new ArrayList<String>(),new ArrayList<Event>(), "grupp1"));
				oos.flush();

				//Skriver vad Användare får tillbaka från server
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois.readObject());
				System.out.println(ois1.readObject());
				System.out.println(ois1.readObject());

				//lägger till medlem i gruppen och skickar ett gruppmeddelande 
				oos.writeObject(new AddObjectRequest("addGroupMember:grupp1",new User("2"),new User("1")));
				oos.flush();
				oos.writeObject(new GroupMessage("hejhej",new User("1"), g));
				oos.writeObject(new GroupMessage("hejhej",new User("1"), g));
				oos.writeObject(new GroupMessage("hejhej",new User("1"), g));
				oos.flush();
				System.out.println(ois.readObject());
				System.out.println(ois1.readObject());

				System.out.println(ois1.readObject());
				GroupMessage message = (GroupMessage)ois.readObject();
				System.out.println(message.getMessage());
				
				
				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				j.showOpenDialog(new JFrame());
				byte[] content = null;
				content = Files.readAllBytes(j.getSelectedFile().toPath());
				System.err.println("Content "+content.length);
				oos.writeObject(new AddObjectRequest("file:grupp1:test.jpg",content,new User("1")));
				oos.flush();
				
				System.err.println("Fil skickad");
				
				System.out.println(((GroupMessage)ois.readObject()).getMessage());
				System.out.println(((GroupMessage)ois.readObject()).getMessage());
				System.out.println(ois.readObject());
				
				
				System.out.println("innan rquest");
				ObjectRequest r = new ObjectRequest("file", "test.jpg", new User("1"));
				System.out.println("r skapad");
				oos.writeObject(r);
				System.out.println("r skapad");
				oos.flush();
				System.out.println("fil rquest skickad");
				System.out.println(((byte[]) ((Response)ois.readObject()).getResponse()).length );

			}catch(Exception e) {
				e.printStackTrace();
			}


		}

	}
}
