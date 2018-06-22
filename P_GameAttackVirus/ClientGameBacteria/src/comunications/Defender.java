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
	private IObserver iObserver;
	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean stop;
	private int [] valuesInit = new int [4];
	private int [] valuesFriends;
	private boolean isValuesInit;
	public static final String PATH = "./configClient/";

	public Defender(String ip,int port,IObserver iObserver) throws IOException {
		this.connection = new Socket(ip, port);
		this.iObserver = iObserver;
		input = new DataInputStream(connection.getInputStream());
		output = new DataOutputStream(connection.getOutputStream());
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

	private void manageResponse(String response) throws IOException {
		switch (EResponse.valueOf(response)) {
		case INITGAME:
			receivedValuesInitGame();
			break;
		default:
			break;
		}
	}
	/**
	 * Recibe el archivo de valores iniciales enviado por el servidor
	 * @return
	 * @throws IOException
	 */
	private void receivedValuesInitGame() throws IOException {
		String[] valuesVectorPlayer = input.readUTF().split("/");
		String[] valuesVectorFriends = input.readUTF().split("/");
		for (int i = 0; i < valuesVectorPlayer.length; i++) {
			valuesInit[i] = Integer.parseInt(valuesVectorPlayer[i]);
		}
		this.valuesFriends = new int[valuesVectorFriends.length];
		for (int i = 0; i < valuesVectorFriends.length; i++) {
			this.valuesFriends[i] = Integer.parseInt(valuesVectorFriends[i]);
		}
		if (this.valuesInit.length == valuesVectorPlayer.length && this.valuesFriends.length == valuesVectorFriends.length) {
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

	public void setAreaGame(int[] areaGame) {
		this.valuesInit = areaGame;
	}

	public int[] getValuesInit() {
		return valuesInit;
	}

	public void setValuesInit(int[] valuesInit) {
		this.valuesInit = valuesInit;
	}

	public boolean isValuesInit() {
		return isValuesInit;
	}

	public void setValuesInit(boolean isValuesInit) {
		this.isValuesInit = isValuesInit;
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

	public int[] getValuesFriends() {
		return valuesFriends;
	}

	public void setValuesFriends(int[] valuesFriends) {
		this.valuesFriends = valuesFriends;
	}
	
}