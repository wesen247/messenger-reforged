package server;

public class ServerController {
	private UserHandler userHandler;
	private GroupHandler groupHandler;
	private Buffer<Runnable> taskBuffer;

	public ServerController(boolean useBackup, int maximumUsers) {
		userHandler = new UserHandler(useBackup,this);
		groupHandler = new GroupHandler(useBackup, this);
		taskBuffer = new Buffer<Runnable>();
		for(int i = 0;i<5+maximumUsers*2;i++) {
			new Worker();
		}
	}
	public Group getGroup(String Group) {
		return groupHandler.getGroup(Group);
	}

	public void addTask(Runnable task) {
		taskBuffer.put(task);
	}

	public void newObjectFromUser(Object incomming) {
		
	}
	
	public void send(User receiver, Object sendObject) {
		userHandler.send(receiver, sendObject);
	}
	
	public class Worker extends Thread{
		public void run() {
			while(true) {
				try {
					taskBuffer.get().run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
