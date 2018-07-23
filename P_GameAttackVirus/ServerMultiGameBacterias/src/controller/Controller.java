package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import view.JFrameWindow;
import view.JPClient;
import comunications.Server;


public class Controller implements ActionListener{
	private Server server;
	private JFrameWindow jFrameWindow;
	private Timer timerRefresh;
	
	public Controller() throws IOException {
		this.jFrameWindow = new JFrameWindow(this);
		jFrameWindow.showJDialogPort();
	}
	
	public void refresh(){
		ArrayList<JPClient> jpClients = new ArrayList<JPClient>();
		this.timerRefresh = new Timer(1000, new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < server.getConnections().size(); i++) {
					if(!server.getConnections().get(i).isStop()){
					jpClients.add(new JPClient(server.getConnections().get(i).getConnection().getInetAddress().getHostAddress()
							, server.getConnections().get(i).getNameClient()));
					}
				}
				jFrameWindow.init(jpClients);
				jFrameWindow.updateNumClients(getNumClientsActive());
				jFrameWindow.revalidate();
				jpClients.clear();
			}
		});
		this.timerRefresh.start();
	}
	
	public void initServer() throws IOException{
		String cant = JOptionPane.showInputDialog("Por favor ingrese el numero de clientes a permitir en la partida");
		this.server = new Server(Integer.parseInt(jFrameWindow.getPort()),Byte.parseByte(cant));
		jFrameWindow.desableJDialog();
		this.refresh();
	}
	
	public int getNumClientsActive(){
		int cant = 0;
		for (int i = 0; i < server.getConnections().size(); i++) {
			if(!server.getConnections().get(i).isStop()){
				cant ++;
			}
		}
		return cant;
	}

	public static void main(String[] args) {
		try {
			new Controller();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		switch (EAction.valueOf(e.getActionCommand())) {
		case ACCEPT_PORT:
			try {
				initServer();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			break;

		default:
			break;
		}
	}

}
