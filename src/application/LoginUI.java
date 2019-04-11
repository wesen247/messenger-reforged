package application;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
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
	private Button btnLogIn;
	@FXML
	private Button createNewUserBtn;
	@FXML
	private Button btnCancel;
	@FXML
	private MenuItem menu;
	@FXML
	private Button btnCreateUser;
	@FXML
	private Button btnCreateGroup;
	@FXML
	private TextField groupNameTextField;
	@FXML
	private Button buttonCancelGroup;
	@FXML
	private TextArea textFieldGroups;
	@FXML
	private TextField logInUsernameField;
	@FXML
	private TextField logInPasswordField;

	private ClientController ctr;
	private FXMLLoader loader = new FXMLLoader();
	private Parent root;
	private Scene scene;
	private Parent root1;
	private ObservableList ol;
	private Stage stage1 = new Stage();
	Button button;
	private int i = 0;

	private boolean loginRequest = false;

	public LoginUI() {

		this.ctr = SingletonController.getController();

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
		new Data();
	}

	public void buttonLogin() {

		String username = logInUsernameField.getText();
		String password = logInPasswordField.getText();
		ctr.logIn(username, password);

	}

	public void logInTrue() {

		try {

			root1 = FXMLLoader.load(getClass().getResource("/application/StartMenu.fxml"));

		} catch (IOException e) {

			e.printStackTrace();
		}

		Stage stage = new Stage();
		stage.setTitle("HHAHAH");
		stage.setScene(new Scene(root1));
		stage.show();

		Stage stage1 = (Stage) btnLogIn.getScene().getWindow();
		stage1.close();

		System.out.println("logged in");
	}

	public void loginFalse(String response) {

		JOptionPane.showMessageDialog(null, response);

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

		ctr.createNewUser(username, password);

		System.out.println(username);
		System.out.println(password);

		try {

			root = FXMLLoader.load(getClass().getResource("/application/LgInUI.fxml"));

		} catch (IOException e) {

			e.printStackTrace();
		}

		scene = new Scene(root);
		stage1.setTitle("Login");
		stage1.setScene(scene);
		stage1.show();

		Stage stage = (Stage) btnCreateUser.getScene().getWindow();

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

	public void buttonCancelGroup() {

		try {
			root = FXMLLoader.load(getClass().getResource("/application/StartMenu.fxml"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		scene = new Scene(root);
		stage1.setTitle("Login");
		stage1.setScene(scene);
		stage1.show();

		Stage stage = (Stage) buttonCancelGroup.getScene().getWindow();
		stage.close();

	}

	public void setText(String user) {

		textFieldGroups.setText(user);

	}

}
