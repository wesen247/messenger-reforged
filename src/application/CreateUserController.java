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

public class CreateUserController implements Initializable {

	@FXML
	private Button btnCreate;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnUploadImage;
	@FXML
	private TextField textFieldUsername;
	@FXML
	private TextField textFieldPassword;
	private String username;
	private String password;
	private BufferedImage image;

	public void initialize(URL location, ResourceBundle resource) {

	}

	public void btnCreate() {
		username = textFieldUsername.getText();
		password = textFieldPassword.getText();
		new ClientController(username, password, this, image);
	}

	public void btnCancel() {
		try {

			Main.showLogin();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
