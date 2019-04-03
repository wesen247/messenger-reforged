package application;

import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TestController extends Application {
	private Parent root;
	private Scene scene;
	@FXML
	private TextField textfieldComment;
	@FXML
	private JFXDatePicker jfxDate;
	@FXML
	private JFXTimePicker jfxTime;

	public TestController() {
		// TODO Auto-generated constructor stub
	}
	

	public void createEvent() {
		date();
		time();
		comment();
	}
	
	
	public void date() {
		String datum = (jfxDate.getValue()!=null ? jfxDate.getValue().toString() : "");
		
		if(!datum.equals("")) {
			System.out.println("Valt datum: " + datum);
		}else {
			System.out.println("Du har inte valt ett datum! Piczko.");
		}		
	}
	
	
	public void time() {
		String tid = (jfxTime.getValue()!=null ? jfxTime.getValue().toString() : "");
		
		if(!tid.equals("")) {
		System.out.println("Vald tid: " + tid);
		}else {
			System.out.println("Du har inte valt ett klockslag! Kocko.");
		}
	}

	
	public void comment() {
		String kommentar = textfieldComment.getText();
		System.out.println("Din kommentar: " + kommentar);
	}

	
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/application/Calendar.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Calendar");
		stage.setScene(scene);
		stage.show();
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
