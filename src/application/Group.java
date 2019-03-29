package application;

import java.util.ArrayList;

public class Group {
	private ArrayList<String> fileLog = new ArrayList<String>();
	private ArrayList<User> groupMembers = new ArrayList<User>();
	private ArrayList<GroupMessage> groupMessages = new ArrayList<GroupMessage>();
	//private ArrayList<Event> eventObjects = new ArrayList<Event>();

	public Group(ArrayList<User> groupMembers, ArrayList<GroupMessage> groupMessages, ArrayList<String> fileLog) {
		this.groupMembers = groupMembers;
		this.groupMessages = groupMessages;
		this.fileLog = fileLog;
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

//	public ArrayList<Event> getEventObjects() {
//		return eventObjects;
//	}
}
