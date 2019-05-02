package application;

import java.net.URL;
import java.util.ResourceBundle;

import entity.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddNewGroupMemberController implements Initializable {
	private Main main;

	@FXML
	private Button btnAddUser;
	@FXML
	private Button btnCancel;
	@FXML
	private TextField textFieldUsername;

	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void addUser() {
		ClientController.getClient().addGroupMember(ClientController.getClient().getGroup().getGroupName(),
				new User(textFieldUsername.getText()));
	}

	public void cancel() {
		Main.getNewMemberStage().close();
	}

}
