package server;

import java.util.LinkedList;

public class Buffer<T> {
	private LinkedList<T> buffer = new LinkedList<T>();
	
	/**
	 * Puts object in buffer
	 * @param obj Object to be put
	 */
	public synchronized void put(T obj) {
		buffer.addLast(obj);
		notifyAll();
	}
	
	/**
	 * Returns next object in buffer
	 * @return  next Object
	 * @throws InterruptedException
	 */
	public synchronized T get() throws InterruptedException {
		while(buffer.isEmpty()) {
			wait();
		}
		return buffer.removeFirst();
	}
	
	/**
	 * Returns size of buffer
	 * @return Size of buffer
	 */
	public int size() {
		return buffer.size();
	}
}
