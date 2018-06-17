package comunications;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import persistence.FileManager;

public class Defender extends Thread {
	private int x;
	private int y;
	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean stop;
	private int [] valuesInit = new int [4];
	private boolean isValuesInit;

	public Defender(String ip,int port) throws IOException {
		this.connection = new Socket(ip, port);
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
		 if (connection != null) {
             try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
         }
	}

	private void manageResponse(String response) throws IOException {
		switch (EResponse.valueOf(response)) {
		case INITGAME:
			this.isValuesInit = receivedValuesInitGame();
			break;
		default:
			break;
		}
	}
	private boolean receivedValuesInitGame() throws IOException{
				File file = new File("./configClient/" + input.readUTF());
				readFile(file);
				String values = FileManager.readFile(file);
				String [] valuesVector = values.split("/");
				for (int i = 0; i < valuesVector.length; i++) {
					valuesInit[i] = Integer.parseInt(valuesVector[i]);
					System.out.println(valuesVector[i]);
				}
			boolean	flag = true;
		
		return flag;
	}
	
	public void readFile(File file) throws IOException {
		byte[] fileArray = new byte[input.readInt()];
		input.readFully(fileArray);
		writeFile(file, fileArray);
	}

	private void writeFile(File file, byte[] array) throws IOException {
		FileOutputStream fOutputStream = new FileOutputStream(file);
		fOutputStream.write(array);
		fOutputStream.close();
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
}