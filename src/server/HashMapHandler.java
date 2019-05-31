package server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentHashMap;

import entity.Group;
import entity.User;
import logger.Logg;

/**This class is used to save and load the
 * ConcurrentHashMap's used by the program.
 * @author Zacharias
 *
 */
public class HashMapHandler {
		private ConcurrentHashMap<String,String> passwordHashMap = new ConcurrentHashMap<String,String>();
		private ConcurrentHashMap<String,User> allUsers = new ConcurrentHashMap<String,User>();
		private ConcurrentHashMap<String,Group> groups = new ConcurrentHashMap<String,Group>();
		private ConcurrentHashMap<String,byte[]> files = new ConcurrentHashMap<String,byte[]>();
		private Logg logg;
		private String username = System.getProperty("user.name");
		
		
		/**Attempts to load allUsers from file.
		 * @return Returns ConcurrentHashMap allUsers.
		 */
		@SuppressWarnings("unchecked")
		public ConcurrentHashMap<String,User> loadAllUsers() {
			
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\" + username + "\\Messenger-Reforged\\reforged-users.txt"));
				allUsers = (ConcurrentHashMap<String,User>) ois.readObject();
				ois.close();
			} catch (Exception e) {
				logg.writeToLog("All users was not loaded succesfully: " + e);
			}
			return allUsers;
		}
		
		/**Receives a ConcurrentHashMap as argument 
		 * and attempts to save it as a local file.
		 * @param allUsers the CHM to be saved.
		 */
		public void saveAllUsers(ConcurrentHashMap<String,User> allUsers) {		
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\users\\" + username + "\\Messenger-Reforged\\reforged-users.txt"));
				oos.writeObject(allUsers);
				oos.flush();
				oos.close();
			} catch (IOException e) {
				logg.writeToLog("All users was not saved succesfully: " + e);
			}
		}
		
		
		
		/**Attempts to load passwordHashMap from file.
		 * @return Returns ConcurrentHashMap passwordHashMap.
		 */
		@SuppressWarnings("unchecked")
		public ConcurrentHashMap<String,String> loadPasswordHashMap() {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\" + username + "\\Messenger-Reforged\\reforged-pw.txt"));
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
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\" + username + "\\Messenger-Reforged\\reforged-pw.txt"));
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
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\" + username + "\\Messenger-Reforged\\reforged-grouplist.txt"));
				groups = (ConcurrentHashMap<String,Group>) ois.readObject();
				ois.close();
			} catch (Exception e) {
				logg.writeToLog("Groups was not loaded succesfully: " + e);
			}
			return groups;
		}

		/**Receives a ConcurrentHashMap as argument
		 * and attempts to save it as a local file.
		 * @param groups the CHM to be saved.
		 */
		public void saveGroups(ConcurrentHashMap<String,Group> groups) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\" + username + "\\Messenger-Reforged\\reforged-grouplist.txt"));
				oos.writeObject(groups);
				oos.flush();
				oos.close();
			} catch (IOException e) {
				logg.writeToLog("Groups was not saved succesfully: " + e);
			}
		}
		
		/**Attempts to load groups from file.
		 * @return ConcurrentHashMap files.
		 */
		@SuppressWarnings("unchecked")
		public ConcurrentHashMap<String,byte[]> loadFiles() {
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\" + username + "\\Messenger-Reforged\\reforged-files.txt"));
				files = (ConcurrentHashMap<String,byte[]>) ois.readObject();
				ois.close();
			} catch (Exception e) {
				logg.writeToLog("Files was not loaded succesfully: " + e);
			}
			return files;
		}

		/**Receives a ConcurrentHashMap as argument
		 * and attempts to save it as a local file.
		 * @param files the CHM to be saved.
		 */
		public void saveFiles(ConcurrentHashMap<String,byte[]> files) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\" + username + "\\Messenger-Reforged\\reforged-files.txt"));
				oos.writeObject(files);
				oos.flush();
				oos.close();
			} catch (IOException e) {
				logg.writeToLog("Files was not saved succesfully: " + e);
			}
		}
}
