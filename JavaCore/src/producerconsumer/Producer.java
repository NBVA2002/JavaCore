package producerconsumer;


public class Producer implements Runnable {
	private String name;
    private final BlockingQueue<Message> queue;
 
    Producer(BlockingQueue<Message> queue, String name) {
        this.queue = queue;
        this.name = name;
    }
 
    public void run() {
        try {
        	int count = 0;
            while (true) {
                queue.put(new Message("message" + (++count)));
                System.out.println(this.name + "("  + queue.size() + "/" + queue.getCompacity() + ")");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	public String getName() {
		return name;
	}
 
}
