package server;

import java.io.Serializable;

/**
 * This class represents a LoginRequest that can be sent to a server for access.
 * 
 * @author Amir,André, Ruben
 */

public class LoginRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 839876300401227423L;
	private String name;
	private String password;

	/**
	 * 
	 * @param name     Username
	 * @param password Password
	 */

	public LoginRequest(String name, String password) {
		this.name = name;
		this.password = password;
	}

	/**
	 * 
	 * @return returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return returns the password.
	 */
	public String getPassword() {

		return password;

	}

}
