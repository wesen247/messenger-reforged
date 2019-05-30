package application;

import javafx.scene.control.Button;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML private TextField logInUsernameField;
	@FXML private TextField logInPasswordField;
	@FXML private Button btnLogIn;
	@FXML private Button btnCreateUser;
	private String username;
	private String password;
	
	public void loginFailed(String response) {
		JOptionPane.showMessageDialog(null, response);
	}
	
	public void buttonLogin() {
		username = logInUsernameField.getText();
		password = logInPasswordField.getText(); 
		new ClientController(username, password, this);
	}
	
	public void createNewUser() {
		try {
			Main.showCreateUser();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}

