package logger;




import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Logg extends Application {
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private Date date = new Date();
	private TextArea textArea;
	
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("LoggFXML.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Logger Reforged");
		stage.setScene(scene);
		stage.show();
	}
	
	public void writeToLog(String text) {
		textArea.appendText(getTime() + text);
	}
	
	public String getTime() {
		return dateFormat.format(date);
	}
	
	
	public static void main(String args[]) {
		launch(args);
	}
}
