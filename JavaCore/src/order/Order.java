package order;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.Gson;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private int idOrder;
	private ArrayList<Product> products;
	private String nameBuyer;

	public Order(int idOrder, ArrayList<Product> products, String nameBuyer) {
		super();
		this.idOrder = idOrder;
		this.products = products;
		this.nameBuyer = nameBuyer;
	}

	public Order(ArrayList<Product> products, String nameBuyer) {
		this.products = products;
		this.nameBuyer = nameBuyer;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public String getNameBuyer() {
		return nameBuyer;
	}

	public void setNameBuyer(String nameBuyer) {
		this.nameBuyer = nameBuyer;
	}

	@Override
	public String toString() {
		return "Order [product=" + products + ", nameBuyer=" + nameBuyer + "]";
	}

	public static void main(String[] args) {

		ArrayList<Product> p = new ArrayList<Product>();
		p.add(new Product("SanPham1", "NguoiBan1", 20000));
		p.add(new Product("SanPham2", "NguoiBan2", 30000));

		Gson gson = new Gson();
		String json = gson.toJson(p);
		System.out.println(json);
//		OrderDAO.getInstance().insert(new Order(p, "Viet Anh"));
		
		
	}
}
