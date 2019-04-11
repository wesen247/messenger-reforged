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
	private Parent root2;
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

	}

	public void buttonLogin() {

		String username = logInUsernameField.getText();
		String password = logInPasswordField.getText();
		ctr.logIn(username, password);





	}

	public void logInTrue() {


		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// if you change the UI, do it here !

				try {
					System.out.println("hej123123");
					root2 = FXMLLoader.load(getClass().getResource("/application/StartMenu.fxml"));

				} catch (IOException e) {

					e.printStackTrace();
				}

				Stage stage = new Stage();
				stage.setTitle("HHAHAH");
				stage.setScene(new Scene(root2));
				stage.show();


				try {
					Stage stage1 = (Stage) btnCancel.getScene().getWindow();
					stage1.close();
				} catch (Exception e) {
				
				}



				try {
					Stage stage2 = (Stage) btnLogIn.getScene().getWindow();
					stage2.close();
				} catch (Exception e) {
				
				}


				System.out.println("logged in");
			}
		});
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

					Parent createNewUserRoot = FXMLLoader
							.load(getClass().getResource("/application/CreateUserUI.fxml"));
					Stage stage = new Stage();
					stage.setTitle("Create new user");
					stage.setScene(new Scene(createNewUserRoot));
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
		});
	}

	public void buttonCreate() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// if you change the UI, do it here !

				String username = usernameTextField.getText();
				String password = passwordTextField.getText();

				ctr.createNewUser(username, password);

				//				System.out.println(username);
				//				System.out.println(password);
				//
				//				try {
				//
				//					root = FXMLLoader.load(getClass().getResource("/application/LgInUI.fxml"));
				//
				//				} catch (IOException e) {
				//
				//					e.printStackTrace();
				//				}
				//
				//				scene = new Scene(root);
				//				stage1.setTitle("Login");
				//				stage1.setScene(scene);
				//				stage1.show();


			}
		});
	}

	public void buttonLogOut() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// if you change the UI, do it here !

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
		});
	}

	public void buttonCreateNewGroup() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// if you change the UI, do it here !

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
		});
	}

	public void buttonCancelGroup() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// if you change the UI, do it here !

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
	//	public void setText() {
	//
	//		textFieldGroups.setText("tja");
	//
	//	}

}
