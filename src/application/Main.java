package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	private static Stage primaryStage;
	private static Stage privateMessage;
	private static AnchorPane mainPane;

	public void start(Stage primaryStage) throws Exception {
		this.privateMessage = new Stage();
		this.primaryStage = primaryStage;
		showLogin();
		
		
	}

	public static void showLogin() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/applicationn/LgInUI.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}
	
	public static Stage getPMStage() {
		return privateMessage;
	}
	
	public static void showCreateGroup() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/applicationn/CreateGroup.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void showCreateUser() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/applicationn/CreateUserUI.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void showChatWindowPrivateMessage() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/applicationn/ChattWindowPrivateMessage.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		privateMessage.setScene(scene);
		privateMessage.setResizable(false);
		privateMessage.show();
	}

	public static void showMainMenu() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/applicationn/StartMenu.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
