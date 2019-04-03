package application;

/**
 * Entity class for group messages.
 * 
 * @author Zacharias
 */
public class GroupMessage {
	private String message;
	private Group receiver;
	private User sender;
	
	public GroupMessage(String message, User sender, Group receiver) {
		this.message = message;
		this.receiver = receiver;
		this.sender = sender;
	}
	
	
	public String getMessage() {
		return message;
	}
	
	
	public User getSender() {
		return sender;
	}

	
	public Group getReceiver() {
		return receiver;
	}
}
