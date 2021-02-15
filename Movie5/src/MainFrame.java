

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class MainFrame extends JFrame {
//	PaymentPanel join=new PaymentPanel();

	public MainFrame() {
		MemberDTO member = new MemberDTO();
		member.setLogin("logOut");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Movei Reservation Program");
		MainPanel p = new MainPanel(this, member);
		add(p);
		setSize(1500, 1000);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();
//		new PaymentPanel();
	}
}
