package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class OrderDAO {
	private static OrderDAO instace = new OrderDAO();

	public static OrderDAO getInstance() {
		return instace;
	}

	public boolean insert(Order t) {
		try {
			Connection connection = JDBCConnection.getJDBCConnection();

			String sql = "INSERT INTO  LIST_ORDER(name_buyer, product)" + " VALUES(?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, t.getNameBuyer());
			Gson gson = new Gson();
			String json = gson.toJson(t.getProducts());
			statement.setString(2, json);

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

	public ArrayList<Order> selectAll() {
		ArrayList<Order> orders = new ArrayList<>();
		try {
			Connection connection = JDBCConnection.getJDBCConnection();

			String sql = "SELECT * FROM LIST_ORDER";

			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id_order");
				String nameBuyer = resultSet.getString("name_buyer");
				String productList = resultSet.getString("product");
				Gson gSon = new Gson();
				ArrayList<Product> products = gSon.fromJson(productList, new TypeToken<ArrayList<Product>>() {
				}.getType());

				orders.add(new Order(id, products, nameBuyer));
			}
			JDBCConnection.closeConnection(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	public int getSequence() {
		int sequence = 0;
		try {
			Connection connection = JDBCConnection.getJDBCConnection();

			String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'test' AND TABLE_NAME = 'list_order'";

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
		ArrayList<Product> p = new ArrayList<Product>();
		p.add(new Product("SanPham1", "NguoiBan1", 20000));
		OrderDAO.getInstance().insert(new Order(p, "Viet Anh Nguyen Ba"));
		System.out.println(OrderDAO.getInstance().getSequence());
	}
}
