package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import entity.*;

/**This class is used to save and load the
 * ConcurrentHashMap's used by the program.
 * @author zw
 *
 */
public class HashMapHandler {
		private ConcurrentHashMap<String,String> passwordHashMap = new ConcurrentHashMap<String,String>();
		private ConcurrentHashMap<String,User> allUsers = new ConcurrentHashMap<String,User>();
		private ConcurrentHashMap<String,Group> groups = new ConcurrentHashMap<String,Group>();
		private ConcurrentHashMap<String,byte[]> files = new ConcurrentHashMap<String,byte[]>();

		
		
		/**Attempts to load allUsers from file.
		 * @return Returns ConcurrentHashMap allUsers.
		 */
		@SuppressWarnings("unchecked")
		public ConcurrentHashMap<String,User> loadAllUsers() {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Messenger-reforged\\reforged-users.txt"));
				allUsers = (ConcurrentHashMap<String,User>) ois.readObject();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("All users was not loaded succesfully: " + e);
			}
			return allUsers;
		}
		
		/**Receives a ConcurrentHashMap as argument 
		 * and attempts to save it as a local file.
		 * @param allUsers the CHM to be saved.
		 */
		public void saveAllUsers(ConcurrentHashMap<String,User> allUsers) {		
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-users.txt"));
				oos.writeObject(allUsers);
				oos.flush();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("All users was not saved succesfully: " + e);
			}
		}
		
		
		
		/**Attempts to load passwordHashMap from file.
		 * @return Returns ConcurrentHashMap passwordHashMap.
		 */
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
		
		/**Receives a ConcurrentHashMap as argument
		 * and attempts to save it as a local file.
		 * @param passwordHashMap the CHM to be saved.
		 */
		public void savePasswordHashMap(ConcurrentHashMap<String,String> passwordHashMap) {	
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-pw.txt"));
				oos.writeObject(passwordHashMap);
				oos.flush();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		/**Attempts to load groups from file.
		 * @return ConcurrentHashMap groups.
		 */
		@SuppressWarnings("unchecked")
		public ConcurrentHashMap<String,Group> loadGroups() {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Messenger-reforged\\reforged-grouplist.txt"));
				groups = (ConcurrentHashMap<String,Group>) ois.readObject();
				ois.close();
			} catch (Exception e) {
				System.err.println("Groups was not loaded succesfully: " + e);
			}
			return groups;
		}

		/**Receives a ConcurrentHashMap as argument
		 * and attempts to save it as a local file.
		 * @param groups the CHM to be saved.
		 */
		public void saveGroups(ConcurrentHashMap<String,Group> groups) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-grouplist.txt"));
				oos.writeObject(groups);
				oos.flush();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Groups was not saved succesfully: " + e);
			}
		}
		
		/**Attempts to load groups from file.
		 * @return ConcurrentHashMap files.
		 */
		@SuppressWarnings("unchecked")
		public ConcurrentHashMap<String,byte[]> loadFiles() {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Messenger-reforged\\reforged-files.txt"));
				files = (ConcurrentHashMap<String,byte[]>) ois.readObject();
				ois.close();
			} catch (Exception e) {
				System.err.println("Files was not loaded succesfully: " + e);
			}
			return files;
		}

		/**Receives a ConcurrentHashMap as argument
		 * and attempts to save it as a local file.
		 * @param files the CHM to be saved.
		 */
		public void saveFiles(ConcurrentHashMap<String,byte[]> files) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Messenger-reforged\\reforged-files.txt"));
				oos.writeObject(files);
				oos.flush();
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Files was not saved succesfully: " + e);
			}
		}
		
		
		public static void main(String args[]) {
			HashMapHandler hmh = new HashMapHandler();
		}
}
