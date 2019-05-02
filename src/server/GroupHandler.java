package server;

import java.util.concurrent.ConcurrentHashMap;

import entity.*;


public class GroupHandler {
	private ConcurrentHashMap<String,Group> groups;
	private ServerController controller;
	/**
	 * Contructor
	 * @param useBackup True if server should load backup saved locally
	 * @param controller Controller
	 * @author André
	 */
	public GroupHandler(boolean useBackup, ServerController controller) {
		this.controller = controller;
		if(!useBackup) {
			groups = new ConcurrentHashMap<String,Group>();
		}else {
			//Läsa in backup
		}
	}
	/**
	 * Returns the group with the parameter as name
	 * @param group String groupname to get
	 * @return group Returns the group with the name of incoming parameter
	 */
	public Group getGroup(String group) {
		return groups.get(group);
	}
	/**
	 * Adds a group to the server
	 * @param group Group to be added to server
	 * @author André
	 */
	public void addGroup(Group group) {
		if(!groups.containsKey(group.getGroupName())) {
			System.out.println("Group added");
			groups.put(group.getGroupName(), group);
			groupUpdate(group);
		}else {
			System.out.println("Group creation failed");
//			controller.send(group.getGroupMembers().get(0), new Response("createGroupFailed", "Name already taken"));
		}
	}
	/**
	 * Sends updates to all the members of group
	 * @param group Group that should have its members updated
	 * @author André
	 */
	public void groupUpdate(Group group) {
		for(int i = 0; i < group.getGroupMembers().size(); i++) {
			System.out.println("Skickar grupp uppdatering till "+ group.getGroupMembers().get(i).getName() );
			controller.send(group.getGroupMembers().get(i), group);
		}
	}
	/**
	 * adds a user in a group memberlist
	 * @param group Group that the member should be added in
	 * @param user Userobject containing information about the member that is being added
	 * @author André
	 */
	public void addMember(Group group, User user) {
		System.out.println(user.getName() + " Added to "+group.getGroupName());
		groups.get(group.getGroupName()).addMember(user);
		groupUpdate(groups.get(group.getGroupName()));
	}
	/**
	 * Adds a event in the event arraylist
	 * @param groupName Name of the group the event should be added in
	 * @param event The event
	 * @author André
	 */
	public synchronized void addEvent(String groupName, Event event) {
		if(groups.containsKey(groupName)) {
			System.out.println("Added group event to group" + groupName);
			groups.get(groupName).addEvent(event);
			groupUpdate(groups.get(groupName));
		}
	}
	/**
	 * Receives and distrubutes a message to all users in a group
	 * @param message Message that came from an user
	 * @author André
	 */
	public void newMessage(GroupMessage message) {
		groups.get(message.getReceiver().getGroupName()).getGroupMessages().add(message);
		System.out.println("Number of messages in group "+groups.get(message.getReceiver().getGroupName()).getGroupMessages().size());
		Group group = groups.get(message.getReceiver().getGroupName());
		for(int i = 0; i<group.getGroupMembers().size();i++) {
			controller.send(group.getGroupMembers().get(i), message);
		}
	}
}
