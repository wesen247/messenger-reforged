package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Handles the createUser scene
 * @author Ruben, Amir
 *
 */
public class CreateUserController {
	@FXML private Button btnCreate;
	@FXML private Button btnCancel;
	@FXML private Button btnUploadImage;
	@FXML private TextField textFieldUsername;
	@FXML private TextField textFieldPassword;
	private String username;
	private String password;
	private BufferedImage image;

	/**
	 * Creates a new user
	 * @author Ruben, Amir
	 */
	public void btnCreate() {
		username = textFieldUsername.getText();
		password = textFieldPassword.getText();
		new ClientController(username, password, this, image);
	}

	/**
	 * Closes the stage
	 * @author Ruben, Amir
	 */
	public void btnCancel() {
		try {
			Main.showLogin();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Uploads an image thats used for the creation of the user
	 * 	@author Ruben, Amir
	 */
	public void btnUploadImage() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select a picture");
		Stage stage = (Stage) Main.getPrimaryStage().getScene().getWindow();
		File file = fileChooser.showOpenDialog(stage);
		try {
			this.image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
