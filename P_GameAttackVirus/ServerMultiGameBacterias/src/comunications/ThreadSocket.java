package comunications;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class ThreadSocket extends Thread implements IObservable{
	private IObserver iObserver;
	public int idObservable;
	private static int cont;
	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean stop;

	public ThreadSocket(Socket socket) throws IOException {
		this.connection = socket;
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		idObservable = ++cont;
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
				System.err.println(e.getMessage());
				this.stop = true;
			}
		}
	}

	private void manageRequest(String request) throws IOException {
		Server.LOGGER.log(Level.INFO, "Request: " + connection.getInetAddress().getHostAddress() + " - " + request);
		switch (ERequest.valueOf(request)) {
		case INITGAME:
			this.iObserver.sentAreaGamePlayers(idObservable);
			break;
		default:
			break;
		}
		Server.LOGGER.log(Level.INFO, "Conexion con: " + connection.getInetAddress().getHostAddress() + " cerrada.");
	}
	
	public void sentAreaGame(int [] areaGame) throws IOException{
		output.writeUTF(EResponse.INITGAME.name());
		output.writeInt(areaGame[0]);
		output.writeInt(areaGame[1]);
	}

	public IObserver getiObserver() {
		return iObserver;
	}

	public void setiObserver(IObserver iObserver) {
		this.iObserver = iObserver;
	}

	@Override
	public void addObserver(IObserver observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver() {
		// TODO Auto-generated method stub
		
	}

	public int getIdObservable() {
		return idObservable;
	}

	public void setIdObservable(int idObservable) {
		this.idObservable = idObservable;
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