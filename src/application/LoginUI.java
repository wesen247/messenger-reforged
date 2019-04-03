package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginUI extends Application {
	@FXML
	private TextField usernameTextField;
	@FXML
	private TextField passwordTextField;
	@FXML
	private Pane pane;
	@FXML
	private Button loginButton;
	@FXML
	private Button createNewUserBtn;
	@FXML
	private Button btnCancel;
	@FXML
	private MenuItem menu;
	@FXML
	private Button createUserBtn;
	@FXML
	private Button btnCreateGroup;
	@FXML
	private TextField groupNameTextField;

	private ClientController ctr;
	private FXMLLoader loader = new FXMLLoader();
	private Parent root;
	private Scene scene;

	private Stage stage1 = new Stage();
	Button button;

	public LoginUI() {

		System.out.println("konsturktorn");
		ClientController ct = new ClientController();
		this.ctr = ct;
	}

	public void start(Stage stage) throws Exception {

		root = FXMLLoader.load(getClass().getResource("/application/LgInUI.fxml"));
		scene = new Scene(root);
		stage1.setTitle("Login");
		stage1.setScene(scene);
		stage1.show();

	}

	public static void main(String[] args) {

		launch(args);

	}

	public void buttonLogin() {

		System.out.println("funkar");

		try {

			Parent root1 = FXMLLoader.load(getClass().getResource("/application/StartMenu.fxml"));
			Stage stage = new Stage();
			stage.setTitle("HHAHAH");
			stage.setScene(new Scene(root1));
			stage.show();

			Stage stage1 = (Stage) loginButton.getScene().getWindow();
			stage1.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

		System.out.println("closed");
	}

	public void createNewUser() {

		try {

			Parent createNewUserRoot = FXMLLoader.load(getClass().getResource("/application/CreateUserUI.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Create new user");
			stage.setScene(new Scene(createNewUserRoot));
			stage.show();

			Stage stage1 = (Stage) createNewUserBtn.getScene().getWindow();
			stage1.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void buttonCancel() {

		try {

			root = FXMLLoader.load(getClass().getResource("/application/LgInUI.fxml"));

		} catch (IOException e) {

			e.printStackTrace();
		}

		scene = new Scene(root);

		stage1.setTitle("Login");
		stage1.setScene(scene);
		stage1.show();

		Stage stage = (Stage) btnCancel.getScene().getWindow();
		stage.close();

	}

	public void buttonCreate() {

		String username = usernameTextField.getText();
		String password = passwordTextField.getText();

		try {

			root = FXMLLoader.load(getClass().getResource("/application/LgInUI.fxml"));

		} catch (IOException e) {

			e.printStackTrace();
		}

		scene = new Scene(root);
		stage1.setTitle("Login");
		stage1.setScene(scene);
		stage1.show();

		Stage stage = (Stage) createUserBtn.getScene().getWindow();

		stage.close();
	}

	public void buttonLogOut() {

		try {
			root = FXMLLoader.load(getClass().getResource("/application/LgInUI.fxml"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		scene = new Scene(root);
		stage1.setTitle("Login");
		stage1.setScene(scene);
		stage1.show();

		Stage stage = (Stage) pane.getScene().getWindow();

		stage.close();

	}

	public void buttonCreateNewGroup() {

		try {
			root = FXMLLoader.load(getClass().getResource("/application/CreateGroup.fxml"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		scene = new Scene(root);
		stage1.setTitle("Login");
		stage1.setScene(scene);
		stage1.show();

		Stage stage = (Stage) pane.getScene().getWindow();

		stage.close();

	}

	public void buttonCreateGroup() {

		String groupName = groupNameTextField.getText();
		System.out.println(groupName);

		try {
			root = FXMLLoader.load(getClass().getResource("/application/StartMenu.fxml"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		scene = new Scene(root);
		stage1.setTitle("Login");
		stage1.setScene(scene);
		stage1.show();

		Stage stage = (Stage) btnCreateGroup.getScene().getWindow();

		stage.close();

	}

}
