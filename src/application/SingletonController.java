package application;

public class SingletonController {
	private static final ClientController controller = new ClientController();
	private SingletonController() {}
	public static ClientController getController() {
		return controller;
	}
}
