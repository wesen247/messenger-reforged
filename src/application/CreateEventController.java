package application;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateEventController {
	@FXML private Button btnCreateEvent;
	@FXML private Button btnCancel;
	@FXML private TextField textFieldComment;
	@FXML private DatePicker datePicker;
	@FXML private TextField textFieldLocation;

	public void createNewEvent() {
		LocalDate localDate = datePicker.getValue();
		try {
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			LocalDate ld = instant.atZone(ZoneId.systemDefault()).toLocalDate();
			System.out.println(ld);
			String location = textFieldLocation.getText();
			String comment = textFieldComment.getText();
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
