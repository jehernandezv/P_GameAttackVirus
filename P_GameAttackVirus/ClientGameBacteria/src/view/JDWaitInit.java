package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class JDWaitInit extends JDialog{

	private static final long serialVersionUID = 1L;
	private JLabel jLabelTitleWait,jLabelMessageProgress,jLabelProgressPlayers;
	public static final String TITLE_MESSAGE_JD = "Wait Moment";
	public static final String TITLE_JD = "WAIT PLAYERS";
	public static final String MESSAGE_CANT_PLAYERS = "Numero de jugadores conectados: ";
	public ImageIcon imageIconLoading = new ImageIcon(this.getClass().getResource("/loading.gif"));
	private byte cantPlayersAtWait;
	
	public JDWaitInit() {
		this.setLayout(new BorderLayout());
		this.setSize(imageIconLoading.getIconHeight(),imageIconLoading.getIconWidth());
		this.setTitle(TITLE_JD);
		this.setLocationRelativeTo(null);
		this.jLabelMessageProgress = new JLabel();
		this.jLabelTitleWait = new JLabel(TITLE_MESSAGE_JD);
		jLabelProgressPlayers = new JLabel(MESSAGE_CANT_PLAYERS);
		refactorJlabel(jLabelTitleWait);
		refactorJlabelSouth(jLabelProgressPlayers);
		this.jLabelMessageProgress.setIcon(imageIconLoading);
		init();
	}

	private void init() {
		this.add(jLabelTitleWait,BorderLayout.NORTH);
		this.add(jLabelMessageProgress, BorderLayout.CENTER);
		this.add(jLabelProgressPlayers, BorderLayout.SOUTH);
	}
	
	private void refactorJlabel(JLabel jLabel){
		jLabel.setOpaque(true);
		jLabel.setBackground(Color.decode("#FA5858"));
		jLabel.setForeground(Color.WHITE);
		jLabel.setFont(new Font("Arial", Font.BOLD, 32));
		jLabel.setHorizontalAlignment(WIDTH / 2);
	}
	
	private void refactorJlabelSouth(JLabel jLabel){
		jLabel.setOpaque(true);
		jLabel.setBackground(Color.decode("#FA5858"));
		jLabel.setForeground(Color.WHITE);
		jLabel.setFont(new Font("Arial", Font.BOLD, 12));
		jLabel.setHorizontalAlignment(WIDTH / 2);
	}
	
	public void updatePlayersConnection(byte cant){
		this.jLabelProgressPlayers.setText(MESSAGE_CANT_PLAYERS + cant + " de " + cantPlayersAtWait);
	}

	public void setCantPlayersAtWait(byte cantPlayersAtWait) {
		this.cantPlayersAtWait = cantPlayersAtWait;
		updatePlayersConnection((byte)0);
	}

}
