package connnection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection {
	
	public static Connection getJDBCConnection() {
		FileReader reader = null;
		BufferedReader bufferedReader = null;

		try {
			reader = new FileReader("config/ConectionConfig.txt");
			bufferedReader = new BufferedReader(reader);
			String line;
			ArrayList<String> list = new ArrayList<>();
			while ((line = bufferedReader.readLine()) != null) {
				list.add(line.trim());
			}

			final String url = list.get(0);
			final String user = list.get(1);
			final String password = list.get(2);

			return DriverManager.getConnection(url, user, password);

		} catch (FileNotFoundException ex) {
			Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		
		return null;
	}

	public static void closeConnection(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection connection = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM STUDENT";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			ResultSet resultSet = preparedStatement.executeQuery(sql);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String name = resultSet.getString("NAME");
				String gender = resultSet.getString("GENDER");
				String country = resultSet.getString("COUNTRY");
				int age = resultSet.getInt("AGE");
				Student s = new Student(id, name, gender, country, age);
				System.out.println(s);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
