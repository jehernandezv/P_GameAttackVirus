package comunications;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

import persistence.FileManager;

public class ThreadSocket extends Thread implements IObservable{
	private IObserver iObserver;
	private File fileSent;
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
		String [] string = {""};
		FileManager.writeFile(string, this.idObservable);
		start();
	}

	public void run() {
		try{
		while (!stop) {
			String request;
				request = input.readUTF();
				if (request != null) {
					manageRequest(request);
				}
		}
		
		} catch (IOException e) {
			System.err.println(e.getMessage());
			this.stop = true;
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
	
	public void sentInitValuesGame(int [] areaGame,int x,int y) throws IOException{
		output.writeUTF(EResponse.INITGAME.name());
		this.fileSent = FileManager.writeFile(generateValuesInit(areaGame, x, y), this.idObservable);
		sendUsersFile(fileSent);
	}
	
	public void sendUsersFile(File file) {
		try {
			sendFile(file);
		} catch (IOException e) {
			Server.LOGGER.log(Level.WARNING, e.getMessage());
		}
	}

	private void sendFile(File file) throws IOException {
		byte[] array = new byte[(int) file.length()];
		readFileBytes(file, array);
		output.writeUTF(file.getName());
		output.writeInt(array.length);
		output.write(array);
}
	
	private void readFileBytes(File file, byte[] array) throws IOException {
		FileInputStream fInputStream = new FileInputStream(file);
		fInputStream.read(array);
		fInputStream.close();
}
	
	public String [] generateValuesInit(int [] areaGame, int x, int y){
		String [] values = new String[4];
		values[0] = String.valueOf(areaGame[0]);
		values[1] = String.valueOf(areaGame[1]);
		values[2] = String.valueOf(x);
		values[3] = String.valueOf(y);
		return values;
	}

	public IObserver getiObserver() {
		return iObserver;
	}

	public void setiObserver(IObserver iObserver) {
		this.iObserver = iObserver;
	}

	@Override
	public void addObserver(IObserver observer) {
		
	}

	@Override
	public void removeObserver() {
		
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