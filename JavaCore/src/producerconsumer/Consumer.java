package producerconsumer;


public class Consumer implements Runnable {
	private String name;
    private final BlockingQueue<Message> queue;
 
    Consumer(BlockingQueue<Message> queue, String name) {
        this.queue = queue;
        this.name = name;
    }
 
    public void run() {
        try {
            while (true) {
            	Message msg = queue.take();
                System.out.println(this.name + ": " + msg.getMSG() + " ("  + queue.size() + "/" + queue.getCompacity() + ")");

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