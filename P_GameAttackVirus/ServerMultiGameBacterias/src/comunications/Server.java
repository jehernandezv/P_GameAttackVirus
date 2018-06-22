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

	public void sentValuesInitGameClient(int idClientRequestInit) {
		if(this.connections.size() == 5){
		for (int i = 0; i < this.connections.size(); i++) {
				try {
					connections.get(i).sentInitValuesGame(genetateStringValuesInit(this.areaGame,posFigures[i].getX(),posFigures[i].getY()),
							generateValuesInitFriends(idClientRequestInit));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String genetateStringValuesInit(int [] areaGame,int x, int y){
		String values = areaGame[0] + "/" + areaGame[1] + "/" + x + "/" + y;
		return values;
	}
	
	public String generateValuesInitFriends(int idClientRequestInit){
		String friends = "";
		for (int i = 0; i < posFigures.length; i++) {
			if(this.posFigures[i].getIdPlayer() != idClientRequestInit){
				friends += this.posFigures[i].getX() + "/" + this.posFigures[i].getY() + "/";
				System.out.println(this.posFigures[i].getX() + "/" + this.posFigures[i].getY() + "/");
			}
		}
		return friends;
	}

}