package view;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.Controller;

public class JPInitValues extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel jLabelTitleNameClient,jLabelTitlePort;
	private JTextField jTextFieldNameClient,jTextFieldPort,jTextFieldIP;
	private JPanel jPanelCheckIP;
	private JRadioButton jRadioButtonTrue,jRadioButtonFalse;
	
	
	public JPInitValues(Controller controller) {
			instanceComponents(controller);
			this.setLayout(new GridLayout(6,1));
			init();
	}
	
	private void instanceComponents(Controller controller){
		this.jPanelCheckIP = new JPanel();
		customCheckJPanel(jPanelCheckIP,controller);
		this.jLabelTitleNameClient = new JLabel("User Name");
		this.jLabelTitlePort = new JLabel("Port");
		this.jTextFieldNameClient = new JTextField();
		this.jTextFieldPort = new JTextField();
		this.jTextFieldIP = new JTextField();
		customJTexfield(jTextFieldNameClient);
		customJTexfield(jTextFieldPort);
		customJTexfield(jTextFieldIP);
		this.jTextFieldIP.setEnabled(false);
		refactorJlabel(jLabelTitleNameClient);
		refactorJlabel(jLabelTitlePort);
		
	}

	private void init() {
		this.add(jLabelTitleNameClient);
		this.add(jTextFieldNameClient);
		this.add(jLabelTitlePort);
		this.add(jTextFieldPort);
		this.add(jPanelCheckIP);
		this.add(jTextFieldIP);
	}
	
	private void customCheckJPanel(JPanel jPanel,Controller controller){
		jPanel.setLayout(new FlowLayout());
		jPanel.setBackground(Color.decode("#FA5858"));
		jRadioButtonTrue = new JRadioButton();
		jRadioButtonFalse = new JRadioButton();
		jRadioButtonTrue.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				jTextFieldIP.setEnabled(true);
				jTextFieldIP.setEditable(true);
			}
		});
		jRadioButtonFalse.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				jTextFieldIP.setEnabled(false);
				jTextFieldIP.setEditable(false);
			}
		});
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(jRadioButtonTrue);
		buttonGroup.add(jRadioButtonFalse);
		JLabel jLabelButtonTrue = new JLabel("Ip");
		JLabel jLabelButtonFalse = new JLabel("Local");
		refactorJlabel(jLabelButtonFalse);
		refactorJlabel(jLabelButtonTrue);
		jPanel.add(jLabelButtonTrue);
		jPanel.add(jRadioButtonTrue);
		jPanel.add(jLabelButtonFalse);
		jPanel.add(jRadioButtonFalse);
		
	}
	
	private void refactorJlabel(JLabel jLabel){
		jLabel.setOpaque(true);
		jLabel.setBackground(Color.decode("#FA5858"));
		jLabel.setForeground(Color.WHITE);
		jLabel.setFont(new Font("Arial", Font.BOLD, 32));
		jLabel.setHorizontalAlignment(WIDTH / 2);
	}
	
	public ArrayList<String> getValuesInitClient(){
		ArrayList<String> values = new ArrayList<String>();
		values.add(jTextFieldNameClient.getText());
		values.add(jTextFieldPort.getText());
		values.add(jTextFieldIP.getText());
		return values;
	}
	
	public byte getSelecRadioButton(){
		return (this.jRadioButtonTrue.isSelected())? (byte)1: 2;
	}
	public void customJTexfield(JTextField jTextField){
		jTextField.setBackground(Color.decode("#E9EBEE"));
		jTextField.setFont(new Font("Arial", Font.BOLD, 18));
		jTextField.setHorizontalAlignment(WIDTH / 2);;
	}
}
