package chat_client_server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
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
		
		Scanner sc = new Scanner(System.in);
		
		try {
			socket.connect(socketAddress, connectTimeOut);
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
			// Connect to server
			
			while (true) {
				//truyen dl lên server
				String str = sc.nextLine();
				dataOutputStream.writeUTF(str);
				dataOutputStream.flush();

				if(str.equals("q")) {
					break;
				}

				//đọc dl từ server
				String str2 = dataInputStream.readUTF();
				System.out.println("Server say: " + str2);
				
			}
			dataInputStream.close();
			dataOutputStream.close();
			socket.close();
			sc.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Không có kết nối sever");
			e.printStackTrace();
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
	
	public static void main(String[] args) {
		Client client = new Client();
		client.connect();
	}

}
