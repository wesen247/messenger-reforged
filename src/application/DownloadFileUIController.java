package application;

import java.net.URL;
import java.util.ResourceBundle;

import entity.Group;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * Handles the fileStage
 * @author Amir
 */
public class DownloadFileUIController implements Initializable {
	@FXML private Button btnDownload;
	@FXML private Button btnCancel;
	@FXML private ListView<String> listViewFiles = new ListView<String>();
	private ObservableList<String> filesList = FXCollections.observableArrayList();

	/**
	 * Initializes the controller
	 * @author Amir
	 */
	public void initialize(URL location, ResourceBundle resource) {
		update();
		listViewFiles.setItems(this.filesList);
	}

	/**
	 * Downloads a file from the group
	 * @author Amir
	 */
	public void download() {
		String file = listViewFiles.getSelectionModel().getSelectedItem();
		ClientController.getClient().setRequestedFile(file);
		ClientController.getClient().download(file);
	}

	/**
	 * Closes the stage
	 * @author Amir
	 */
	public void cancel() {
		Main.getFileStage().close();
	}

	/**
	 * Updates the list of files from downloading
	 * @author Amir
	 */
	public void update() {
		Data.getData();
		Group group = Data.getGroups().get(ClientController.getClient().getGroup().getGroupName());
		for (int i = 0; i < group.getFileLog().size(); i++) {
			filesList.add(group.getFileLog().get(i));
		}
	}
}
