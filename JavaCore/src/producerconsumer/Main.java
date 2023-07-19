package producerconsumer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java_core_basic.QuickSort;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		FileReader reader = null;
		BufferedReader bufferedReader = null;

		try {
			reader = new FileReader("config/ProducerConsumerConfig.txt");
			bufferedReader = new BufferedReader(reader);
			String line;
			ArrayList<Integer> list = new ArrayList<>();
			while ((line = bufferedReader.readLine()) != null) {
				list.add(Integer.parseInt(line.trim()));
			}

			BlockingQueue<Message> boundedBuffer = new BlockingQueue<>(list.get(0));
			Producer[] producers = new Producer[list.get(1)];
			Consumer[] consumers = new Consumer[list.get(2)];

			for (int i = 0; i < producers.length; i++) {
				producers[i] = new Producer(boundedBuffer, "producer" + (i + 1));
			}
			for (int i = 0; i < consumers.length; i++) {
				consumers[i] = new Consumer(boundedBuffer, "consumers" + (i + 1));
			}

			for (int i = 0; i < producers.length; i++) {
				new Thread(producers[i]).start();
			}

			Thread.sleep(list.get(3));

			for (int i = 0; i < consumers.length; i++) {
				new Thread(consumers[i]).start();
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(QuickSort.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(QuickSort.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				Logger.getLogger(QuickSort.class.getName()).log(Level.SEVERE, null, ex);
			}

			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					Logger.getLogger(QuickSort.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

}
