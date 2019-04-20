package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class ArrayListHandler implements Serializable {
	private ArrayList<User> friendList = new ArrayList<User>();
	private ArrayList<Group> groups = new ArrayList<Group>();
	private ArrayList<GroupMessage> groupMessages = new ArrayList<GroupMessage>();
	private ArrayList<String> fileLog = new ArrayList<String>();
	private ArrayList<Event> events = new ArrayList<Event>();
	private ArrayList<String> memberOfGroups;
	private ArrayList<User> onlineUsers;
	private ArrayList<User> allUsers;
	
	//Friendlist
	@SuppressWarnings("unchecked")
	public ArrayList<User> loadFriendList() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("reforged-friendlist.txt"));
			friendList = (ArrayList<User>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Friendlist was not loaded succesfully: " + e);
		}
		return friendList;
	}
	
	public void saveFriendList() {
		File directory = new File("C:\\Messenger-reforged");
		if(!directory.exists()) {
			try {
				directory.mkdir();
			}catch(SecurityException e) {
				e.printStackTrace();
			}
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-friendlist.txt"));
			oos.writeObject(friendList);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Friendlist was not saved succesfully: " + e);
		}
	}
	
	//Groups
	@SuppressWarnings("unchecked")
	public ArrayList<Group> loadGroups() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Messenger-reforged\\reforged-grouplist.txt"));
			groups = (ArrayList<Group>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.err.println("Grouplist was not loaded succesfully: " + e);
		}
		return groups;
	}
	
	public void saveGroups() {
		File directory = new File("C:\\Messenger-reforged");
		if(!directory.exists()) {
			try {
				directory.mkdir();
			}catch(SecurityException e) {
				e.printStackTrace();
			}
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-grouplist.txt"));
			oos.writeObject(friendList);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Grouplist was not saved succesfully: " + e);
		}
	}
	
	//GroupMessages
	@SuppressWarnings("unchecked")
	public ArrayList<GroupMessage> loadGroupMessages() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Messenger-reforged\\reforged-grouplist.txt"));
			groupMessages = (ArrayList<GroupMessage>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.err.println("Grouplist was not loaded succesfully: " + e);
		}
		return groupMessages;
	}
	
	public void saveGroupMessages() {
		File directory = new File("C:\\Messenger-reforged");
		if(!directory.exists()) {
			try {
				directory.mkdir();
			}catch(SecurityException e) {
				e.printStackTrace();
			}
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-grouplist.txt"));
			oos.writeObject(groupMessages);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Grouplist was not saved succesfully: " + e);
		}
	}
	
	//Filelog
	@SuppressWarnings("unchecked")
	public ArrayList<String> loadFileLog() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Messenger-reforged\\reforged-fileLog.txt"));
			 fileLog = (ArrayList<String>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.err.println("Filelog was not loaded succesfully: " + e);
		}
		return fileLog;
	}
	
	public void saveFileLog() {
		File directory = new File("C:\\Messenger-reforged");
		if(!directory.exists()) {
			try {
				directory.mkdir();
			}catch(SecurityException e) {
				e.printStackTrace();
			}
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-fileLog.txt"));
			oos.writeObject(fileLog);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Filelog was not saved succesfully: " + e);
		}
	}
	
	//Events
	@SuppressWarnings("unchecked")
	public ArrayList<Event> loadEvents() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Messenger-reforged\\reforged-events.txt"));
			 events = (ArrayList<Event>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.err.println("Events was not loaded succesfully: " + e);
		}
		return events;
	}
	
	public void saveEvents() {
		File directory = new File("C:\\Messenger-reforged");
		if(!directory.exists()) {
			try {
				directory.mkdir();
			}catch(SecurityException e) {
				e.printStackTrace();
			}
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-events.txt"));
			oos.writeObject(events);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Events was not saved succesfully: " + e);
		}
	}
	
	//MemberOfGroups
	@SuppressWarnings("unchecked")
	public ArrayList<String> loadMemberOfGroups() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Messenger-reforged\\reforged-memberOfGroups.txt"));
			 memberOfGroups = (ArrayList<String>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.err.println("Member of groups was not loaded succesfully: " + e);
		}
		return memberOfGroups;
	}
	
	public void saveMemberOfGroups() {
		File directory = new File("C:\\Messenger-reforged");
		if(!directory.exists()) {
			try {
				directory.mkdir();
			}catch(SecurityException e) {
				e.printStackTrace();
			}
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-memberOfGroups.txt"));
			oos.writeObject(memberOfGroups);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Member of groups was not saved succesfully: " + e);
		}
	}
	
	//OnlineUsers
	@SuppressWarnings("unchecked")
	public ArrayList<User> loadOnlineUsers() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Messenger-reforged\\reforged-onlineUsers.txt"));
			 onlineUsers = (ArrayList<User>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.err.println("Online users was not loaded succesfully: " + e);
		}
		return onlineUsers;
	}
	
	public void saveOnlineUsers() {
		File directory = new File("C:\\Messenger-reforged");
		if(!directory.exists()) {
			try {
				directory.mkdir();
			}catch(SecurityException e) {
				e.printStackTrace();
			}
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-onlineUsers.txt"));
			oos.writeObject(onlineUsers);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Online users was not saved succesfully: " + e);
		}
	}
	
	//AllUsers
	@SuppressWarnings("unchecked")
	public ArrayList<User> loadAllUsers() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Messenger-reforged\\reforged-allUsers.txt"));
			 allUsers = (ArrayList<User>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.err.println("All users was not loaded succesfully: " + e);
		}
		return allUsers;
	}
	
	public void saveAllUsers() {
		File directory = new File("C:\\Messenger-reforged");
		if(!directory.exists()) {
			try {
				directory.mkdir();
			}catch(SecurityException e) {
				e.printStackTrace();
			}
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-allUsers.txt"));
			oos.writeObject(allUsers);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("All users was not saved succesfully: " + e);
		}
	}
	
	
	public static void main(String args[]) {
		ArrayListHandler alh = new ArrayListHandler();
		alh.saveFriendList();
	}
}
