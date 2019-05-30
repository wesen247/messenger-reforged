package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

/**
 * Handles the groupChatStage
 * @author Ruben, Amir, Andre
 *
 */
public class GroupMessageController implements Initializable {
	@FXML private Button btnSend;
	@FXML private Button btnAddGroupMember;
	@FXML private TextField textFieldMessage;
	@FXML private TextArea textAreaIncomingMessages;
	@FXML private ListView<String> listViewMembers = new ListView<String>();
	@FXML private Button btnAddEvent;
	@FXML private Button btnUploadFile;
	@FXML private Text textGroupName;
	@FXML private Button btnShowEvents;
	@FXML private Button btnShowFiles;
	private static GroupMessageController controller;
	private ObservableList<String> membersList = FXCollections.observableArrayList();

	/**
	 * 
	 * @return Returns the controller
	 * @author Ruben, Amir
	 */
	public static GroupMessageController getGroupMessageController() {
		return controller;
	}

	/**
	 * Initializes the class
	 * @author Ruben, Amir
	 */
	public void initialize(URL location, ResourceBundle resource) {
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

	/**
	 * Opens the eventStage
	 * @author Ruben, Amir
	 */
	public void addEvent() {
		try {
			Main.showCreateEvent();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends a message to the group
	 * @author Ruben, Amir
	 */
	public void send() {
		ClientController.getClient().createGroupMessage(textFieldMessage.getText());
		textFieldMessage.clear();
	}

	/**
	 * Opens the addGroupMemberStage
	 * @author Ruben, Amir
	 */
	public void addGroupMember() {
		try {
			Main.showAddNewGroupMember();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates the chat history and updates the member list
	 * @author Ruben, Amir
	 */
	public void update() {
		Platform.runLater(new Runnable() {
			public void run() {
				try {
					textAreaIncomingMessages.setText("");
					Data data = Data.getData();
					ClientController client = ClientController.getClient();
					
					for (int i = 0; i < data.getGroupMessage().get(client.getGroup().getGroupName()).size(); i++) {
						textAreaIncomingMessages.appendText(data.getGroupMessage()
								.get(client.getGroup().getGroupName()).get(i).getSender()
								+ ": " + data.getGroupMessage().get(client.getGroup().getGroupName()).get(i)
								.getMessage());
						textAreaIncomingMessages.appendText("\n");
					}
					ArrayList<User> members = data.getGroups().get(client.getGroup().getGroupName()).getGroupMembers();
					membersList.clear();
					
					for (int i = 0; i < members.size(); i++) {
						membersList.add(members.get(i).getName());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Uploads a file to the current group
	 * @author Ruben, Amir, Andre
	 */
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

	/**
	 * Opens the fileStage
	 * @author Ruben, Amir
	 */
	public void showFiles() {
		try {
			Main.showFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens the eventStage
	 * @author Ruben, Amir
	 */
	public void showEvent() {
		try {
			Main.showEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
