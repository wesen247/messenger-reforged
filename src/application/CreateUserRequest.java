package application;
/**
 * Entity, used to create new user
 * @author André Ruben Amir
 *
 */
public class CreateUserRequest {
	private String password;
	private String name;

	/**
	 * @author André Ruben Amir
	 * @param name
	 * @param password
	 */
	public CreateUserRequest(String name, String password) {
		this.name = name;
		this.password = password;
	}
	/**
	 * @author André Ruben Amir
	 * @return Name of user
	 */
	public String getName() {
		return name;
	}
	/**
	 * @author André Ruben Amir
	 * @return User password
	 */
	public String getPassword() {
		return password;
	}
}

