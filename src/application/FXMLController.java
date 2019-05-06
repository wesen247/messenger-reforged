package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLController implements Initializable{
	@FXML
	private Button singleButton;
	@FXML
	private Button multiButton;
	@FXML
	private AnchorPane anchorPane;

	@FXML
	private File singleButtonAction(ActionEvent e) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select a file");
		Stage stage = (Stage)anchorPane.getScene().getWindow();

		File file = fileChooser.showOpenDialog(stage);
		return file;
//		if(file!=null) {
//			Desktop desktop = Desktop.getDesktop();
//			try {
//				desktop.open(file);
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		}
	}

	@FXML
	private List<File> multipleButtonAction(ActionEvent e) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select files");
		Stage stage = (Stage)anchorPane.getScene().getWindow();
		
		List<File> fileList = fileChooser.showOpenMultipleDialog(stage);
		return fileList;
//		if(fileList!=null) {
//			for(File file : fileList) {
//				Desktop desktop = Desktop.getDesktop();
//				try {
//					desktop.open(file);
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

}
