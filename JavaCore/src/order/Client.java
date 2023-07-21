package order;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
	private String ip;
	private int port;
	private int connectTimeOut;
	private int sendTimeOut;
	private int receiveTimeOut;

	private String nameClient;
	private ArrayList<Product> products;

	public Client(String name, ArrayList<Product> products) {
		this.nameClient = name;
		this.products = products;
		FileReader reader = null;
		BufferedReader bufferedReader = null;

		try {
			reader = new FileReader("config/SocketConfig.txt");
			bufferedReader = new BufferedReader(reader);
			String line;
			ArrayList<String> list = new ArrayList<>();
			while ((line = bufferedReader.readLine()) != null) {
				list.add(line.trim());
			}

			this.ip = list.get(0);
			this.port = Integer.parseInt(list.get(1));
			this.connectTimeOut = Integer.parseInt(list.get(2));
			this.sendTimeOut = Integer.parseInt(list.get(3));
			this.receiveTimeOut = Integer.parseInt(list.get(4));

		} catch (FileNotFoundException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	@SuppressWarnings("resource")
	public void connect() {
		SocketAddress socketAddress = new InetSocketAddress(ip, port);
		try (Socket socket = new Socket()) {

			socket.connect(socketAddress, connectTimeOut);

			// Sending to server
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			Order order = new Order(products, nameClient);
			objectOutputStream.writeObject(order);

//			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//			Message message = (Message) objectInputStream.readObject();
//			if (message != null) {
//				System.out.println("Receive from server: " + message.getMSG());
//			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Lỗi2");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}

	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public int getConnectTimeOut() {
		return connectTimeOut;
	}

	public int getSendTimeOut() {
		return sendTimeOut;
	}

	public int getReceiveTimeOut() {
		return receiveTimeOut;
	}

	public ArrayList<Product> getProduct() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public String getNameClient() {
		return nameClient;
	}

	public static void main(String[] args) {
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product("SamPham1", "NguoiBan1", 20000));
		products.add(new Product("SamPham2", "NguoiBan1", 25000));

		ExecutorService pool = Executors.newFixedThreadPool(500);
		for (int i = 0; i < 1000; i++) {
			pool.submit(new MultiThreadClient(i) {
				public void run() {
					Client client = new Client("Client" + i, products);
					client.connect();
				};
			});
		}

	}

}
