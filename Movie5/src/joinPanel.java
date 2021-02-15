
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;



public class joinPanel extends JPanel implements ActionListener {
	JPanel pCenter, pNorth, pEast, pWest, pSouth;
	JLabel lblId, lblPwd, lblAddr, lblName, lblPhone;
	JTextField tfId, tfAddr, tfName, tfPhone;
	JPasswordField pfPwd;
	JButton btnJoin, btnCancel;
	MainFrame mainFrame;
	MemberDTO member;

	public joinPanel(MainFrame mainFrame, MemberDTO member) {
		this.member = member;
		this.mainFrame = mainFrame;
		setLayout(new BorderLayout());
		add(pCenter = new JPanel(), BorderLayout.CENTER);
		add(pWest = new JPanel(), BorderLayout.WEST);
		FlowLayout flowLayout = (FlowLayout) pWest.getLayout();
		flowLayout.setVgap(50);
		flowLayout.setHgap(50);
		add(pEast = new JPanel(), BorderLayout.EAST);
		FlowLayout flowLayout_1 = (FlowLayout) pEast.getLayout();
		flowLayout_1.setVgap(50);
		flowLayout_1.setHgap(50);
		add(pNorth = new JPanel(), BorderLayout.NORTH);
		FlowLayout flowLayout_2 = (FlowLayout) pNorth.getLayout();
		flowLayout_2.setVgap(10);
		flowLayout_2.setHgap(10);
		add(pSouth = new JPanel(), BorderLayout.SOUTH);
		FlowLayout flowLayout_3 = (FlowLayout) pSouth.getLayout();
		flowLayout_3.setVgap(50);
		flowLayout_3.setHgap(50);
		// north
		pNorth.setBackground(Color.BLACK);
		JLabel lblTitle = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lblTitle.setFont(new Font("Title", Font.BOLD, 40));
		lblTitle.setForeground(Color.WHITE);
		pNorth.add(lblTitle);
		// south
		pSouth.setBackground(Color.WHITE);
		pSouth.add(btnJoin = new JButton("회원가입"));
		pSouth.add(btnCancel = new JButton("취소"));
		btnJoin.addActionListener(this);
		btnCancel.addActionListener(this);
		// west
		pWest.setBackground(Color.BLACK);
		// east
		pEast.setBackground(Color.BLACK);
		// center 
		GridLayout gl_pCenter = new GridLayout(10, 10);
		gl_pCenter.setVgap(20);
		gl_pCenter.setHgap(20);
		pCenter.setLayout(gl_pCenter);
		pCenter.add(lblId = new JLabel("아이디"));
		lblId.setDisplayedMnemonic('5');
		pCenter.add(tfId = new JTextField(10));
		pCenter.add(lblPwd = new JLabel("비밀번호"));
		pCenter.add(pfPwd = new JPasswordField(10));
		pCenter.add(lblName = new JLabel("이름"));
		pCenter.add(tfName = new JTextField(10));
		pCenter.add(lblPhone = new JLabel("전화번호"));
		pCenter.add(tfPhone = new JTextField(15));
		pCenter.add(lblAddr = new JLabel("주소"));
		pCenter.add(tfAddr = new JTextField(50));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnJoin) {
			MemberDTO join = new MemberDTO();
			MemberDAO memberDAO = new MemberDAO();
			join.setId(tfId.getText());
			join.setPwd(pfPwd.getText());
			join.setName(tfName.getText());
			join.setPhone(tfPhone.getText());
			join.setAddr(tfAddr.getText());
			boolean ok = memberDAO.joinMember(join);
			if (ok) {
				JOptionPane.showMessageDialog(this, "회원가입 되었습니다.");
				mainFrame.remove(this);
				MainPanel p = new MainPanel(mainFrame, member);
				mainFrame.getContentPane().add(p);
				mainFrame.invalidate();
				mainFrame.validate();
				mainFrame.repaint();
			} else {
				JOptionPane.showMessageDialog(this, "회원정보를 잘못 입력하셨습니다.");
			}
		} else if (e.getSource() == btnCancel) {
			mainFrame.remove(this);
			MainPanel p = new MainPanel(mainFrame, member);
			mainFrame.getContentPane().add(p);
			mainFrame.invalidate();
			mainFrame.validate();
			mainFrame.repaint();
		}
	}
}
