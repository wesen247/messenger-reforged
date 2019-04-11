package entity;

import java.io.Serializable;

/**This is an entity class for messages between more than two users.
 * @author Zacharias
 */
public class GroupMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8274923273246040101L;
	private String message;
	private Group receiver;
	private User sender;
	
	/**Constructor for group messages.
	 * @param message String sent to other users.
	 * @param sender User who sent the message.
	 * @param receiver Group containing users that receives the message.
	 */
	public GroupMessage(String message, User sender, Group receiver) {
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
	 * @return User Author of message.
	 */
	public User getSender() {
		return sender;
	}

	/**
	 * @return Group with receivers of the message.
	 */
	public Group getReceiver() {
		return receiver;
	}
}

