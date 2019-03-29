package application;

public class PrivateMessage {
	private String message;
	private User receiver;
	private User sender;

	public PrivateMessage(String message, User receiver, User sender) {
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
	
	
	public User getReceiver() {
		return receiver;
	}

}
