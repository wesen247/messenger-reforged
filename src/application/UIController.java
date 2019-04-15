package application;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UIController extends Application {
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
	@FXML
	private Pane paneCreateUser;

	private ClientController ctr;

	private FXMLLoader loader = new FXMLLoader();

	private Scene scene;

	private ObservableList ol;

	Button button;

	private int i = 0;
	private Parent root2;
	private boolean loginRequest = false;

	public UIController() {

		this.ctr = SingletonController.getController();

	}

	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("/application/LgInUI.fxml"));
		scene = new Scene(root);
		stage.setTitle("Login");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {

		launch(args);

	}

	public void buttonLogin() {

		String username = logInUsernameField.getText();
		String password = logInPasswordField.getText();
		ctr.logIn(username, password);

	}

	public void logInTrue() {

		

	}

	public void loginFalse(String response) {

		JOptionPane.showMessageDialog(null, response);

	}

	public void createNewUser() {

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// if you change the UI, do it here !

				try {

					Parent root = FXMLLoader.load(getClass().getResource("/application/CreateUserUI.fxml"));
					Stage stage = new Stage();
					stage.setTitle("Create new user");
					stage.setScene(new Scene(root));
					stage.show();

					Stage stage1 = (Stage) btnLogIn.getScene().getWindow();
					stage1.close();

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		});
	}

	public void buttonCancel() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// if you change the UI, do it here !

				try {

					Parent root = FXMLLoader.load(getClass().getResource("/application/LgInUI.fxml"));
					scene = new Scene(root);
					Stage stage1 = new Stage();
					stage1.setTitle("Login");
					stage1.setScene(scene);
					stage1.show();

				} catch (IOException e) {

					e.printStackTrace();
				}

				Stage stage = (Stage) btnCancel.getScene().getWindow();
				stage.close();
			}
		});
	}

	public void buttonCreate() {
		Parent root;

		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		ctr.createNewUser(username, password);

		Stage stage1 = (Stage) createNewUserBtn.getScene().getWindow();
		stage1.close();

		try {
		
			Platform.setImplicitExit(false);
			root = FXMLLoader.load(getClass().getResource("/application/StartMenu.fxml"));

			Stage stage = new Stage();
			stage.setTitle("Start Menu");
			stage.setScene(new Scene(root));
			stage.show();
			System.out.println("FUNKAR");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void buttonLogOut() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// if you change the UI, do it here !

				try {

					Parent root = FXMLLoader.load(getClass().getResource("/application/LgInUI.fxml"));
					scene = new Scene(root);
					Stage stage1 = new Stage();
					stage1.setTitle("Login");
					stage1.setScene(scene);
					stage1.show();

				} catch (IOException e) {

					e.printStackTrace();
				}

				Stage stage = (Stage) pane.getScene().getWindow();

				stage.close();
			}
		});
	}

	public void buttonCreateNewGroup() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// if you change the UI, do it here !

				try {

					Parent root = FXMLLoader.load(getClass().getResource("/application/CreateGroup.fxml"));

					scene = new Scene(root);
					Stage stage1 = new Stage();
					stage1.setTitle("Login");
					stage1.setScene(scene);
					stage1.show();

				} catch (IOException e) {

					e.printStackTrace();
				}

				Stage stage = (Stage) pane.getScene().getWindow();
				stage.close();
			}
		});
	}

	public void buttonCreateGroup() {

		String groupName = groupNameTextField.getText();
		System.out.println(groupName);

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// if you change the UI, do it here !

				try {
					Parent root = FXMLLoader.load(getClass().getResource("/application/StartMenu.fxml"));
					scene = new Scene(root);
					Stage stage1 = new Stage();
					stage1.setTitle("Login");
					stage1.setScene(scene);
					stage1.show();

				} catch (IOException e) {

					e.printStackTrace();
				}

				Stage stage = (Stage) btnCreateGroup.getScene().getWindow();
				stage.close();
			}
		});
	}

	public void buttonCancelGroup() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// if you change the UI, do it here !

				try {
					Parent root = FXMLLoader.load(getClass().getResource("/application/StartMenu.fxml"));

					scene = new Scene(root);
					Stage stage1 = new Stage();
					stage1.setTitle("Login");
					stage1.setScene(scene);
					stage1.show();

				} catch (IOException e) {

					e.printStackTrace();
				}

				Stage stage = (Stage) buttonCancelGroup.getScene().getWindow();
				stage.close();
			}
		});
	}

	public void btnUploadImage() {

		System.out.println("uploaded");

		JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		j.showOpenDialog(new JFrame());
		try {
			BufferedImage image = ImageIO.read(j.getSelectedFile());

			ctr.setPicture(image);

		} catch (IOException e) {
			e.printStackTrace();

		}

	}
	// public void setText() {
	//
	// textFieldGroups.setText("tja");
	//
	// }

}
