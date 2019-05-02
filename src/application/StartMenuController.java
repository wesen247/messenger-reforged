package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StartMenuController implements Initializable {
	@FXML
	private MenuItem btnCreateGroup;
	@FXML
	private MenuItem btnLogout;
	@FXML
	private MenuItem btnSettings;
	private Main main;
	@FXML
	private ListView<String> listViewOnline = new ListView<String>();
	@FXML
	private ListView<String> listViewGroups = new ListView<String>();
	@FXML
	private ImageView profileImage;

	private String name;
	private ObservableList<String> onlineUsers = FXCollections.observableArrayList();
	private ObservableList<String> groupList = FXCollections.observableArrayList();

	private Data data;
	public static StartMenuController startmenu;

	public static StartMenuController getStartMenuController() {

		return startmenu;
	}

	public void createGroup() {
		try {
			main.showCreateGroup();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		ClientController.getClient().closeSocket();
		try {
			main.showLogin();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void settings() {
		// main.showSettings();
	}

	public void initialize(URL location, ResourceBundle resource) {

		setOnlineList();
		setGroupList();
		startmenu = this;
		this.data = Data.getData();
		data.addMenuController(this);


		listViewOnline.setItems(this.onlineUsers);
		listViewGroups.setItems(this.groupList);
		listViewOnline.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				name = newValue;
				ClientController.getClient().setReciver(name);
				try {
					main.showChatWindowPrivateMessage();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		listViewGroups.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				name = newValue;
				ClientController.getClient().setGroup(name);
				try {
					main.showGroupChatWindow();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
				

	}

	public void setOnlineList() {
		Platform.runLater(new Runnable() {
			public void run() {
				onlineUsers.clear();
				for (int i = 0; i < Data.getData().getUser().size(); i++) {
					try {
						if (!Data.getData().getUser().get(i).getName()
								.equals(ClientController.getClient().getUser().getName())) {
							onlineUsers.add(Data.getData().getUser().get(i).getName());
						}
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
				}

			}
		});

	}

	public void setGroupList() {
		Platform.runLater(new Runnable() {
			public void run() {
				groupList.clear();
				for (int i = 0; i < Data.getData().getGroup().size(); i++) {
					try {
						groupList.add(Data.getData().getGroup().get(i).getGroupName());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

}
