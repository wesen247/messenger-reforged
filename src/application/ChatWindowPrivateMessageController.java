package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class ChatWindowPrivateMessageController implements Initializable {
	@FXML
	private Button btnSend;
	@FXML
	private TextField textFieldMessage;
	@FXML
	private TextArea chat;
	@FXML
	private ImageView userImage;
	@FXML
	private Text recieverName;

	private Worker worker;

	public void send() {
		ClientController.getClient().createPrivateMessage(textFieldMessage.getText());
		showMessage(ClientController.getClient().getUser().getName() + ": " + textFieldMessage.getText());
		textFieldMessage.clear();
	}

	public void showMessage(String message) {
		chat.appendText(message);
		chat.appendText("\n");
	}

	public void initialize(URL location, ResourceBundle resource) {
		for (int i = 0; i < Data.getData().getUsers().size(); i++) {
			if (Data.getData().getUsers().get(i).getName().equals(ClientController.getClient().getReceiver())) {
				recieverName.setText(Data.getData().getUsers().get(i).getName());
				Image image1 = SwingFXUtils.toFXImage(Data.getData().getUsers().get(i).getImage(), null);
				userImage.setImage(image1);
			}
		}

		Main.getPMStage().setOnCloseRequest(e -> worker.notifyThread());
		worker = new Worker();
		worker.start();

		textFieldMessage.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode() == KeyCode.ENTER) {
					send();
				}
			}
		});
	}

	private class Worker extends Thread {
		private Boolean stop = false;
		private Buffer<String> buffer;

		public void notifyThread() {
			synchronized (this) {
				buffer.stopThis();

			}
		}

		public void run() {
			buffer = Data.getData().getMessageBuffer(ClientController.getClient().getReceiver());

			try {
				while (!stop) {

					System.out.println(Thread.currentThread().getName());
					showMessage(buffer.get());

				}
			} catch (Exception e) {
				System.err.println("Killing old thread");

			}
		}
	}
}
