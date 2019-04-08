package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class TestClassForServer {
	public static void main(String args[]) {
		new ServerController(false, 100);//startar server
		new TestClassForServer();

	}
	public TestClassForServer() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//avkommentera det som ska testas
		new AddEventInGroup();
//		new TestConnection();
//		new TestLogin();
//  	new LoginMany();
//		new SendPm();
//		new AddGroupMemberRequest();
//		new SendGroupMessage();
//		new CreateGroup();
	}


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
	public class TestLogin {
		public Socket socket;
		public TestLogin() {
			try {
				socket = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2"));//Ändra skapande av användare
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
	public class LoginMany{
		public Socket socket;
		public LoginMany() {
			try {
				Socket socket1 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket1.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2"));//Ändra skapande av användare
				oos.flush();


				Thread.sleep(1000);

				Socket socket2 = new Socket(InetAddress.getLocalHost(), 5434);
				oos = new ObjectOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
				oos.flush();
				ois = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
				oos.writeObject(new CreateUserRequest("2","2"));//Ändra skapande av användare
				oos.flush();


				Thread.sleep(1000);

				Socket 	socket3 = new Socket(InetAddress.getLocalHost(), 5434);
				oos = new ObjectOutputStream(new BufferedOutputStream(socket3.getOutputStream()));
				oos.flush();
				ois = new ObjectInputStream(new BufferedInputStream(socket3.getInputStream()));
				oos.writeObject(new CreateUserRequest("3","2"));//Ändra skapande av användare
				oos.flush();


				Thread.sleep(1000);

				Socket socket4 = new Socket(InetAddress.getLocalHost(), 5434);
				oos = new ObjectOutputStream(new BufferedOutputStream(socket4.getOutputStream()));
				oos.flush();
				ois = new ObjectInputStream(new BufferedInputStream(socket4.getInputStream()));
				oos.writeObject(new CreateUserRequest("4","2"));//Ändra skapande av användare
				oos.flush();


				Thread.sleep(1000);

				Socket socket5 = new Socket(InetAddress.getLocalHost(), 5434);
				oos = new ObjectOutputStream(new BufferedOutputStream(socket5.getOutputStream()));
				oos.flush();
				ois = new ObjectInputStream(new BufferedInputStream(socket5.getInputStream()));
				oos.writeObject(new CreateUserRequest("5","2"));//Ändra skapande av användare
				oos.flush();


				Thread.sleep(1000);

				Socket socket6 = new Socket(InetAddress.getLocalHost(), 5434);
				oos = new ObjectOutputStream(new BufferedOutputStream(socket6.getOutputStream()));
				oos.flush();
				ois = new ObjectInputStream(new BufferedInputStream(socket6.getInputStream()));
				oos.writeObject(new CreateUserRequest("6","2"));//Ändra skapande av användare
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
				UserUpdate s3 = (UserUpdate) ois.readObject();
				System.out.println(s3.getUsers());

			} catch (IOException | InterruptedException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

	}
	public class SendPm{

		public SendPm(){
			try {
				//Skapar användare
				Socket socket1 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket1.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2"));//Ändra skapande av användare
				oos.flush();
				Thread.sleep(1000);

				Socket socket2 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos1 = new ObjectOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
				oos1.flush();
				ObjectInputStream ois1 = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
				oos1.writeObject(new CreateUserRequest("2","2"));//Ändra skapande av användare
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

				Thread.sleep(1000);
			}catch(Exception e) {

			}

		}

	}
	public class AddEventInGroup{
		public AddEventInGroup() {

			try {
				//Skapar användare
				Socket socket1 = new Socket(InetAddress.getLocalHost(), 5434);

				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream((socket1.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2"));//Ändra skapande av användare
				oos.flush();
				Thread.sleep(1000);

				Socket socket2 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos1 = new ObjectOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
				oos1.flush();
				ObjectInputStream ois1 = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
				oos1.writeObject(new CreateUserRequest("2","2"));//Ändra skapande av användare
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
	public class AddGroupMemberRequest{

		public AddGroupMemberRequest() {
			try {
				//Skapar användare
				Socket socket1 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket1.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2"));//Ändra skapande av användare
				oos.flush();
				Thread.sleep(1000);

				Socket socket2 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos1 = new ObjectOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
				oos1.flush();
				ObjectInputStream ois1 = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
				oos1.writeObject(new CreateUserRequest("2","2"));//Ändra skapande av användare
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
	public class SendGroupMessage{
		public SendGroupMessage() {
			try {
				//Skapar användare
				Socket socket1 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket1.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2"));//Ändra skapande av användare
				oos.flush();
				Thread.sleep(1000);

				Socket socket2 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos1 = new ObjectOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
				oos1.flush();
				ObjectInputStream ois1 = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
				oos1.writeObject(new CreateUserRequest("2","2"));//Ändra skapande av användare
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
	public class CreateGroup {
		public CreateGroup() {
			try {
				//Skapar användare
				Socket socket1 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				oos.flush();
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket1.getInputStream()));
				oos.writeObject(new CreateUserRequest("1","2"));//Ändra skapande av användare
				oos.flush();
				Thread.sleep(1000);

				Socket socket2 = new Socket(InetAddress.getLocalHost(), 5434);
				ObjectOutputStream oos1 = new ObjectOutputStream(new BufferedOutputStream(socket2.getOutputStream()));
				oos1.flush();
				ObjectInputStream ois1 = new ObjectInputStream(new BufferedInputStream(socket2.getInputStream()));
				oos1.writeObject(new CreateUserRequest("2","2"));//Ändra skapande av användare
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
}
