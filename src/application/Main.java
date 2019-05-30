package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Handles all javaFX stages
 * @author Ruben, Amir, Alexander
 *
 */
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

	/**
	 * Shows the first window
	 * @author Ruben, Amir
	 */
	public void start(Stage primaryStage) throws Exception {
		Main.primaryStage = primaryStage;
		showLogin();
	}

	/**
	 * Shows the login window
	 * @throws IOException
	 * @author Ruben, Amir
	 */
	public static void showLogin() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/LgInUI.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	/**
	 *
	 * @author Ruben, Amir
	 * @return Private Message stage
	 */
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
	 * Shows the create group window
	 * @throws IOException
	 * @author Alexander Måbrink, Amir, Ruben
	 */
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

	/**
	 * Shows the crate user window
	 * @author Ruben, Amir
	 * @throws IOException
	 */
	public static void showCreateUser() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/application/CreateUserUI.fxml"));
		mainPane = loader.load();
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	/**
	 * Shows the private message window
	 * @throws IOException
	 * @author Alexander Måbrink
	 */
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

	/**
	 * Shows the main menu window
	 * @throws IOException
	 * @author Alexander Måbrink, Ruben, Amir
	 */
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

	/**
	 * Shows the group chat window
	 * @throws IOException
	 * @author Alexander Måbrink, Amir, Ruben
	 */
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

	/**
	 * Shows the add group member window
	 * @throws IOException
	 * @author Alexander Måbrink, Amir, Ruben
	 */
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

	/**
	 * Shows the crate event window
	 * @throws IOException
	 * @author Alexander Måbrink, Ruben, Amir
	 */
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

	/**
	 * Shows the event window
	 * @throws IOException
	 * @author Alexander Måbrink, Ruben, Amir
	 */
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

	/**
	 * Shows the delete user window
	 * @throws IOException
	 * @author Alexander Måbrink, Amir, Ruben
	 */
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

	/**
	 * Shows the download files window
	 * @throws IOException
	 * @author Alexander Måbrink, Amir, Ruben
	 */
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

	/**
	 * @author Ruben, Amir
	 * @return deleteUserStage
	 */
	public static Stage getDeleteStage() {
		return deleteUserStage;
	}

	/**
	 * @author Ruben, Amir
	 * @return primaryStage
	 */
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * @author Ruben, Amir
	 * @return addGroupMemberStage
	 */
	public static Stage getNewMemberStage() {
		return addGroupMemberStage;
	}

	/**
	 * @author Ruben, Amir
	 * @return eventStage
	 */
	public static Stage getEventStage() {
		return eventStage;
	}

	/**
	 * @author Ruben, Amir
	 * @return deleteUserStage
	 */
	public static Stage getDeleteUserStage() {
		return deleteUserStage;
	}

	/**
	 * @author Ruben, Amir
	 * @return calendarStage
	 */
	public static Stage getCalendarStage() {
		return calendarStage;
	}

	/**
	 * @author Ruben, Amir
	 * @return fileStage
	 */
	public static Stage getFileStage() {
		return fileStage;
	}

	/**
	 * @author Ruben, Amir
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
