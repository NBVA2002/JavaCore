package chat_client_server;

import java.io.DataInputStream;

public class ThreadInput extends Thread{
	DataInputStream dataInputStream;

	public ThreadInput(DataInputStream dataInputStream) {
		this.dataInputStream = dataInputStream;
	}

}
