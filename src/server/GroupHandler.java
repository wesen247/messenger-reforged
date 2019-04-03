package server;

import java.util.concurrent.ConcurrentHashMap;

public class GroupHandler {
	private ConcurrentHashMap<String,Group> groups;
	private ServerController controller;
	public GroupHandler(boolean useBackup, ServerController controller) {
		this.controller = controller;
		if(!useBackup) {
			groups = new ConcurrentHashMap<String,Group>();
		}else {
			//Läsa in backup
		}
	}

	public Group getGroup(String group) {
		return groups.get(group);
	}
	public void addGroup(Group group) {
		if(!groups.containsKey(group.getGroupName())) {
			groups.put(group.getGroupName(), group);
			groupUpdate(group);
		}else {
			controller.send(group.getGroupMembers().get(0), new Response("createUserFailed", "Name already taken"));
		}
	}
	public void groupUpdate(Group group) {
		for(int i = 0; i < group.getGroupMembers().size(); i++) {
			controller.send(group.getGroupMembers().get(i), group);
		}
	}
	public void addMember(Group group, User user) {
		groups.get(group.getGroupName()).addMember(user);		
		groupUpdate(group);
	}
	public void addEvent(String groupName, Event event) {
		if(groups.containsKey(groupName)) {
			groups.get(groupName).getEvents().add(event);
		}
	}
	public void newMessage(GroupMessage message) {
		groups.get(message.getReceiver().getGroupName()).getGroupMessages().add(message);
		Group group = groups.get(message.getReceiver().getGroupName());
		for(int i = 0; i<group.getGroupMembers().size();i++) {
			controller.send(group.getGroupMembers().get(i), message);
		}
	}
}
