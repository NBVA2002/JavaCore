package java_core_basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/input.txt"));
            String line;
            ArrayList<Integer> arr = new ArrayList<Integer>();
            while ((line = reader.readLine()) != null) {
                int num = Integer.parseInt(line.trim());
                arr.add(num);
            }
            reader.close();
            // sử dụng mảng arr ở đây
        } catch (IOException e) {
            System.out.println("Không thể đọc file");
        }
    }

}
