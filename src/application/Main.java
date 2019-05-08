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
	private static Stage eventStage;
	private static Stage deleteUserStage;
	private static Stage fileStage;
	private static Stage settingsStage;

	public void start(Stage primaryStage) throws Exception {

		Main.primaryStage = primaryStage;
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

	public static Stage getSettingsStage() {
		return settingsStage;
	}



	public static void showCreateGroup() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/CreateGroup.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		if (ClientController.getClient().getDarkMode()) {
			mainPane.getScene().getStylesheets().add("/application/Darkmode.css");
		} else {
			mainPane.getScene().getStylesheets().add("/application/Lightmode.css");
		}

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

		if (ClientController.getClient().getDarkMode()) {
			mainPane.getScene().getStylesheets().add("/application/Darkmode.css");
		} else {
			mainPane.getScene().getStylesheets().add("/application/Lightmode.css");
		}
	}

	public static void showMainMenu() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/StartMenu.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		if (ClientController.getClient().getDarkMode()) {
			mainPane.getScene().getStylesheets().add("/application/Darkmode.css");
		} else {
			mainPane.getScene().getStylesheets().add("/application/Lightmode.css");
		}
	}

	public static void showGroupChatWindow() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/ChattWindowGroup.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		groupChatStage.setScene(scene);
		groupChatStage.setResizable(false);
		groupChatStage.show();

		if (ClientController.getClient().getDarkMode()) {
			mainPane.getScene().getStylesheets().add("/application/Darkmode.css");
		} else {
			mainPane.getScene().getStylesheets().add("/application/Lightmode.css");
		}
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

		if (ClientController.getClient().getDarkMode()) {
			mainPane.getScene().getStylesheets().add("/application/Darkmode.css");
		} else {
			mainPane.getScene().getStylesheets().add("/application/Lightmode.css");
		}
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

		if (ClientController.getClient().getDarkMode()) {
			mainPane.getScene().getStylesheets().add("/application/Darkmode.css");
		} else {
			mainPane.getScene().getStylesheets().add("/application/Lightmode.css");
		}
	}

	public static void showEvents() throws IOException {
		eventStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/EventsWindow.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		eventStage.setScene(scene);
		eventStage.setResizable(false);
		eventStage.show();

		if (ClientController.getClient().getDarkMode()) {
			mainPane.getScene().getStylesheets().add("/application/Darkmode.css");
		} else {
			mainPane.getScene().getStylesheets().add("/application/Lightmode.css");
		}
	}

	public static void showDeleteUser() throws IOException {
		deleteUserStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/DeleteUserUI.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		deleteUserStage.setScene(scene);
		deleteUserStage.setResizable(false);
		deleteUserStage.show();

		if (ClientController.getClient().getDarkMode()) {
			mainPane.getScene().getStylesheets().add("/application/Darkmode.css");
		} else {
			mainPane.getScene().getStylesheets().add("/application/Lightmode.css");
		}
	}

	public static void showFiles() throws IOException {
		fileStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/DownloadFileUI.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		fileStage.setScene(scene);
		fileStage.setResizable(false);
		fileStage.show();

		if (ClientController.getClient().getDarkMode()) {
			mainPane.getScene().getStylesheets().add("/application/Darkmode.css");
		} else {
			mainPane.getScene().getStylesheets().add("/application/Lightmode.css");
		}

	}

	public static void showSettings() throws IOException {
		settingsStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/Settings.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		settingsStage.setScene(scene);
		settingsStage.setResizable(false);
		settingsStage.show();

		if (ClientController.getClient().getDarkMode()) {
			mainPane.getScene().getStylesheets().add("/application/Darkmode.css");
		} else {
			mainPane.getScene().getStylesheets().add("/application/Lightmode.css");
		}
	}

	public static Stage getDeleteStage() {
		return deleteUserStage;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static Stage getNewMemberStage() {
		return addGroupMemberStage;
	}

	public static Stage getEventStage() {
		return eventStage;
	}

	public static Stage getDeleteUserStage() {
		return deleteUserStage;
	}

	public static Stage getCalendarStage() {
		return calendarStage;
	}

	public static Stage getFileStage() {
		return fileStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
