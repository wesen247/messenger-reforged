package application;

import entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddNewGroupMemberController {
	@FXML private Button btnAddUser;
	@FXML private Button btnCancel;
	@FXML private TextField textFieldUsername;
	ClientController client = ClientController.getClient();
	
	public void addUser() {
		client.addGroupMember(client.getGroup().getGroupName(),
				new User(textFieldUsername.getText()));
	}

	public void cancel() {
		Main.getNewMemberStage().close();
	}
}