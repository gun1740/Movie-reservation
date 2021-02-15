

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class ReservatePanel extends JPanel implements ActionListener {
	MainFrame mainFrame;
	JPanel pNorth, pCenter, pWest, pEast, pSouth;
	JButton btnLogout, btnNext, btnPrev;
	MemberDTO member;

	public ReservatePanel(MainFrame mainFrame, MemberDTO member) {
		this.member = member;
		this.mainFrame = mainFrame;
		this.member.setDong("no");
		setLayout(new BorderLayout());
		add(pNorth = new JPanel(), BorderLayout.NORTH);
		add(pCenter = new JPanel(), BorderLayout.CENTER);
		add(pWest = new JPanel(), BorderLayout.WEST);
		add(pEast = new JPanel(), BorderLayout.EAST);
		add(pSouth = new JPanel(), BorderLayout.SOUTH);
		pSouth.setForeground(new Color(0, 0, 0));

		pWest.setBackground(new Color(0, 0, 0));
		pEast.setBackground(new Color(0, 0, 0));
		pCenter.setBackground(Color.RED);
		pSouth.setBackground(new Color(0, 0, 0));
		// south
		pSouth.add(btnPrev = new JButton("이전"));
		pSouth.add(btnNext = new JButton("다음"));
		btnNext.addActionListener(this);
		btnPrev.addActionListener(this);
		// north
		pNorth.setLayout(new GridLayout(1, 2));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setLayout(new FlowLayout(3));
		p2.setLayout(new FlowLayout(2));
		p1.setBackground(new Color(0, 0, 0));
		p2.setBackground(new Color(0, 0, 0));
		JLabel lblTitle = new JLabel("Reservation Page");
		lblTitle.setFont(new Font("Reserate", Font.BOLD, 40));
		lblTitle.setForeground(Color.WHITE);
		btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(this);
		p1.add(lblTitle);
		p2.add(btnLogout);
		pNorth.add(p1);
		pNorth.add(p2);
		// center
		pCenter.setLayout(new GridLayout(1, 3));
		// 패널 3개 붙이기
		JPanel left = new JPanel();
		JPanel center = new JPanel();
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout());
		center.setLayout(new BorderLayout());
		left.setLayout(new BorderLayout());
		DongPanel dong = new DongPanel("");
		GuPanel gu = new GuPanel(right, dong, "");
		SiPanel si = new SiPanel(center, right, gu, dong);
		left.add(si);

		pCenter.add(left);
		pCenter.add(center);
		pCenter.add(right);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNext) {
			MovieDAO movieDAO = new MovieDAO();
			member = movieDAO.checkArea(member);
			System.out.println(member.getDong());
			if (member.getDong().equals("no")) {
				JOptionPane.showMessageDialog(this, "영업점을 선택해주세요.");
			} else {
				mainFrame.remove(this);
				MoviePanel p = new MoviePanel(mainFrame, member, 0);
				mainFrame.getContentPane().add(p);
				mainFrame.invalidate();
				mainFrame.validate();
				mainFrame.repaint();
			}
		} else if (e.getSource() == btnPrev) {
			mainFrame.remove(this);
			MainPanel p = new MainPanel(mainFrame, member);
			mainFrame.getContentPane().add(p);
			mainFrame.invalidate();
			mainFrame.validate();
			mainFrame.repaint();
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
