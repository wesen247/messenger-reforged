package application;

public class ClientController {
	private LoginUI loginUI;
	private	GroupChatUI groupChatUI;
	private MainUI mainUI;
	private CreateGroupUI createGroupUI;
	private CreateNewUserUI createNewUserUI;
	private PrivateChatUI privateChatUI;
	private User user;
	private Data data;

	public ClientController() {
		
	}

	public void start() {

	}

	public void initializeMainUI() {

	}

	public void initializeLoginUI() {

	}

	public void initializePrivateChatUI() {

	}
	
	public void initializeCreateNewUserUI() {

	}

	public void initializeCreateGroupUI() {

	}

	public void initializeGroupChatUI() {

	}
	
	public void createNewGroup(String groupName, String user) {
		
	}
	
	public void createNewUser(String name, String password) {
		
	}
	
	public void createGroupMessage(String message, String group) {
		
	}
	
	public void createPrivateMessage(String message, String reciver) {
		
	}
	
	public void logIn(String userName, String password) {
		
	}
	
	public void addGroupMember(String groupName, String user) {
		
	}

	private class ServerListener extends Thread {

		public void run() {

		}
	}
	
	public static void main(String[] args) {
		ClientController controller = new ClientController();
		controller.start();
	}
}
