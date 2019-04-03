package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Data {

	private ObjectInputStream ois;
	private Socket socket;

	public Data() {

		try {
			socket = new Socket(InetAddress.getLocalHost(), 1234);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private class ServerListener extends Thread {

		public void run() {
			
			try {
			
				ois = new ObjectInputStream(socket.getInputStream());
			
			} catch (IOException e) {

				e.printStackTrace();
			}

			while (true) {

				try {
				
					Object object = ois.readObject();
				
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}

				
				
				
			}

		}

	}

}
