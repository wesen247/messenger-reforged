package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class EventWindowController implements Initializable {

	@FXML
	private TextArea txtAreaEvents;

	public void initialize(URL location, ResourceBundle resources) {

//		txtAreaEvents.setEditable(false);
//		update();
	}

	public void update() {

		for (int i = 0; i < Data.getData().getGroups().get(ClientController.getClient().getGroup()).getEvents()
				.size(); i++) {

			txtAreaEvents.appendText(Data.getData().getGroups().get(ClientController.getClient().getGroup()).getEvents()
					.get(i).getComment());
		}

	}
}
