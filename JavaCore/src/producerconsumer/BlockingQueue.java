package producerconsumer;
import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private int compacity;
    private final Queue<T> items = new LinkedList<>();
 
    public BlockingQueue(int compacity) {
    	this.compacity = compacity;
	}
    
    public synchronized void put(T value) throws InterruptedException {
        while (items.size() == compacity) {
            System.out.println("Queue is full");
            wait();
        }
        items.add(value);
        notifyAll();
    }
 
    public synchronized T take() throws InterruptedException {
        while (items.size() == 0) {
            System.out.println("Queue is empty");
            wait();
        }
        notifyAll();
        return items.poll();
    }
     
    public synchronized int size() {
        return items.size();
    }

	public int getCompacity() {
		return compacity;
	}
    
}