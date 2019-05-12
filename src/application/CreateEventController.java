package application;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateEventController implements Initializable {

	@FXML
	private Button btnCreateEvent;
	@FXML
	private Button btnCancel;
	@FXML
	private TextField txtFieldComment;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField txtFieldLocation;

public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void createNewEvent() {
		
		LocalDate localDate = datePicker.getValue();
		try {
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			LocalDate ld = instant.atZone(ZoneId.systemDefault()).toLocalDate();
			System.out.println(ld);
			String location = txtFieldLocation.getText();
			String comment = txtFieldComment.getText();
			ClientController.getClient().addEvent(comment, ld.toString(),location);
		} catch(NullPointerException n) {
			n.printStackTrace();
		}
		Main.getCalendarStage().close();
	}

	public void cancel() {
		Main.getCalendarStage().close();
	}

}
