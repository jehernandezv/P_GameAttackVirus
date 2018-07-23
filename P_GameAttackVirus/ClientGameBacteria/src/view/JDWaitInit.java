package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class JDWaitInit extends JDialog{

	private static final long serialVersionUID = 1L;
	private JLabel jLabelTitleWait;
	private JLabel jLabelMessageProgress;
	public static final String TITLE_MESSAGE_JD = "Wait Moment";
	public static final String TITLE_JD = "WAIT PLAYERS";
	public static final String MESSAGE_LOADING = "Loading";
	
	public JDWaitInit() {
		this.setLayout(new BorderLayout());
		this.setSize(400, 600);
		this.setTitle(TITLE_JD);
		this.setLocationRelativeTo(null);
		this.jLabelMessageProgress = new JLabel(MESSAGE_LOADING);
		this.jLabelTitleWait = new JLabel(TITLE_MESSAGE_JD);
		refactorJlabel(jLabelMessageProgress);
		refactorJlabel(jLabelTitleWait);
		init();
	}

	private void init() {
		this.add(jLabelTitleWait,BorderLayout.NORTH);
		this.add(jLabelMessageProgress, BorderLayout.CENTER);
	}
	
	private void refactorJlabel(JLabel jLabel){
		jLabel.setOpaque(true);
		jLabel.setBackground(Color.decode("#FA5858"));
		jLabel.setForeground(Color.WHITE);
		jLabel.setFont(new Font("Arial", Font.BOLD, 32));
		jLabel.setHorizontalAlignment(WIDTH / 2);
	}

}
