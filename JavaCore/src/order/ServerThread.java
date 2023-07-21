package order;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerThread extends Thread {
	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		ObjectInputStream objectInputStream;
		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			Order order = (Order) objectInputStream.readObject();
			
			if (order != null) {
//				// Do something
				OrderDAO.getInstance().insert(order);
//				Message message = new Message("Nhan don dat hang thanh cong" );
//				ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//				objectOutputStream.writeObject(message);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
