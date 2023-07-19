package connnection;

import java.util.ArrayList;

public class StudentManeger {
	private ArrayList<Student> list;

	public StudentManeger() {
		list = StudentDAO.getInstance().selectAll();
	}

	public boolean insert(Student s) {
		if (checkValid(s)) {
			if (StudentDAO.getInstance().insert(s)) {
				return true;
			}
		} else {
			System.out.println("khong duoc trung ten");
		}
		return false;
	}

	public boolean checkValid(Student student) {
		for (Student s : list) {
			if (s.getName().equals(student.getName())) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<Student> getList() {
		return list;
	}

}
