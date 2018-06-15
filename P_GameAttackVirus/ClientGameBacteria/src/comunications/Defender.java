package comunications;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Defender extends Thread {

	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean stop;
	private int [] areaGame =  new int [2];

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
	}

	private void manageResponse(String response) throws IOException {
		switch (EResponse.valueOf(response)) {
		case INITGAME:
			consumeAreaGame();
			break;
		default:
			
			break;
		}

	}
	
	private void consumeAreaGame() throws IOException{
		this.areaGame[0] = input.readInt();
		this.areaGame[1] = input.readInt();
	}
	
	public void requestInitGame() throws IOException{
		output.writeUTF(ERequest.INITGAME.name());
	}

	public int[] getAreaGame() {
		return areaGame;
	}

	public void setAreaGame(int[] areaGame) {
		this.areaGame = areaGame;
	}

	
}