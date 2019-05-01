package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

	public void initialize(URL arg0, ResourceBundle arg1) {
		Data.getData().addGroupListener(this);
		update();
	}

	public void send() {
		ClientController.getClient().createGroupMessage(textFieldMessage.getText());
	}

	public void addGroupMember() {

	}

	public void update() {

		System.out.println(ClientController.getClient().getGroup());

		try {

			textAreaIncomingMessages.setText(" ");
			
			for (int i = 0; i < Data.getData().getGroupMessage()
					.get(ClientController.getClient().getGroup().getGroupName()).size(); i++) {
				textAreaIncomingMessages.appendText(Data.getData().getGroupMessage()
						.get(ClientController.getClient().getGroup().getGroupName()).get(i).getMessage());
				textAreaIncomingMessages.appendText("\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
