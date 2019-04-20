package application;

import server.UserClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapHandler {
		private ConcurrentHashMap<String,UserClient> connectedUsers = new ConcurrentHashMap<String,UserClient>();
		private ConcurrentHashMap<String,String> passwordHashMap = new ConcurrentHashMap<String,String>();
		private ConcurrentHashMap<String,User> allUsers = new ConcurrentHashMap<String,User>();

		//AllUsers
		@SuppressWarnings("unchecked")
		public ConcurrentHashMap<String,User> loadAllUsers() {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Messenger-reforged\\reforged-users.txt"));
				allUsers = (ConcurrentHashMap<String,User>) ois.readObject();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Friendlist was not loaded succesfully: " + e);
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
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-users.txt"));
				oos.writeObject(allUsers);
				oos.flush();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Users was not saved succesfully: " + e);
			}
		}
		
		//ConnectedUsers
		@SuppressWarnings("unchecked")
		public ConcurrentHashMap<String,UserClient> loadConnectedUsers() {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Messenger-reforged\\reforged-connectedUsers.txt"));
				connectedUsers = (ConcurrentHashMap<String,UserClient>) ois.readObject();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("Friendlist was not loaded succesfully: " + e);
			}
			return connectedUsers;
		}
		
		public void saveConnectedUsers() {
			File directory = new File("C:\\Messenger-reforged");
			if(!directory.exists()) {
				try {
					directory.mkdir();
				}catch(SecurityException e) {
					e.printStackTrace();
				}
			}
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-connectedUsers.txt"));
				oos.writeObject(connectedUsers);
				oos.flush();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Connected users not saved succesfully: " + e);
			}
		}
		
		//PasswordHashMap
		@SuppressWarnings("unchecked")
		public ConcurrentHashMap<String,String> loadPasswordHashMap() {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Messenger-reforged\\reforged-pw.txt"));
				passwordHashMap = (ConcurrentHashMap<String,String>) ois.readObject();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return passwordHashMap;
		}
		
		public void savePasswordHashMap() {
			File directory = new File("C:\\Messenger-reforged");
			if(!directory.exists()) {
				try {
					directory.mkdir();
				}catch(SecurityException e) {
					e.printStackTrace();
				}
			}
			
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-pw.txt"));
				oos.writeObject(connectedUsers);
				oos.flush();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String args[]) {
			HashMapHandler hmh = new HashMapHandler();
		}
}
