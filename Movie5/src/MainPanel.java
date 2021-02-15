
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainPanel extends JPanel implements ActionListener {
	private Image back;
	ImageIcon icon;
	
	MainFrame mainFrame;
	JPanel pNorth, pCenter;
	JButton btnHit, btnReservation, btnJoin, btnLogin, btnLogout;
	int[] iArr = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 };
	String[] sArr = { null, null, null, null, null, null, null, null, null, null, null,"예매조회","영화예매","회원가입", null, null, null,"로그인",null, null,
			 null, null, null,  null, null };
	String isLogin = "logOut";
	JTextField tfId;
	JPasswordField pfPwd;
	MemberDTO member;
	static final int pCnt = 25;
	
	public void paintComponent(Graphics g) {
		Dimension d = getSize();
		ImageIcon Image = new ImageIcon("C:\\Users\\GeonSu\\Desktop\\java\\java1.png");
		g.drawImage(Image.getImage(),0,0, d.width, d.height, null);
	}

	public MainPanel(MainFrame mainFrame, MemberDTO member) {

		back = Toolkit.getDefaultToolkit().getImage("C:\\Users\\GeonSu\\Desktop\\java\\java1.png");
		this.member = member;
		this.mainFrame = mainFrame;
		isLogin = member.getLogin();
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(600);
		borderLayout.setHgap(700);
		setLayout(borderLayout);
		add(pNorth = new JPanel(), BorderLayout.NORTH);
		add(pCenter = new JPanel(), BorderLayout.CENTER);
		// north
		pNorth.setLayout(new GridLayout(2, 1));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setLayout(new FlowLayout(5));
		p2.setLayout(new FlowLayout(5));
		p1.setBackground(new Color(0, 0, 0));
		p2.setBackground(new Color(0, 0, 0));
		JLabel lblTitle = new JLabel("                                            Movie Reservation Program");
		lblTitle.setFont(new Font("Main", Font.BOLD, 40));
		lblTitle.setForeground(Color.WHITE);
		p1.add(lblTitle);
		if (isLogin.equals("logIn")) {
			// 로그인중일 때 로그아웃 버튼 뜨기
			btnLogout = new JButton("로그아웃");
			btnLogout.addActionListener(this);
			p2.add(btnLogout);
		}
		pNorth.add(p1);
		pNorth.add(p2);
		// center
		
//		pCenter.setBackground();
		pCenter.setLayout(new GridLayout(5, 5, 10, 10));
		for (int i = 0; i < pCnt; i++) {

			JPanel p = new JPanel();
			p.paint(getGraphics());
			ImageIcon Image = new ImageIcon("C:\\Users\\GeonSu\\Desktop\\new\\java.jpg");
			p.paintComponents(getGraphics());
			if (sArr[i] == null) {
				pCenter.paintComponents(getGraphics());
				pCenter.add(p);
			} else if (sArr[i].equals("로그인")) {
				if (isLogin.equals("logIn")) {

					// 로그인 됐으면 로그인 창 안뜸
					pCenter.add(p);
				} else {
					// 로그인 안됐으면 로그인 창
					p.setLayout(new BorderLayout());
					JPanel pCenter = new JPanel();
					JPanel pEast = new JPanel();
					tfId = new JTextField(10);
					pfPwd = new JPasswordField(10);
					p.add(pCenter, BorderLayout.CENTER);
					p.add(pEast, BorderLayout.EAST);
					pEast.setLayout(new BorderLayout());

					btnLogin = new JButton("로그인");
					btnLogin.setBackground(Color.BLACK);

					pEast.add(btnLogin);
					pCenter.setLayout(new GridLayout(2, 1));
					pCenter.add(tfId);
					pCenter.add(pfPwd);
					this.pCenter.add(p);
					btnLogin.addActionListener(this);
				}
			} else if (sArr[i].equals("예매조회")) {
				btnHit = new JButton("예매조회");
				btnHit.setBackground(Color.BLACK);
				pCenter.add(btnHit);
				btnHit.addActionListener(this);
			} else if (sArr[i].equals("영화예매")) {
				btnReservation = new JButton("영화예매");
				btnReservation.setBackground(Color.BLACK);
				pCenter.add(btnReservation);
				btnReservation.addActionListener(this);
			} else if (sArr[i].equals("회원가입")) {
				btnJoin = new JButton("회원가입");
				btnJoin.setBackground(Color.BLACK);
				pCenter.add(btnJoin);
				btnJoin.addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnJoin) {
			mainFrame.remove(this);
			joinPanel p = new joinPanel(mainFrame, member);
			mainFrame.getContentPane().add(p);
			mainFrame.invalidate();
			mainFrame.validate();
			mainFrame.repaint();
		} else if (e.getSource() == btnHit) {
			if (isLogin.equals("logIn")) {
				mainFrame.remove(this);
				HitPanel p = new HitPanel(mainFrame, member);
				mainFrame.getContentPane().add(p);
				mainFrame.invalidate();
				mainFrame.validate();
				mainFrame.repaint();
			} else {
				JOptionPane.showMessageDialog(this, "로그인을 해주세요");
			}
		} else if (e.getSource() == btnReservation) {
			if (isLogin.equals("logIn")) {
				MovieDAO movieDAO = new MovieDAO();
				movieDAO.resetArea();
				mainFrame.remove(this);
				ReservatePanel p = new ReservatePanel(mainFrame, member);
				mainFrame.getContentPane().add(p);
				mainFrame.invalidate();
				mainFrame.validate();
				mainFrame.repaint();
			} else {
				JOptionPane.showMessageDialog(this, "로그인을 해주세요.");
			}
		} else if (e.getSource() == btnLogin) {
			MemberDAO memberDAO = new MemberDAO();
			MemberDTO login = new MemberDTO();
			login.setId(tfId.getText());
			login.setPwd(pfPwd.getText());
			login = memberDAO.loginMember(login);
			if (login.getLogin() == null || login.getLogin().equals("logOut")) {
				JOptionPane.showMessageDialog(this, "아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
			} else {
				mainFrame.remove(this);
				MainPanel p = new MainPanel(mainFrame, login);
				mainFrame.getContentPane().add(p);
				mainFrame.invalidate();
				mainFrame.validate();
				mainFrame.repaint();
			}
		} else if (e.getSource() == btnLogout) {
			MemberDAO memberDAO = new MemberDAO();
			member = memberDAO.logoutMember(member);
			mainFrame.remove(this);
			MainPanel p = new MainPanel(mainFrame, member);
			mainFrame.getContentPane().add(p);
			mainFrame.invalidate();
			mainFrame.validate();
			mainFrame.repaint();
		}
	}

}
