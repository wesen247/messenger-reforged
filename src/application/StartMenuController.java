package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

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
	private String name;
	private ObservableList<String> onlineUsers;
	private Data data;
	
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
		
	}
	
	public void setOnlineList(String userUpdate) {
		onlineUsers.add(userUpdate);
	}
	
	public void initialize(URL location, ResourceBundle resource) {
		this.data = Data.getData();
		data.addMenuController(this);
		listViewOnline.setItems(this.onlineUsers);
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
	}
}
