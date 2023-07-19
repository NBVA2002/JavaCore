package socket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
	private String ip;
	private int port;
	
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
		try {
			// Sending to server
			while (true) {
				Socket socket = new Socket(ip, 1985);
				new ClientThread(socket).start();

			    Thread.sleep(1000);
			    
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Mất kết nối sever");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Client client = new Client();
		client.connect();
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

}
