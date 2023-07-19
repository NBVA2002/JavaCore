package connnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO {
	private static StudentDAO instace = new StudentDAO();
	
	public static StudentDAO getInstance() {
		return instace;
	}

	public boolean insert(Student t) {
		try {
			Connection connection = JDBCConnection.getJDBCConnection();
			
			String sql = "INSERT INTO  STUDENT(NAME, GENDER, COUNTRY, AGE)"
					+ " VALUES(?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, t.getName());
			statement.setString(2, t.getGender());
			statement.setString(3, t.getCountry());
			statement.setInt(4, t.getAge());

			int check = statement.executeUpdate();
			if (check > 0) {
				System.out.println("them du lieu thanh cong");
			} else {
				System.out.println("Them du lieu that bai");
			}

			JDBCConnection.closeConnection(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Nhập sai định dạng");
			return false;
		}
		return true;
	}

	public ArrayList<Student> selectAll() {
		ArrayList<Student> students = new ArrayList<>();
		try {
			Connection connection = JDBCConnection.getJDBCConnection();

			String sql = "SELECT * FROM STUDENT";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String name = resultSet.getString("NAME");
				String gender = resultSet.getString("GENDER");
				String country = resultSet.getString("COUNTRY");
				int age = resultSet.getInt("AGE");

				students.add(new Student(id, name, gender, country, age));
			}
			JDBCConnection.closeConnection(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}

	public int getSequence() {
		int sequence = 0;
		try {
			Connection connection = JDBCConnection.getJDBCConnection();

			String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'test' AND TABLE_NAME = 'student'";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				sequence = resultSet.getInt("AUTO_INCREMENT");
			}
			JDBCConnection.closeConnection(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sequence;
	}
	
	public static void main(String[] args) {
//		System.out.println(StudentDAO.getInstance().getSequence());
//		StudentDAO.getInstance().insert(new Student("Student", "male", "Thanh Hoa", 23));
	}
}
