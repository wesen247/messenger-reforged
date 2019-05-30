package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DeleteUserUIController {
	@FXML private Button btnDelte;
	@FXML private Button btnCancel;
	@FXML private TextField txtFieldPassword;

	public void delete() {
		ClientController.getClient().deleteAccount(txtFieldPassword.getText());
	}

	public void cancel() {
		Main.getDeleteUserStage().close();
	}
}
