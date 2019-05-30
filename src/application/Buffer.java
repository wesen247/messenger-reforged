package application;

import java.util.LinkedList;

/**
 * Buffer class
 * @author Ruben, Amir
 *
 * @param <T>
 */
public class Buffer<T> {
	private LinkedList<T> buffer = new LinkedList<T>();
	private Boolean stop = false;
	
	/**
	 * Puts an object into the buffer
	 * @param obj Any object
	 * @author Ruben, Amir
	 */
	public synchronized void put(T obj) {
		buffer.addLast(obj);
		notifyAll();
	}
	
	/**
	 * Pauses the buffer
	 * @author Ruben, Amir
	 */
	public void stopThis() {
		stop = true;
		synchronized(this) {
		notifyAll();
		}
	}
	
	/**
	 * Returns an object from the buffer
	 * @return Returns an object from the buffer
	 * @throws InterruptedException
	 * @author Ruben, Amir
	 */
	public synchronized T get() throws InterruptedException {
		while(buffer.isEmpty() && !stop) {
			wait();
		}
		stop = false;
		return buffer.removeFirst();
	}
	
	/**
	 * 
	 * @return The size of the buffer
	 * @author Ruben, Amir
	 */
	public int size() {
		return buffer.size();
	}
}
