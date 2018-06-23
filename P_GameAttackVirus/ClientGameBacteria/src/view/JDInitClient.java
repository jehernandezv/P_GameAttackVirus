package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import controller.Controller;
import controller.EAction;

public class JDInitClient extends JDialog{

	private static final long serialVersionUID = 1L;
	private JButton jButtonAcceptInitClient;
	private JPInitValues jpInitValues;
	private ImageIcon icon = new ImageIcon(getClass().getResource("/check.png"));
	private ImageIcon icon1 = new ImageIcon(getClass().getResource("/check1.png"));
	private ImageIcon iconJDialog = new ImageIcon(getClass().getResource("/user.png"));
	public static final String NAME_BUTTON = "Accept";
	public static final String TITLE_JLABEL = "Start Game";
	public static final String TITLE_DIALOG = "Init Game";
	private JLabel jLabelTitleInit;
	
	public JDInitClient(Controller controller) {
		setSize(300, 350);
		this.setTitle(TITLE_DIALOG);
		this.setIconImage(iconJDialog.getImage());
		this.setModal(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		jLabelTitleInit = new JLabel();
		this.jButtonAcceptInitClient = new JButton(NAME_BUTTON);
		jButtonAcceptInitClient.addActionListener(controller);
		jButtonAcceptInitClient.setActionCommand(EAction.INIIGAME.name());
		this.jpInitValues = new JPInitValues(controller);
		this.setVisible(true);
		init();
	}

	private void init() {
		jLabelTitleInit.setFont(new Font("Arial", Font.BOLD,34));
		jLabelTitleInit.setText(TITLE_JLABEL);
		jLabelTitleInit.setHorizontalAlignment(WIDTH /2);
		customJButton(jButtonAcceptInitClient);
		jButtonAcceptInitClient.setIcon(icon);
		jButtonAcceptInitClient.setRolloverIcon(icon1);
		this.add(jLabelTitleInit, BorderLayout.NORTH);
		this.add(jpInitValues, BorderLayout.CENTER);
		this.add(jButtonAcceptInitClient,BorderLayout.SOUTH);
	}
	
	public ArrayList<String> getValuesInitClient(){
		return this.jpInitValues.getValuesInitClient();
	}
	
	public byte getSelecRadioButton(){
		return this.jpInitValues.getSelecRadioButton();
	}
	
	public void customJButton(JButton button){
		button.setOpaque(true);
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setForeground(Color.BLACK);
	}
}
