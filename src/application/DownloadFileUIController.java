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

public class DownloadFileUIController implements Initializable {

	@FXML
	private Button btnDownload;
	@FXML
	private Button btnCancel;
	@FXML
	private ListView<String> listViewFiles = new ListView<String>();

	private ObservableList<String> filesList = FXCollections.observableArrayList();

	public void initialize(URL arg0, ResourceBundle arg1) {

		update();
		listViewFiles.setItems(this.filesList);
	}

	public void download() {
		String file = listViewFiles.getSelectionModel().getSelectedItem();
		ClientController.getClient().setRequestedFile(file);
		ClientController.getClient().download(file);

	}

	public void cancel() {
		Main.getFileStage().close();
	}

	public void update() {

		Group group = Data.getData().getGroups().get(ClientController.getClient().getGroup().getGroupName());
		System.out.println(group.getFileLog().size());
		for (int i = 0; i < group.getFileLog().size(); i++) {
			System.out.println("går in i forsatsen" + group.getFileLog().get(i));
			filesList.add(group.getFileLog().get(i));

		}

	}
}
