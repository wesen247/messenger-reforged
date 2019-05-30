package application;

import java.net.URL;
import java.util.ResourceBundle;

import entity.Group;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class EventWindowController implements Initializable {
	@FXML private TextArea txtAreaEvents;

	public void initialize(URL location, ResourceBundle resources) {
		txtAreaEvents.setEditable(false);
		update();
	}

	public void update() {
		Data.getData();
		Group group = Data.getGroups().get(ClientController.getClient().getGroup().getGroupName());
		for (int i = 0; i < group.getEvents().size(); i++) {
			txtAreaEvents.appendText(group.getEvents().get(i).getDate() + " " 
					+ group.getEvents().get(i).getComment() 
					+ " @ "+group.getEvents().get(i).getLocation());
			txtAreaEvents.appendText("\n");
		}
	}
}