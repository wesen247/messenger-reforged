package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatWindowPrivateMessageController implements Initializable {
	private Main main;
	@FXML
	private Button btnSend;
	@FXML
	private TextField textFieldMessage;
	@FXML
	private TextArea chat;

	private Worker worker;

	public void send() {
		ClientController.getClient().createPrivateMessage(textFieldMessage.getText());
		textFieldMessage.clear();
	}

	public void showMessage(String message) {
		chat.appendText(message);
		chat.appendText("\n");
	}

	public void initialize(URL location, ResourceBundle resource) {
		Main.getPMStage().setOnCloseRequest(e -> worker.notifyThread());
		worker = new Worker();
		worker.start();

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
				;
			}
		}
	}
}
