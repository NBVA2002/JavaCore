package order;

import java.io.Serializable;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nameProduct;
	private String nameSeller;
	private int price;
	
	public Product(String nameProduct, String nameSeller, int price) {
		super();
		this.nameProduct = nameProduct;
		this.nameSeller = nameSeller;
		this.price = price;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getNameSeller() {
		return nameSeller;
	}

	public void setNameSeller(String nameSeller) {
		this.nameSeller = nameSeller;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [nameProduct=" + nameProduct + ", nameSeller=" + nameSeller + ", price=" + price + "]";
	}

}
