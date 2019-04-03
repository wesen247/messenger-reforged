package server;

/**This is an entity class for messages between two users.
 * @author Zacharias
 */
public class PrivateMessage {
	private String message;
	private User receiver;
	private User sender;

	
	/**Constructor for private messages.
	 * @param message String sent to another user.
	 * @param sender User who sent the message.
	 * @param receiver User who receives the message.
	 */
	public PrivateMessage(String message, User sender, User receiver) {
		this.message = message;
		this.receiver = receiver;
		this.sender = sender;
	}
	
	
	/**
	 * @return String message.
	 */
	public String getMessage() {
		return message;
	}
	
	
	/**
	 * @return User: Author of message.
	 */
	public User getSender() {
		return sender;
	}
	
	
	/**
	 * @return User that receives the message.
	 */
	public User getReceiver() {
		return receiver;
	}
}
