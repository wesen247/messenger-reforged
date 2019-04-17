package application;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CreateUserController implements Initializable {

	private Main main;
	private ClientController client;

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
		client = new ClientController(username, password, this, image);
	}

	public void btnCancel() {
		try {
			
			main.showLogin();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void btnUploadImage() {
		try {
			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			j.showOpenDialog(new JFrame());
			this.image = ImageIO.read(j.getSelectedFile());

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
