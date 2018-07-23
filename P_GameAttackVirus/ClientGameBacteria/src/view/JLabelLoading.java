package view;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class JLabelLoading extends JLabel{
	private static final long serialVersionUID = 1L;
	private ImageIcon imageIconLoading = new ImageIcon(this.getClass().getResource("/loading.gif"));
	
	public JLabelLoading() {
		this.setSize(imageIconLoading.getIconHeight(),imageIconLoading.getIconWidth());
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		
	}
}
