package java_core_basic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuickSort {

	public static void main(String[] args) throws IOException {
		FileReader reader = null;
		BufferedReader bufferedReader = null;

		try {
			reader = new FileReader("data/input.txt");
			bufferedReader = new BufferedReader(reader);

			String line = bufferedReader.readLine();
			String[] tokens = line.split(" ");
			int[] array = new int[0];

			if (tokens.length == 1) {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(Integer.parseInt(line.trim()));
				while ((line = bufferedReader.readLine()) != null) {
					list.add(Integer.parseInt(line.trim()));
				}
				array = new int[list.size()];
				for (int i = 0; i < list.size(); i++) {
					array[i] = list.get(i);
				}
				
			} else {
				array = new int[tokens.length];
				for (int i = 0; i < tokens.length; i++) {
					array[i] = Integer.parseInt(tokens[i]);
				}
			}

			System.out.print("Đọc dữ liệu mảng đầu vào: " );
			printArr(array);
			System.out.print("\nMảng sau khi sắp xếp: " );
			quickSort(array, 0, array.length - 1);
			printArr(array);

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

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static int partition(int[] arr, int low, int high) {
		int pivot = arr[high];

		int i = (low - 1);

		for (int j = low; j <= high - 1; j++) {

			if (arr[j] < pivot) {

				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}

	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}

	public static void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

}
