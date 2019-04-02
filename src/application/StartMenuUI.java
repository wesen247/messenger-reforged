package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartMenuUI extends Application {
	
	private Parent content;
	private FXMLLoader loader = new FXMLLoader();
	private Parent root;
	private Scene scene;

	public StartMenuUI() {

	}

	public void start(Stage stage) throws Exception {

		root = FXMLLoader.load(getClass().getResource("/application/LgInUI.fxml"));
		scene = new Scene(root);

		stage.setTitle("Login");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {

		launch(args);

	}

}