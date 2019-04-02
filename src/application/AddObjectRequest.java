package application;

/**
 * Entity, Used to add various object to the server
 * @author André Ruben Amir
 *
 */
public class AddObjectRequest {
	private User user;
	private String type;
	private Object objectToAdd;
	/**
	 * @author André Ruben Amir
	 * @param type			Type of request
	 * @param objectToAdd	Object to be added
	 * @param user			Sender
	 */
	public AddObjectRequest(String type, Object objectToAdd, User user) {
		this.type = type;
		this.objectToAdd = objectToAdd;
		this.user = user;
	}
	
	public String getType() {
		return type;
	}
	
	public User getUser() {
		return user;
	}
	
	public Object getObjectToAdd() {
		return objectToAdd;
	}
}
