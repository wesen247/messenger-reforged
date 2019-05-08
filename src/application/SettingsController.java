package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class is for the settings UI. Here you can get information about the system and can 
 * change the color theme.
 * @author Alexander Måbrink
 *
 */
public class SettingsController implements Initializable {

	private ToggleGroup toggleGroup;

	@FXML private RadioButton btnRDark;
	@FXML private RadioButton btnRLight;
	@FXML private Button btnApply;
	@FXML private Button btnCancel;
	@FXML private Button btnDeleteAccount;
	@FXML private Text faq;
	@FXML private Text theme;
	private Main main;

	/**
	 * Creates a group of the radiobuttons so only one can be picked at a time.
	 * @author Alexander Måbrink
	 */
	public void ToggleGroup() {
		toggleGroup = new ToggleGroup();
		this.btnRDark.setToggleGroup(toggleGroup);
		this.btnRLight.setToggleGroup(toggleGroup);
	}

	/**
	 * This class change which radio button that should be working depending on if the user has
	 * dark or light theme activated.
	 * @author Alexander Måbrink
	 */
	public void initialize(URL location, ResourceBundle resource){
		if(ClientController.getClient().getDarkMode()) {
			btnRDark.setSelected(true);
			btnRLight.setSelected(false);
			btnRLight.setDisable(false);
			btnRDark.setDisable(true);
		} else {
			btnRDark.setSelected(false);
			btnRLight.setSelected(true);
			btnRDark.setDisable(false);
			btnRLight.setDisable(true);
		}
	}

	/**
	 * Method that creates events for the buttons.
	 * @author Alexander Måbrink
	 * @param e the events 
	 */
	public void changeTheme(ActionEvent e) throws IOException {
		if (e.getSource() == btnApply){
			if (this.toggleGroup.getSelectedToggle().equals(this.btnRDark)){
				ClientController.getClient().setDarkMode(true);
				main.getPrimaryStage().getScene().getStylesheets().add("/application/Darkmode.css");
				main.getSettingsStage().getScene().getStylesheets().add("/application/Darkmode.css");
				main.getPrimaryStage().getScene().getStylesheets().remove("/application/Lightmode.css");
				main.getSettingsStage().getScene().getStylesheets().remove("/application/Lightmode.css");
				btnRDark.setDisable(true);
				btnRLight.setDisable(false);
			} else if (this.toggleGroup.getSelectedToggle().equals(this.btnRLight)){
				ClientController.getClient().setDarkMode(false);
				main.getPrimaryStage().getScene().getStylesheets().remove("/application/Darkmode.css");
				main.getSettingsStage().getScene().getStylesheets().remove("/application/Darkmode.css");
				main.getPrimaryStage().getScene().getStylesheets().add("/application/Lightmode.css");
				main.getSettingsStage().getScene().getStylesheets().add("/application/Lightmode.css");
				btnRLight.setDisable(true);
				btnRDark.setDisable(false);
			}
		}
	}

	/**
	 * Method that close the settings UI when the button "cancel" is clicked.
	 * @author Alexander Måbrink
	 * 
	 * @param e the events
	 */

	public void Cancel(ActionEvent e) {
		if(e.getSource() == btnCancel) {
			Stage stage1 = (Stage) btnCancel.getScene().getWindow();
			stage1.close();
		}
	}
}