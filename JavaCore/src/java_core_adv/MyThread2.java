package java_core_adv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import java_core_basic.QuickSort;

public class MyThread2 extends Thread {
	boolean flag = true;
	int distance;

	public MyThread2(int distance) {
		this.distance = distance;
	}

	@Override
	public void run() {
		int count = 1;
		while (flag) {
			if (count >= distance * 60) {
				flag = false;
			}
			Random random = new Random();
			int num = random.nextInt(101);
			System.out.println(num);
			try {
				Thread.sleep(distance * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count += distance;
		}
		System.out.println("done");
	}

	public static void main(String[] args) {
		FileReader reader = null;
		BufferedReader bufferedReader = null;

		try {
			reader = new FileReader("config/MyThread2Config.txt");
			bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			int input = Integer.parseInt(line.trim());

			System.out.println("Chương trình kết thúc sau " + input + " phút:");
			MyThread2 thread2 = new MyThread2(input);
			thread2.start();

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
