import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class PaymentPanel extends JFrame implements ActionListener, MouseListener{

	private JPanel contentPane;
	private JTextField KardNumber_1;
	private JTextField KardNumber_2;
	private JPasswordField KardNumber_3;
	private JPasswordField KardNumber_4;
	private JTextField year;
	private JTextField mom;
	private JTextField KardName;
	private JTextField textField;
	private Button btn1 , btn2;
	
	private MainFrame mainFrame;
	private MemberDTO member;


	/**
	 * Create the frame.
	 * @param member 
	 * @param mainFrame 
	 */
	public PaymentPanel () {
		

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\uACE0\uAC1D \uAC1C\uC778\uC815\uBCF4 \uC81C\uACF5 \uB3D9\uC758");
		rdbtnNewRadioButton.setBounds(127, 339, 165, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel_4_3_1 = new JLabel("CVC");
		lblNewLabel_4_3_1.setBounds(33, 270, 57, 23);
		contentPane.add(lblNewLabel_4_3_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(87, 271, 68, 21);
		contentPane.add(textField);
		
		KardName = new JTextField();
		KardName.setColumns(10);
		KardName.setBounds(87, 147, 68, 21);
		contentPane.add(KardName);
		
		JLabel lblNewLabel_4_3 = new JLabel("\uCE74\uB4DC\uC0AC");
		lblNewLabel_4_3.setBounds(33, 146, 57, 23);
		contentPane.add(lblNewLabel_4_3);
		
		JButton btn2 = new JButton("\uCDE8\uC18C");
		btn2.setBounds(226, 398, 102, 47);
		contentPane.add(btn2);
		
		JButton btn1 = new JButton("\uACB0\uC81C");
		btn1.setBounds(92, 398, 102, 47);
		contentPane.add(btn1);
		
		
		
		mom = new JTextField();
		mom.setColumns(10);
		mom.setBounds(167, 231, 68, 21);
		contentPane.add(mom);
		
		JLabel lblNewLabel_4_1_2 = new JLabel("/");
		lblNewLabel_4_1_2.setBounds(158, 231, 6, 23);
		contentPane.add(lblNewLabel_4_1_2);
		
		year = new JTextField();
		year.setColumns(10);
		year.setBounds(87, 232, 68, 21);
		contentPane.add(year);
		
		JLabel lblNewLabel_4_2 = new JLabel("\uC720\uD6A8\uAE30\uAC04");
		lblNewLabel_4_2.setBounds(33, 231, 57, 23);
		contentPane.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("-");
		lblNewLabel_4_1_1_1.setBounds(319, 192, 6, 23);
		contentPane.add(lblNewLabel_4_1_1_1);
		
		KardNumber_4 = new JPasswordField();
		KardNumber_4.setBounds(328, 192, 68, 21);
		contentPane.add(KardNumber_4);
		
		KardNumber_3 = new JPasswordField();
		KardNumber_3.setBounds(247, 192, 68, 21);
		contentPane.add(KardNumber_3);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("-");
		lblNewLabel_4_1_1.setBounds(238, 192, 6, 23);
		contentPane.add(lblNewLabel_4_1_1);
		
		KardNumber_2 = new JTextField();
		KardNumber_2.setColumns(10);
		KardNumber_2.setBounds(167, 192, 68, 21);
		contentPane.add(KardNumber_2);
		
		JLabel lblNewLabel_4_1 = new JLabel("-");
		lblNewLabel_4_1.setBounds(158, 192, 6, 23);
		contentPane.add(lblNewLabel_4_1);
		
		KardNumber_1 = new JTextField();
		KardNumber_1.setBounds(87, 193, 68, 21);
		contentPane.add(KardNumber_1);
		KardNumber_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\uCE74\uB4DC\uBC88\uD638");
		lblNewLabel_4.setBounds(33, 192, 57, 23);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("\uC2E0\uC6A9/\uCCB4\uD06C\uCE74\uB4DC\uC785\uB825");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(lblNewLabel_3.getFont().deriveFont(lblNewLabel_3.getFont().getStyle() | Font.BOLD, lblNewLabel_3.getFont().getSize() + 8f));
		lblNewLabel_3.setBounds(46, 81, 237, 47);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\GeonSu\\Desktop\\qeasd.PNG"));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(29, 67, 373, 417);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("\uC2E0\uC6A9\uCE74\uB4DC/\uCCB4\uD06C\uCE74\uB4DC \uACB0\uC81C");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(lblNewLabel.getFont().getStyle() | Font.BOLD, lblNewLabel.getFont().getSize() + 8f));
		lblNewLabel.setBounds(97, 10, 237, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\GeonSu\\Desktop\\search.pstatic.jpg"));
		lblNewLabel_1.setBounds(12, 10, 410, 496);
		contentPane.add(lblNewLabel_1);	
		btn1.addActionListener(this);
		
	btn1.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			
			PaymentDTO pd = new PaymentDTO();
			pd.setkardname(KardName.getText());
			pd.setkardnumner(KardNumber_1.getText()+" - "+KardNumber_2.getText()+" - "+KardNumber_3.getText()+" "+KardNumber_4.getText());
			pd.setyymm(year.getText()+"/"+mom.getText());
			pd.setcvc(textField.getText());
			
			if(e.getSource()==btn1) {
				JOptionPane.showMessageDialog(null, "결제가 완료되었습니다!");
				PaymentDAO dao = PaymentDAO.getInstance();
				dispose();
				}

			
		}
	});
			
		
	btn2.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==btn2){
				JOptionPane.showMessageDialog(null, "결제가 취소되었습니다!");
				dispose();
				}
			
		}
		
		
		
	});
		
		
		
	}
		
			


	



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	 {
			
			if(e.getSource()==btn1) {
			JOptionPane.showConfirmDialog(this, "결제가 완료되었습니다");
			dispose();
			}
			else if (e.getSource()==btn2){
			JOptionPane.showConfirmDialog(this, "결제가 취소되었습니다");
			dispose();
			}
		
			
		}
		
	}



	
}

	

