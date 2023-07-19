package socket;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
	private String ip;
	private int port;
	
	public Server() {
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
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
			}
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}
	
	@SuppressWarnings("resource")
	public void serve() {
		try {
			// Start server
			ServerSocket server = new ServerSocket(1985);
			System.out.println("Server is ready...");
			
			while (true) {
				Socket socket = server.accept();
				
				// Received from a client
				new ServerThread(socket).start();
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.serve();
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

}
