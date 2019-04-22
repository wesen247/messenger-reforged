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

public class CreateGroupController implements Initializable {
	private Main main;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnChooseImage;
	@FXML
	private Button btnCreateGroups;
	@FXML
	private TextField textFieldGroupName;
	
	private BufferedImage image;

	public void initialize(URL location, ResourceBundle resource) {

	}

	public void createGroup() {
		ClientController.getClient().createNewGroup(textFieldGroupName.getText());
		try {
			main.showMainMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cancel() {
		try {
			main.showMainMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void chooseImage() {
		try {

			JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			j.showOpenDialog(new JFrame());
			this.image = ImageIO.read(j.getSelectedFile());

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

