package connnection;

import java.util.Scanner;

public class MainRun {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		StudentManeger maneger = new StudentManeger();

		System.out.print("Number student insert: ");
		int numStudent = inputInteger(sc);
		Student[] listStudent = new Student[numStudent];

		for (int i = 0; i < listStudent.length; i++) {
			System.out.println("Student " + (i + 1) + ": ");
			System.out.print("Name: ");
			sc.nextLine();
			String name = sc.nextLine();
			System.out.print("Gender: ");
			String gender = sc.next();
			System.out.print("Country: ");
			sc.nextLine();
			String country = sc.nextLine();
			System.out.print("Age: ");
			int age = inputInteger(sc);
			listStudent[i] = new Student(name, gender, country, age);
			System.out.println();

		}

		int count = 0;
		for (int i = 0; i < listStudent.length; i++) {
			if (maneger.insert(listStudent[i])) {
				count++;
			}
		}
		System.out.println("Thêm thành công " + count + " dữ liệu");

		sc.close();
	}
	
	public static int inputInteger(Scanner sc) {
		int reusult = 0;
		boolean isValidInput = false;

		while (!isValidInput) {
			String input = sc.next();
			try {
				reusult = Integer.parseInt(input);
				isValidInput = true;
			} catch (NumberFormatException e) {
				System.out.print("Phải là số nguyên vui lòng nhập lại: ");
			}
		}
		return reusult;
	}
}
