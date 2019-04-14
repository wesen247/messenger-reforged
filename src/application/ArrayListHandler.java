package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ArrayListHandler {
	private ArrayList<User> friendList = new ArrayList<User>();
	private ArrayList<Group> groupList = new ArrayList<Group>();
	
	
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
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("reforged-friendlist.txt"));
			oos.writeObject(friendList);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Friendlist was not saved succesfully: " + e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public ArrayList<Group> loadGroupList() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("reforged-grouplist.txt"));
			groupList = (ArrayList<Group>) ois.readObject();
			ois.close();
		} catch (Exception e) {
			System.err.println("Grouplist was not loaded succesfully: " + e);
		}
		return groupList;
	}
	
	public void saveGroupList() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("reforged-grouplist.txt"));
			oos.writeObject(friendList);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Grouplist was not saved succesfully: " + e);
		}
	}
	
	
	public static void main(String args[]) {
		ArrayListHandler alh = new ArrayListHandler();
		alh.loadFriendList();
	}

}
