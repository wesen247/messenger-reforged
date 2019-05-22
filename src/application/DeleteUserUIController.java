package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DeleteUserUIController implements Initializable {

	@FXML
	private Button btnDelte;
	@FXML
	private Button btnCancel;
	@FXML
	private TextField txtFieldPassword;

	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void delete() {

		ClientController.getClient().deleteAccount(txtFieldPassword.getText());

		
	}

	public void cancel() {
		Main.getDeleteStage().close();
	}

}
