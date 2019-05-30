package application;

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

/**
 * Handles the createGroup scene
 * @author Ruben, Amir
 *
 */
public class CreateGroupController {
	@FXML private Button btnCancel;
	@FXML private Button btnChooseImage;
	@FXML private Button btnCreateGroups;
	@FXML private TextField textFieldGroupName;

	/**
	 * Creates a new group
	 * @author Ruben, Amir
	 */
	public void createGroup() {
		ClientController.getClient().createNewGroup(textFieldGroupName.getText());
		try {
			Main.showMainMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the stage
	 * @author Ruben, Amir
	 */
	public void cancel() {
		try {
			Main.showMainMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

