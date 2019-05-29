package application;

import java.util.LinkedList;

public class Buffer<T> {
	private LinkedList<T> buffer = new LinkedList<T>();
	private Boolean stop = false;
	
	public synchronized void put(T obj) {
		buffer.addLast(obj);
		notifyAll();
	}
	
	public void stopThis() {
		stop = true;
		synchronized(this) {
		notifyAll();
		}
	}
	
	public synchronized T get() throws InterruptedException {
		while(buffer.isEmpty() && !stop) {
			wait();
		}
		stop = false;
		return buffer.removeFirst();
	}
	
	public int size() {
		return buffer.size();
	}
}
