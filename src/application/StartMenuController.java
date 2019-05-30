package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class StartMenuController implements Initializable {
	@FXML private MenuItem btnCreateGroup;
	@FXML private MenuItem btnLogout;
	@FXML private MenuItem btnSettings;
	@FXML private MenuItem btnDelete;
	@FXML private ListView<String> listViewOnline = new ListView<String>();
	@FXML private ListView<String> listViewGroups = new ListView<String>();
	@FXML private ImageView profileImage;
	private ObservableList<String> onlineUsers = FXCollections.observableArrayList();
	private ObservableList<String> groupList = FXCollections.observableArrayList();
	private Data data;
	public static StartMenuController startmenu;

	public static StartMenuController getStartMenuController() {
		return startmenu;
	}

	public void createGroup() {
		try {
			Main.showCreateGroup();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		ClientController.getClient().closeSocket();
		try {
			Main.showLogin();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void settings() {
		 try {
			Main.showSettings();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize(URL location, ResourceBundle resource) {
		Main.getPrimaryStage().setOnCloseRequest(e -> System.exit(0));
		Data.getData();
		for (int i = 0; i < Data.getUsers().size(); i++) {
			Data.getData();
			if (Data.getUsers().get(i).getName().equals(ClientController.getClient().getUser().getName())) {
				Data.getData();
				Image image = SwingFXUtils.toFXImage(Data.getUsers().get(i).getImage(), null);
				profileImage.setImage(image);
			}
		}
		setOnlineList();
		setGroupList();
		startmenu = this;
		this.data = Data.getData();
		listViewOnline.setItems(this.onlineUsers);
		listViewGroups.setItems(this.groupList);
		listViewOnline.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent event) {
				ClientController.getClient().setReciver(listViewOnline.getSelectionModel().getSelectedItem());
				try {
					if (listViewOnline.getSelectionModel().getSelectedItem() != null) {
						Main.showChatWindowPrivateMessage();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		listViewGroups.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				ClientController.getClient().setGroup(listViewGroups.getSelectionModel().getSelectedItem());
				try {
					if (listViewGroups.getSelectionModel().getSelectedItem() != null) {
						Main.showGroupChatWindow();
					}
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
						System.err.println(e);
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

	public void deleteUser() {
		System.out.println("delete funkar");
		try {
			Main.showDeleteUser();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
