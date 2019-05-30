package application;

import javafx.scene.control.Button;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Handles the loginStage
 * @author Ruben, Amir
 *
 */
public class LoginController {
	@FXML private TextField logInUsernameField;
	@FXML private TextField logInPasswordField;
	@FXML private Button btnLogIn;
	@FXML private Button btnCreateUser;
	private String username;
	private String password;
	
	/**
	 * Show a message dialog if you failed to login
	 * @author Ruben, Amir
	 * @param response
	 */
	public void loginFailed(String response) {
		JOptionPane.showMessageDialog(null, response);
	}
	
	/**
	 * Login requst
	 * @author Ruben, Amir
	 */
	public void buttonLogin() {
		username = logInUsernameField.getText();
		password = logInPasswordField.getText(); 
		new ClientController(username, password, this);
	}
	
	/**
	 * Changes to the createUser stage
	 * @author Ruben, Amir
	 */
	public void createNewUser() {
		try {
			Main.showCreateUser();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}

