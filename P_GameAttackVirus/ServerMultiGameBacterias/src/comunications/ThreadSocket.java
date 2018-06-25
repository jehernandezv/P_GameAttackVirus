package comunications;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class ThreadSocket extends Thread implements IObservable{
	private String nameClient;
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
			Server.LOGGER.log(Level.INFO, "El cliente: " + this.nameClient + " se desconecto");
			this.stop = true;
		}
	}

	private void manageRequest(String request) throws IOException {
		Server.LOGGER.log(Level.INFO, "Request: " + connection.getInetAddress().getHostAddress() + " - " + request);
		switch (ERequest.valueOf(request)) {
		case INITGAME:
			this.iObserver.sentValuesInitGameClient(idObservable);
			break;
		case NAME_CLIENT:
			receivedNameClient();
			Server.LOGGER.log(Level.INFO, "Conexion con : " + nameClient);
			break;
		case BULLET:
			receivedBulletAtProcess();
			break;
		default:
			break;
		}
		Server.LOGGER.log(Level.INFO, "Conexion con: " + connection.getInetAddress().getHostAddress() + " cerrada.");
	}
	
	public void sendPosBullet(String bullets) throws IOException{
		output.writeUTF(EResponse.SENT_POS_BULLET.name());
		output.writeUTF(bullets);
	}
	
	private void receivedBulletAtProcess() throws IOException{
		String [] bullet = input.readUTF().split("/");
		iObserver.createBullet(Integer.parseInt(bullet[0]), Integer.parseInt(bullet[1]), this.idObservable);
 	}
	/**
	 * Este metodo se encarga de enviar el archivo de valores iniciales requerido al cliente
	 * @param areaGame
	 * @param x
	 * @param y
	 * @throws IOException
	 */
	public void sentInitValuesGame(String valuesPlayer,String PosFriends,String namesFriends) throws IOException{
		output.writeUTF(EResponse.INITGAME.name());
		output.writeUTF(valuesPlayer);
		output.writeUTF(PosFriends);
		output.writeUTF(namesFriends);
	}
	
	private void receivedNameClient() throws IOException{
		this.nameClient = input.readUTF();
		iObserver.createHeroGameServer(this.nameClient, (byte) this.idObservable);
	}
	
	public void sendUsersFile(File file) {
		try {
			sendFile(file);
		} catch (IOException e) {
			Server.LOGGER.log(Level.WARNING, e.getMessage());
		}
	}
	/**
	 * Haciendo uso del Dataoutput se envia los datos del archivo ya constituido
	 * @param file
	 * @throws IOException
	 */
	private void sendFile(File file) throws IOException {
		byte[] array = new byte[(int) file.length()];
		readBytesBuffer(file, array);
		output.writeUTF(file.getName());
		output.writeInt(array.length);
		output.write(array);
}
	/**
	 * Este metodo lee un archivo con los bytes del array que entra por parametro
	 * @param file
	 * @param array
	 * @throws IOException
	 */
	private void readBytesBuffer(File file, byte[] array) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(file);
		fileInputStream.read(array);
		fileInputStream.close();
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

	public String getNameClient() {
		return nameClient;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	
}