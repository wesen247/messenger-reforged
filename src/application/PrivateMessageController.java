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

/**
 * Handles the privateMessage stage
 * @author Ruben, Amir
 *
 */
public class PrivateMessageController implements Initializable {
	@FXML private Button btnSend;
	@FXML private TextField textFieldMessage;
	@FXML private TextArea textAreaChat;
	@FXML private ImageView imageViewUser;
	@FXML private Text textReciever;
	private Worker worker;

	/**
	 * Sends a message
	 * @author Ruben, Amir
	 */
	public void send() {
		ClientController client = ClientController.getClient();
		client.createPrivateMessage(textFieldMessage.getText());
		showMessage(client.getUser().getName() + ": " + textFieldMessage.getText());
		textFieldMessage.clear();
	}

	/**
	 * Shows a received message in the window
	 * @param message
	 * @author Ruben, Amir
	 */
	public void showMessage(String message) {
		textAreaChat.appendText(message);
		textAreaChat.appendText("\n");
	}

	/**
	 * Initializes the class
	 * @author Ruben, Amir
	 */
	public void initialize(URL location, ResourceBundle resource) {
		ClientController client = ClientController.getClient();
		Data.getData();
		for (int i = 0; i < Data.getUsers().size(); i++) {
			if (Data.getUsers().get(i).getName().equals(client.getReceiver())) {
				textReciever.setText(Data.getUsers().get(i).getName());
				Image image = SwingFXUtils.toFXImage(Data.getUsers().get(i).getImage(), null);
				imageViewUser.setImage(image);
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

	/**
	 * Inner class that handles a thread
	 * @author Ruben, Amir
	 *
	 */
	private class Worker extends Thread {
		private Boolean stop = false;
		private Buffer<String> buffer;
		
		/**
		 * Stops the buffer
		 * @author Amir
		 */
		public void notifyThread() {
			synchronized (this) {
				buffer.stopThis();
			}
		}

		/**
		 * Gets messages from the buffer to show in the window
		 * @author Ruben, Amir
		 */
		public void run() {
			buffer = Data.getData().getMessageBuffer(ClientController.getClient().getReceiver());
			try {
				while (!stop) {
					showMessage(buffer.get());
				}
			} catch (Exception e) {
				System.err.println("Killing old thread");
			}
		}
	}
}
