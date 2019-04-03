package server;

import java.util.ArrayList;

/**This entity class holds attributes and methods for Group. 
 * @author Zacharias
 */
public class Group {
	private ArrayList<String> fileLog = new ArrayList<String>();
	private ArrayList<User> groupMembers = new ArrayList<User>();
	private ArrayList<GroupMessage> groupMessages = new ArrayList<GroupMessage>();
	private ArrayList<Event> events = new ArrayList<Event>();

	/**Constructor for groups containing several users.
	 * @param groupMembers ArrayList that holds the users in the group.
	 * @param groupMessages ArrayList that holds messages for the group.
	 * @param fileLog ArrayList of Strings containing which files held by server.
	 * @param events ArrayList containing events planned by the group.
	 */
	public Group(ArrayList<User> groupMembers, ArrayList<GroupMessage> groupMessages, ArrayList<String> fileLog, ArrayList<Event> events) {
		this.groupMembers = groupMembers;
		this.groupMessages = groupMessages;
		this.fileLog = fileLog;
		this.events = events;
	}
	
	
	public ArrayList<User> getGroupMembers() {
		return groupMembers;
	}
	
	
	public ArrayList<GroupMessage> getGroupMessages() {
		return groupMessages;
	}
	
	
	public ArrayList<String> getFileLog() {
		return fileLog;
	}

	
	public ArrayList<Event> getEvents() {
		return events;
	}
	
	
	public void addMember(User user) {
		groupMembers.add(user);
	}
}
