package java_core_basic;

import java.util.Scanner;

public class ElectricBill {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int electricNum = 0;
		boolean isValidInput = false;
		
		System.out.print("Nhập số điện đã dùng trong tháng: ");
        
        while (!isValidInput) {
            String input = sc.nextLine();          
            try {
            	electricNum = Integer.parseInt(input);
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.print("Số điện phải là số nguyên vui lòng nhập lại: ");
            }
        }
		
        System.out.println("Tiền điện tháng này là: " + electricBill(electricNum));
		sc.close();
	}
	
	public static int electricBill(int numElec) {
		int price = 0;
		for (int i = 1; i <= numElec; i++) {
			if (i < 100) {
				price += 1000;
			} else if (100 <= i && i < 150) {
				price += 1500;
			} else if (i > 150) {
				price += 2000;
			}
		}
		return price;
	}	
}
