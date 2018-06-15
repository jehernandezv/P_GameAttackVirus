package comunications;

import java.awt.Rectangle;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Defender extends Thread {

	private Socket connection;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean stop;
	private Rectangle areaGame;

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
			System.out.println("entro");
			this.areaGame = new Rectangle(consumeAreaGame());
			System.out.println("recibido: " + this.areaGame.height + "  ====  " + this.areaGame.width);
			break;
		default:
			
			break;
		}

	}
	
	private Rectangle consumeAreaGame() throws IOException{
		int width = input.readInt();
		int heigth = input.readInt();
		return new Rectangle(width, heigth);
	}
	
	public void requestInitGame() throws IOException{
		output.writeUTF(ERequest.INITGAME.name());
	}

	public Rectangle getAreaGame() {
		return areaGame;
	}

	public void setAreaGame(Rectangle areaGame) {
		this.areaGame = areaGame;
	}
	
	

//	public static void main(String[] args) {
//		try {
//			Defender defender = new Defender();
//			defender.requestMessage("hola",1);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}