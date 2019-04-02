package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GroupChatUI extends Application {
	
	private Parent content;
	private FXMLLoader loader = new FXMLLoader();
	private Parent root;
	private Scene scene;

	public GroupChatUI() {

	}

	public void start(Stage stage) throws Exception {

		root = FXMLLoader.load(getClass().getResource("/application/ChattWindowGroup.fxml"));
		scene = new Scene(root);

		stage.setTitle("Login");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {

		launch(args);

	}

}