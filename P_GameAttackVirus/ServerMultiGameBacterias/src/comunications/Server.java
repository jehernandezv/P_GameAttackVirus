package comunications;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread implements IObserver {

	private ServerSocket server;
	private int port;
	private boolean stop;
	public final static Logger LOGGER = Logger.getGlobal();
	private ArrayList<ThreadSocket> connections;
	private PosFigures [] posFigures;
	private int [] areaGame = {800,600};
	
	public Server(int port) throws IOException {
		connections = new ArrayList<>();
		this.port = port;
		server = new ServerSocket(port);
		start();
		LOGGER.log(Level.INFO, "Servidor iniciado en puerto: " + this.port);
		this.posFigures = ManagerPosClients.generateNewPosClient(areaGame[0] - 10,areaGame[1] - 90,50);
		for (int i = 0; i < posFigures.length; i++) {
			System.out.println("x " + posFigures[i].getX()+ " y " + posFigures[i].getY());
		}
	}

	public void run() {
		while (!stop) {
			Socket connection;
			System.out.println("Cant Clientes: " + this.connections.size());
			try {
				connection = server.accept();
				ThreadSocket socket = new ThreadSocket(connection);
				socket.setiObserver(this);
				connections.add(socket);
				LOGGER.log(Level.INFO, "Conexion aceptada: " + connection.getInetAddress().getHostAddress());
			} catch (IOException e) {
				System.err.println(e.getMessage());
				this.stop = true;
			}
		}
	}

	public static void main(String[] args) {
		try {
			new Server(9000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sentValuesInitGameClient(int idClientRequestArea) {
		for (int i = 0; i < this.connections.size(); i++) {
			if(connections.get(i).getIdObservable() == idClientRequestArea){
				try {
					connections.get(i).sentInitValuesGame(genetateStringValuesInit((byte) i));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String genetateStringValuesInit(byte index){
		String values = "";
		switch (index) {
		case 0:
			values = areaGame[0]+ "/"+ areaGame[1] + "/" + this.posFigures[2].getX()+ "/"+ this.posFigures[2].getY();
			break;
		case 1:
			values = areaGame[0]+ "/"+ areaGame[1] + "/" + this.posFigures[1].getX()+ "/"+ this.posFigures[1].getY();
			break;
		case 2:
			values = areaGame[0]+ "/"+ areaGame[1] + "/" + this.posFigures[3].getX()+ "/"+ this.posFigures[3].getY();
			break;
		case 3:
			values = areaGame[0]+ "/"+ areaGame[1] + "/" + this.posFigures[0].getX()+ "/"+ this.posFigures[0].getY();
			break;
		case 4:
			values = areaGame[0]+ "/"+ areaGame[1] + "/" + this.posFigures[4].getX()+ "/"+ this.posFigures[4].getY();
			break;
		}
		return values;
	}

	public void sentPosClien(int idClientRequestPos) {
		
	}
}