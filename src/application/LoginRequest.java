package server;

/**
 * This class represents a LoginRequest that can be sent to a server for access.
 * 
 * @author Amir,André, Ruben
 */

public class LoginRequest {

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
