package java_core_adv;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyThread extends Thread {
	boolean flag = true;

	@Override
	public void run() {
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream("output/output.txt", false);
			while (flag) {
				Random random = new Random();
				int num = random.nextInt(101);
				String line = num + "\n";
				byte[] b = line.getBytes();
				fos.write(b);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				fos.close();
			} catch (IOException ex) {
				Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MyThread thread = new MyThread();

		thread.start();

		while (true) {
			String command = sc.nextLine();
			if (command.equals("stop")) {
				thread.flag = false;
				break;
			}
		}

		sc.close();
	}
}
