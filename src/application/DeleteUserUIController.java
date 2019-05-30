package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Handles the deleteUserStage
 * @author Ruben, Amir
 */
public class DeleteUserUIController {
	@FXML private Button btnDelte;
	@FXML private Button btnCancel;
	@FXML private TextField txtFieldPassword;

	/**
	 * Deletes a account
	 * @author Ruben, Amir
	 */
	public void delete() {
		ClientController.getClient().deleteAccount(txtFieldPassword.getText());
	}

	/**
	 * Closes the stage
	 * @author Ruben, Amir
	 */
	public void cancel() {
		Main.getDeleteUserStage().close();
	}
}
