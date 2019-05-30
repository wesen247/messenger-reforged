package application;

import entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Handles the NewMemberStage
 * @author Amir
 *
 */
public class AddNewGroupMemberController {
	@FXML private Button btnAddUser;
	@FXML private Button btnCancel;
	@FXML private TextField textFieldUsername;
	ClientController client = ClientController.getClient();
	
	/**
	 * Adds a user to the group
	 * @author Amir
	 */
	public void addUser() {
		client.addGroupMember(client.getGroup().getGroupName(),
				new User(textFieldUsername.getText()));
	}
	
	/**
	 * Close the stage
	 * @author Amir
	 */
	public void cancel() {
		Main.getNewMemberStage().close();
	}
}