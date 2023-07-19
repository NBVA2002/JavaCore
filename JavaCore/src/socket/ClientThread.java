package socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

public class ClientThread extends Thread {
	private Socket socket;

	public ClientThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		PrintStream printStream;
		try {
			printStream = new PrintStream(socket.getOutputStream());
			printStream.println(randomMessage());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String randomMessage() {
		Random random = new Random();

        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        int length = 10;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charset.length());
            char randomChar = charset.charAt(randomIndex);
            sb.append(randomChar);
        }
        
        return sb.toString();
	}
}
