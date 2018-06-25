package comunications;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import controller.IObservable;
import controller.IObserver;

public class Defender extends Thread implements IObservable{
	private String nameClient;
	private IObserver iObserver;
	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean stop;
	private int [] valuesInit;
	private int [] PosFriends;
	private String [] namesFriends;
	private boolean isValuesInit;
	public static final String PATH = "./configClient/";

	public Defender(String ip,int port,IObserver iObserver,String nameClient) throws IOException {
		this.connection = new Socket(ip, port);
		this.iObserver = iObserver;
		this.nameClient = nameClient;
		input = new DataInputStream(connection.getInputStream());
		output = new DataOutputStream(connection.getOutputStream());
		sentName();
		start();
	}

	public void run() {
		while (!stop) {
			String response;
			try {
				response = input.readUTF();
				if (response != null) {
					manageResponse(response);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
				this.stop = true;
			}
		}
	}
	
	public void sendBulletServer(int x,int y) throws IOException{
		output.writeUTF(ERequest.BULLET.name());
		output.writeUTF(x+"/"+y);
	}

	private void sentName() throws IOException{
		output.writeUTF(ERequest.NAME_CLIENT.name());
		output.writeUTF(this.nameClient);
	}
	private void manageResponse(String response) throws IOException {
		switch (EResponse.valueOf(response)) {
		case INITGAME:
			receivedValuesInitGame();
			break;
		case SENT_POS_BULLET:
			receivedPosBullets();
			break;
		default:
			break;
		}
	}
	
	
	private void receivedPosBullets() throws IOException{
		iObserver.updateBullets(input.readUTF());
	}
	/**
	 * Recibe el archivo de valores iniciales enviado por el servidor
	 * @return
	 * @throws IOException
	 */
	private void receivedValuesInitGame() throws IOException {
		String[] valuesVectorPlayer = input.readUTF().split("/");
		String[] valuesVectorFriends = input.readUTF().split("/");
		String[] namesFriends = input.readUTF().split("/");
		//Parametros del juego, y pos del jugador principal
		this.valuesInit = new int [valuesVectorPlayer.length];
		for (int i = 0; i < valuesVectorPlayer.length; i++) {
			valuesInit[i] = Integer.parseInt(valuesVectorPlayer[i]);
		}
		//posiciones de los amigos en el juego
		this.PosFriends = new int[valuesVectorFriends.length];
		for (int i = 0; i < valuesVectorFriends.length; i++) {
				this.PosFriends[i] = Integer.parseInt(valuesVectorFriends[i]);
		}
		//nombres de los amigos del jugador principal
		this.namesFriends = new String [namesFriends.length];
		for (int i = 0; i < namesFriends.length; i++) {
			this.namesFriends[i] = namesFriends[i];
		}
		
		if (this.valuesInit.length == valuesVectorPlayer.length
				&& this.PosFriends.length == valuesVectorFriends.length
				&& this.namesFriends.length == namesFriends.length) {
			iObserver.isSendValuesInit();
		}
	}
	
	/**
	 * Este metodo saca del buffer los bytes recibidos y lo envia a un metodo para que sean escritos en un archivo
	 * @param file
	 * @throws IOException
	 */
	public void readByteBuffer(File file) throws IOException {
		byte[] fileArray = new byte[input.readInt()];
		input.readFully(fileArray);
		writeFile(file, fileArray);
	}
	/**
	 * Este metodo escribe un archivo con los bytes del array que entra por parametro
	 * @param file
	 * @param array
	 * @throws IOException
	 */
	private void writeFile(File file, byte[] array) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(array);
		fileOutputStream.close();
}
	
	public void requestInitGame() throws IOException{
		output.writeUTF(ERequest.INITGAME.name());
	}

	public int[] getAreaGame() {
		return valuesInit;
	}

	public int[] getValuesInit() {
		return valuesInit;
	}
	
	public boolean isValuesInit() {
		return isValuesInit;
	}

	public IObserver getiObserver() {
		return iObserver;
	}

	@Override
	public void addObserver(IObserver observer) {
		
	}

	@Override
	public void removeObserver() {
		
	}

	public int[] getValuesFriends() {
		return PosFriends;
	}
	
	public String getNameClient() {
		return nameClient;
	}

	public String[] getNamesFriends() {
		return namesFriends;
	}
}