package comunications;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Bullet;
import model.GameServer;
import model.Hero;

public class Server extends Thread implements IObserver {

	private ServerSocket server;
	private int port;
	private boolean stop;
	public final static Logger LOGGER = Logger.getGlobal();
	private ArrayList<ThreadSocket> connections;
	private PosFigures [] posFigures;
	private int [] areaGame = {800,600};
	private GameServer gameServer;
	private static final byte LIMITPLAYERS = 3;
	private static final String SEPARATOR_ONE = "/";
	private static final String SEPARATOR_TWO = ";";
	
	public Server(int port) throws IOException {
		connections = new ArrayList<>();
		this.port = port;
		server = new ServerSocket(port);
		start();
		LOGGER.log(Level.INFO, "Servidor iniciado en puerto: " + this.port);
		this.posFigures = ManagerPosClients.generateNewPosClient(areaGame[0] - 10,areaGame[1] - 90,50);
		this.gameServer = new GameServer(areaGame,this);
		this.gameServer.start();
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
		if(this.connections.size() == LIMITPLAYERS){
		for (int i = 0; i < this.connections.size(); i++) {
				try {
					connections.get(i).sentInitValuesGame(generateStringValuesInit(this.areaGame,posFigures[i].getX()
							,posFigures[i].getY()),generateValuesInitFriends(connections.get(i).getIdObservable())
							,generateNamesFriends(connections.get(i).getIdObservable()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void createHeroGameServer(String nameHero, byte idClientHero) {
		gameServer.addHero(new Hero(posFigures[idClientHero - 1].getX(), posFigures[idClientHero - 1].getY(), nameHero, idClientHero));
		
	}
	public String generateStringValuesInit(int [] areaGame,int x, int y){
		return areaGame[0] + SEPARATOR_ONE + areaGame[1] + SEPARATOR_ONE + x + SEPARATOR_ONE + y;
	}
	
	public String generateValuesInitFriends(int idClientRequestInit){
		String friends = "";
		for (int i = 0; i < this.connections.size(); i++) {
			if(this.posFigures[i].getIdPlayer() != idClientRequestInit){
				friends += this.posFigures[i].getX() + SEPARATOR_ONE + this.posFigures[i].getY() + SEPARATOR_ONE;
			}
		}
		return friends;
	}
	
	public String generateNamesFriends(int idClientRequestInit){
		String names = "";
		for (int i = 0; i < this.connections.size(); i++) {
			if(connections.get(i).getIdObservable() != idClientRequestInit){
				names += connections.get(i).getNameClient() + SEPARATOR_ONE;
			}
		}
			return names;
	}

	@Override
	public void createBullet(int x, int y, int idClientshot) {
		//Agrego el nuevo disparo
		for (int i = 0; i < gameServer.getListHero().size(); i++) {
			if(gameServer.getListHero().get(i).getIdHero() == idClientshot){
				Bullet bullet = new Bullet(Math.atan2(y - posFigures[i].getY(),x - posFigures[i].getX()), posFigures[i].getX(),
						posFigures[i].getY());
				gameServer.getListHero().get(i).getListBullet().add(bullet);
			}
		}
	}

	@Override
	public void updateBulletsUsers() {
		//Envio a los jugadores las posiciones de los disparos
		for (int i = 0; i < connections.size(); i++) {
			try {
				if(connections.get(i).isStop() != true){
				connections.get(i).sendPosBullet(refactorBullets());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public String refactorBullets(){
		String values = "";
		for (Iterator<?> iterator = gameServer.getListHero().iterator(); iterator.hasNext();) {
			Hero hero = (Hero) iterator.next();
			for (Iterator<?> iterator2 = hero.getListBullet().iterator(); iterator2.hasNext();) {
				Bullet bullet = (Bullet) iterator2.next();
				values += bullet.getX() + SEPARATOR_TWO + bullet.getY() + SEPARATOR_TWO + bullet.getDirection() + SEPARATOR_ONE;
			}
		}
		return values;
		
	}


}