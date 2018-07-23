package view;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.Controller;

public class JFrameWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPListClients jpListClients;
	private JDInitPortServer jdInitPortServer;
	public static final String NAME_APP_SERVER = "Server Game";
	private ImageIcon icon = new ImageIcon(getClass().getResource("/server.png"));
	
	public JFrameWindow(Controller controller) {
		this.setIconImage(icon.getImage());
		this.setTitle(NAME_APP_SERVER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.jdInitPortServer = new JDInitPortServer(controller,this);
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		jpListClients = new JPListClients();
		this.setVisible(true);
		init();
	}

	private void init() {
		this.add(jpListClients);
	}
	
	public void init(ArrayList<JPClient> listJpClients) {
		 this.jpListClients.updateListClient(listJpClients);
	}
	
	public void updateNumClients(int cantClients){
		this.jpListClients.updateNumClients(cantClients);
	 }
	
	public void showJDialogPort(){
		this.jdInitPortServer.setVisible(true);
	}
	public void desableJDialog(){
		this.jdInitPortServer.dispose();
	}
	
	public String getPort(){
		return this.jdInitPortServer.getPort();
	}
	
	

}
