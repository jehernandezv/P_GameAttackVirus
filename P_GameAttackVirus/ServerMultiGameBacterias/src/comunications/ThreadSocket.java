package comunications;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class ThreadSocket extends Thread {

	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean stop;

	public ThreadSocket(Socket socket) throws IOException {
		this.connection = socket;
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		start();
	}

	public void run() {
		while (!stop) {
			String request;
			try {
				request = input.readUTF();
				if (request != null) {
					manageRequest(request);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void manageRequest(String request) throws IOException {
		Server.LOGGER.log(Level.INFO, "Request: " + connection.getInetAddress().getHostAddress() + " - " + request);
		switch (ERequest.valueOf(request)) {
		case INITGAME:
			sentAreaGame();
			break;
		default:
			break;
		}
		Server.LOGGER.log(Level.INFO, "Conexion con: " + connection.getInetAddress().getHostAddress() + " cerrada.");
	}
	
	private void sentAreaGame() throws IOException{
		output.writeUTF(EResponse.INITGAME.name());
		output.writeInt(800);
		output.writeInt(600);
	}

//	private void responseTimeService() throws IOException {
//		output.writeUTF(EResponse.TIME.toString());
//		output.writeUTF(LocalTime.now().format(DateTimeFormatter.ISO_TIME));
//	}
//
//	private void responseWordsService() throws IOException {
//		output.writeUTF(EResponse.WORDS.toString());
//		output.writeUTF("Hola mundo");
//	}
}