package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	private static Stage groupChatStage = new Stage();
	private static Stage primaryStage;
	private static Stage privateMessage;
	private static AnchorPane mainPane;
	private static Stage addGroupMemberStage;
	private static Stage calendarStage;
	public void start(Stage primaryStage) throws Exception {

		this.primaryStage = primaryStage;
		showLogin();
	}

	public static void showLogin() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/LgInUI.fxml"));
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
		loader.setLocation(Main.class.getResource("/application/CreateGroup.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static void showCreateUser() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/CreateUserUI.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void showChatWindowPrivateMessage() throws IOException {
		privateMessage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/ChattWindowPrivateMessage.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		privateMessage.setScene(scene);
		privateMessage.setResizable(false);
		privateMessage.show();
	}

	public static void showMainMenu() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/StartMenu.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void showGroupChatWindow() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/ChattWindowGroup.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		groupChatStage.setScene(scene);
		groupChatStage.setResizable(false);
		groupChatStage.show();
	}

	public static void showAddNewGroupMember() throws IOException {
		addGroupMemberStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/AddGroupMember.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		addGroupMemberStage.setScene(scene);
		addGroupMemberStage.setResizable(false);
		addGroupMemberStage.show();
	}

	public static void showCreateEvent() throws IOException {
		calendarStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/CreateEvent.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		calendarStage.setScene(scene);
		calendarStage.setResizable(false);
		calendarStage.show();
	}
	public static Stage getNewMemberStage() {
		return addGroupMemberStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
