package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPListClients extends JPanel{
	private static final long serialVersionUID = 1L;
	ArrayList<JPClient> listJpClients;
	private JPanel jPanelConstainsList;
	private JLabel jLabelTitleClient;
	private static final String TITLEJPANEL = "IP Client   |   Name Client";
	private static final String CONECTION = "Number Of Clients: ";
 	private JLabel jLabelSouth;
	
	public JPListClients() {
		jPanelConstainsList = new JPanel();
		jLabelTitleClient = new JLabel(TITLEJPANEL);
		this.jLabelSouth = new JLabel(CONECTION);
		jPanelConstainsList.setLayout(new BoxLayout(jPanelConstainsList, BoxLayout.Y_AXIS));
		this.setLayout(new BorderLayout());
		listJpClients = new ArrayList<JPClient>();
		
	}

	 public void updateListClient(ArrayList<JPClient> listJpClients) {
		refactorJlabel(jLabelTitleClient,30);
		refactorJlabel(jLabelSouth, 20);
		 
		 this.listJpClients = listJpClients;
		 this.jPanelConstainsList.removeAll();
		 for (int i = 0; i < this.listJpClients.size(); i++) {
			 this.jPanelConstainsList.add(this.listJpClients.get(i));
		}
		 this.add(jLabelTitleClient, BorderLayout.NORTH);
		 this.add(jPanelConstainsList,BorderLayout.CENTER);
		 this.add(jLabelSouth, BorderLayout.SOUTH);
	}
	 
	 public void updateNumClients(int cantClients){
		 this.jLabelSouth.setText(CONECTION + cantClients);
	 }
	 
	 public void refactorJlabel(JLabel jLabel,int sizeString){
			jLabel.setOpaque(true);
			jLabel.setBackground(Color.decode("#FE2E2E"));
			jLabel.setForeground(Color.WHITE);
			jLabel.setFont(new Font("Arial", Font.BOLD, sizeString));
			jLabel.setHorizontalAlignment(WIDTH / 2);
		}
}
