package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import entity.User;
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

	private ObservableList<String> membersList = FXCollections.observableArrayList();

	public void initialize(URL arg0, ResourceBundle arg1) {

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
				Main.showCalendar();
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
		try {
			textAreaIncomingMessages.setText("");
			for (int i = 0; i < Data.getData().getGroupMessage()
					.get(ClientController.getClient().getGroup().getGroupName()).size(); i++) {
				textAreaIncomingMessages.appendText(Data.getData().getGroupMessage()
						.get(ClientController.getClient().getGroup().getGroupName()).get(i).getSender() + ": "
						+ Data.getData().getGroupMessage().get(ClientController.getClient().getGroup().getGroupName())
								.get(i).getMessage());
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


}
