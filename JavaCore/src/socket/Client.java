package socket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
	private String ip;
	private int port;
	private int connectTimeOut;
	private int sendTimeOut;
	private int receiveTimeOut;

	public Client() {
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

	public void connect() {
		SocketAddress socketAddress = new InetSocketAddress(ip, port);
		Socket socket = new Socket();
		PrintStream printStream;
		try {
			// Connect to server
			socket.connect(socketAddress, connectTimeOut);
			while (socket.isConnected()) {
				printStream = new PrintStream(socket.getOutputStream());
				String str = randomMessage();
				printStream.println(str);
				System.out.println(str + socket.isConnected());
				Thread.sleep(1000);
			}
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Không có kết nối sever");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String randomMessage() {
		// Tạo một đối tượng Random
		Random random = new Random();

		// Khai báo các ký tự có thể có trong chuỗi
		String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		// Khai báo độ dài của chuỗi
		int length = 10;

		// Tạo chuỗi ngẫu nhiên
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(charset.length());
			char randomChar = charset.charAt(randomIndex);
			sb.append(randomChar);
		}
		return sb.toString();
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
	
	public static void main(String[] args) {
		Client client = new Client();
		client.connect();
	}

}
