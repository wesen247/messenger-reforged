package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import entity.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ChatWindowGroupMessageController implements Initializable {

	@FXML
	private Button btnSend;
	@FXML
	private Button btnAddGroupMember;
	@FXML
	private TextField textFieldMessage;
	@FXML
	private TextArea textAreaIncomingMessages;
	@FXML
	private ListView<String> listViewMembers = new ListView<String>();
	@FXML
	private Button btnAddEvent;
	@FXML
	private Button btnUploadFile;
	@FXML
	private Text textGroupName;
	@FXML
	private Button btnShowEvents;
	@FXML
	private Button btnShowFiles;
	private static ChatWindowGroupMessageController controller;

	private ObservableList<String> membersList = FXCollections.observableArrayList();

	public static ChatWindowGroupMessageController getGroupMessageController() {
		return controller;
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		controller = this;
		textAreaIncomingMessages.setEditable(false);
		Data.getData().addGroupListener(this);
		update();
		listViewMembers.setItems(membersList);

		textFieldMessage.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					send();
				}
			}
		});

		textGroupName.setText(ClientController.getClient().getGroup().getGroupName());
	}

	public void addEvent() {

		System.out.println("tja");
		try {
			Main.showCreateEvent();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void send() {
		ClientController.getClient().createGroupMessage(textFieldMessage.getText());
		textFieldMessage.clear();
	}

	public void addGroupMember() {
		try {
			Main.showAddNewGroupMember();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void update() {
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					textAreaIncomingMessages.setText("");
					for (int i = 0; i < Data.getData().getGroupMessage()
							.get(ClientController.getClient().getGroup().getGroupName()).size(); i++) {
						textAreaIncomingMessages.appendText(Data.getData().getGroupMessage()
								.get(ClientController.getClient().getGroup().getGroupName()).get(i).getSender()
								+ ": "
								+ Data.getData().getGroupMessage()
										.get(ClientController.getClient().getGroup().getGroupName()).get(i)
										.getMessage());
						textAreaIncomingMessages.appendText("\n");
					}
					ArrayList<User> members = Data.getData().getGroups()
							.get(ClientController.getClient().getGroup().getGroupName()).getGroupMembers();

					membersList.clear();

					for (int i = 0; i < members.size(); i++) {

						membersList.add(members.get(i).getName());

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

	}

	public void uploadFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select a file");

		Stage stage = (Stage) Main.getPrimaryStage().getScene().getWindow();

		File file = fileChooser.showOpenDialog(stage);

		try {

			byte[] files = Files.readAllBytes(file.toPath());
			ClientController.getClient().sendFile(file.getName(), files);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
public void showFiles() {
	try {
		Main.showFiles();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	public void showEvent() {
		try {
			Main.showEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
