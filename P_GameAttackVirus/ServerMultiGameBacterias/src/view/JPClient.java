package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPClient extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabelIp;
	private JLabel jLabelNameClient;
	
	public JPClient(String ip,String nameClient) {
		this.setLayout(new GridLayout(1,2));
		jLabelIp = new JLabel(ip);
		jLabelNameClient = new JLabel(nameClient);
		init();
	}

	private void init() {
		refactorJlabel(jLabelIp);
		refactorJlabel(jLabelNameClient);
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
		
		this.add(jLabelIp);
		this.add(jLabelNameClient);
	}
	
	public void refactorJlabel(JLabel jLabel){
		jLabel.setOpaque(true);
		jLabel.setBackground(Color.decode("#FA5858"));
		jLabel.setForeground(Color.WHITE);
		jLabel.setFont(new Font("Arial", Font.BOLD, 32));
		jLabel.setHorizontalAlignment(WIDTH / 2);
	}

}
