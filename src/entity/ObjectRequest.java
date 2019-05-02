package entity;

import java.io.Serializable;

/**
 * This class represents a object-request that can be sent to the server.
 * 
 * @author Amir,André,Ruben
 *
 */
public class ObjectRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4565151383584860847L;
	private User user;
	private String type;
	private String request;

	/**
	 * Constructs a ObjectRequest-object with a given type-name,request-name and a
	 * user which is requesting.
	 * 
	 * @param type    Name of type of the request.
	 * @param request Name of the request.
	 * @param user    The user that is requesting.
	 */
	public ObjectRequest(String type, String request, User user) {
		this.type = type;
		this.request = request;
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public String getRequest() {
		return request;
	}

	public User getUser() {
		return user;
	}
}
