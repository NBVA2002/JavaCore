package java_core_basic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrueNumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		FileReader reader = null;
		BufferedReader bufferedReader = null;

		try {
			reader = new FileReader("config/TrueNumConfig.txt");
			bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			int num = Integer.parseInt(line.trim());
			
			trueNumber(sc, num);

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
		sc.close();
	}

	public static void trueNumber(Scanner sc, int trueNum) {
		String input;
		int number = 0;
		int count = 0;
		boolean flag = true;

		System.out.println("Nhập số chính xác (tối đa 5 lần)");
		while (flag) {
			System.out.print("Còn " + (5 - count) + "/5 lần: ");
			try {
				input = sc.next();
				number = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("Phải nhập số nguyên");
			}

			count++;
			if (number == trueNum) {
				flag = false;
				System.out.println("Thành công");
			} else if (count == 5 && number != trueNum) {
				System.out.println("Nhập lỗi");
				System.exit(0);
			}
		}

	}

}
