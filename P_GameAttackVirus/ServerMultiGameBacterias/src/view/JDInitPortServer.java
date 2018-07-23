package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.Controller;
import controller.EAction;

public class JDInitPortServer extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private JTextField jTextFieldPort;
	private JLabel jLabelTitilePort;
	private JButton jButtonInitPort;
	public static final String TITLE_JDIALOG = "Ingrese el puerto del servidor";
	public static final String NAME_JBUTTON = "Accept";
	private ImageIcon icon = new ImageIcon(getClass().getResource("/check.png"));
	private ImageIcon icon1 = new ImageIcon(getClass().getResource("/check1.png"));
	
	
	public JDInitPortServer(Controller controller,JFrame jFrameWindow) {
		super(jFrameWindow,true);
		this.setSize(250, 150);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(3,1));
		this.jTextFieldPort = new JTextField();
		this.jLabelTitilePort = new JLabel(TITLE_JDIALOG);
		this.jButtonInitPort = new JButton(NAME_JBUTTON);
		this.setModal(false);
		this.jButtonInitPort.setActionCommand(EAction.ACCEPT_PORT.name());
		this.jButtonInitPort.addActionListener(controller);
		init();
	}

	private void init() {
		this.jTextFieldPort.setFont(new Font("Arial", Font.BOLD, 18));
		this.jButtonInitPort.setIcon(icon);
		this.jButtonInitPort.setRolloverIcon(icon1);
		this.jLabelTitilePort.setFont(new Font("Arial", Font.BOLD, 14));
		this.jLabelTitilePort.setHorizontalAlignment(WIDTH / 2);
		customJButton(jButtonInitPort);
		this.add(jLabelTitilePort);
		this.add(jTextFieldPort);
		this.add(jButtonInitPort);
	}
	
	public void customJButton(JButton button){
		button.setOpaque(true);
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFont(new Font("Arial", Font.BOLD, 16));
		button.setForeground(Color.BLACK);
	}
	
	public String getPort(){
		return this.jTextFieldPort.getText();
	}
	
	

}
