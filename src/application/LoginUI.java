package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class LoginUI {
	FXMLLoader loader = new FXMLLoader();
	loader.setLocation(getClass().getResource("/application/LgInUI.fxml"));
	Parent content = loader.load();
	

}
