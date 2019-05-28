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
		loader.setLocation(Main.class.getResource("/fxml/LgInUI.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public static Stage getPMStage() {
		return privateMessage;
	}

	/**
	 * Returns the settings stage
	 * @return settings stage
	 * @author Alexander Måbrink
	 */
	public static Stage getSettingsStage() {
		return settingsStage;
	}


	/**
	 * 
	 * @throws IOException
	 * @author Alexander Måbrink
	 */
	public static void showCreateGroup() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/CreateGroup.fxml"));
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
		loader.setLocation(Main.class.getResource("/fxml/CreateUserUI.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * 
	 * @throws IOException
	 * @author Alexander Måbrink
	 */
	public static void showChatWindowPrivateMessage() throws IOException {
		privateMessage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/ChattWindowPrivateMessage.fxml"));
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

	/**
	 * 
	 * @throws IOException
	 * @author Alexander Måbrink
	 */
	public static void showMainMenu() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/StartMenu.fxml"));
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

	/**
	 * 
	 * @throws IOException
	 * @author Alexander Måbrink
	 */
	public static void showGroupChatWindow() throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/ChattWindowGroup.fxml"));
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

	/**
	 * 
	 * @throws IOException
	 * @author Alexander Måbrink
	 */
	public static void showAddNewGroupMember() throws IOException {
		addGroupMemberStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/AddGroupMember.fxml"));
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

	/**
	 * 
	 * @throws IOException
	 * @author Alexander Måbrink
	 */
	public static void showCreateEvent() throws IOException {
		calendarStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/CreateEvent.fxml"));
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

	/**
	 * 
	 * @throws IOException
	 * @author Alexander Måbrink
	 */
	public static void showEvents() throws IOException {
		eventStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/EventsWindow.fxml"));
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

	/**
	 * 
	 * @throws IOException
	 * @author Alexander Måbrink
	 */
	public static void showDeleteUser() throws IOException {
		deleteUserStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/DeleteUserUI.fxml"));
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

	/**
	 * 
	 * @throws IOException
	 * @author Alexander Måbrink
	 */
	public static void showFiles() throws IOException {
		fileStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/DownloadFileUI.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		fileStage.setScene(scene);
		fileStage.setResizable(false);
		fileStage.show();

		if (ClientController.getClient().getDarkMode()) {
			mainPane.getScene().getStylesheets().add("/application/DarkmodeDownload.css");
		} else {
			mainPane.getScene().getStylesheets().add("/application/LightmodeDownload.css");
		}

	}

	/**
	 *  Method that open the settings UI
	 * @throws IOException
	 * @author Alexander Måbrink
	 */
	public static void showSettings() throws IOException {
		settingsStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fxml/Settings.fxml"));
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
