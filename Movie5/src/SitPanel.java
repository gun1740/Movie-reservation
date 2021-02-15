

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;



public class SitPanel extends JPanel implements ActionListener {
	MainFrame mainFrame;
	JPanel pNorth, pSouth, pCenter, pWest, pEast;
	JButton btnNext, btnPrev, btnMovie1, btnMovie2, btnLogout , payment;
	JTable table;
	String[] sitArr = { "A1", "A2", "A3", "A4", null, "A5", "A6", "A7", "A8", "A9", null, "A10", "A11", "A12", "A13",
			"B1", "B2", "B3", "B4", null, "B5", "B6", "B7", "B8", "B9", null, "B10", "B11", "B12", "B13", "C1", "C2",
			"C3", "C4", null, "C5", "C6", "C7", "C8", "C9", null, "C10", "C11", "C12", "C13", "D1", "D2", "D3", "D4",
			null, "D5", "D6", "D7", "D8", "D9", null, "D10", "D11", "D12", "D13", "E1", "E2", "E3", "E4", null, "E5",
			"E6", "E7", "E8", "E9", null, "E10", "E11", "E12", "E13", "F1", "F2", "F3", "F4", null, "F5", "F6", "F7",
			"F8", "F9", null, "F10", "F11", "F12", "F13", "G1", "G2", "G3", "G4", null, "G5", "G6", "G7", "G8", "G9",
			null, "G10", "G11", "G12", "G13", "H1", "H2", "H3", "H4", null, "H5", "H6", "H7", "H8", "H9", null, "H10",
			"H11", "H12", "H13", "I1", "I2", "I3", "I4", null, "I5", "I6", "I7", "I8", "I9", null, "I10", "I11", "I12",
			"I13", "J1", "J2", "J3", "J4", null, "J5", "J6", "J7", "J8", "J9", null, "J10", "J11", "J12", "J13", "K1",
			"K2", "K3", "K4", null, "K5", "K6", "K7", "K8", "K9", null, "K10", "K11", "K12", "K13", "L1", "L2", "L3",
			"L4", null, "L5", "L6", "L7", "L8", "L9", null, "L10", "L11", "L12", "L13", "M1", "M2", "M3", "M4", null,
			"M5", "M6", "M7", "M8", "M9", null, "M10", "M11", "M12", "M13", "N1", "N2", "N3", "N4", null, "N5", "N6",
			"N7", "N8", "N9", null, "N10", "N11", "N12", "N13", "O1", "O2", "O3", "O4", null, "O5", "O6", "O7", "O8",
			"O9", null, "O10", "O11", "O12", "O13", "P1", "P2", "P3", "P4", null, "P5", "P6", "P7", "P8", "P9", null,
			"P10", "P11", "P12", "P13", "Q1", "Q2", "Q3", "Q4", null, "Q5", "Q6", "Q7", "Q8", "Q9", null, "Q10", "Q11",
			"Q12", "Q13", "R1", "R2", "R3", "R4", null, "R5", "R6", "R7", "R8", "R9", null, "R10", "R11", "R12", "R13",
			"S1", "S2", "S3", "S4", null, "S5", "S6", "S7", "S8", "S9", null, "S10", "S11", "S12", "S13", "T1", "T2",
			"T3", "T4", null, "T5", "T6", "T7", "T8", "T9", null, "T10", "T11", "T12", "T13" };
	ArrayList<JButton> btnArr;
	ArrayList<String> sitSelectArr;
	MemberDTO member;


	public SitPanel(MainFrame mainFrame, MemberDTO member) {
		this.member = member;
		this.mainFrame = mainFrame;
		ArrayList<MemberDTO> reservationArr = new ArrayList<>();
		MovieDAO movieDAO = new MovieDAO();
		reservationArr = movieDAO.getReservation(member);
		sitSelectArr = new ArrayList<>();
		btnArr = new ArrayList<>();
		setLayout(new BorderLayout());
		add(pNorth = new JPanel(), BorderLayout.NORTH);
		add(pSouth = new JPanel(), BorderLayout.SOUTH);
		add(pCenter = new JPanel(), BorderLayout.CENTER);
		add(pWest = new JPanel(), BorderLayout.WEST);
		add(pEast = new JPanel(), BorderLayout.EAST);

		pSouth.setBackground(Color.BLACK);
		pWest.setBackground(Color.BLACK);
		pEast.setBackground(Color.BLACK);
		// south
		pSouth.add(btnPrev = new JButton("이전"));
		pSouth.add(btnNext = new JButton("다음"));
		
		payment = new JButton("\uACB0\uC81C");
		pSouth.add(payment);
		
		btnNext.addActionListener(this);
		btnPrev.addActionListener(this);
		
		btnNext.getActionListeners();
		// north
		pNorth.setLayout(new GridLayout(1, 2));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setLayout(new FlowLayout(3));
		p2.setLayout(new FlowLayout(2));
		p1.setBackground(Color.BLACK);
		p2.setBackground(Color.BLACK);
		JLabel lblTitle = new JLabel("SitSelect Page");
		lblTitle.setFont(new Font("Sit", Font.BOLD, 20));
		lblTitle.setForeground(Color.WHITE);
		btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(this);
		p1.add(lblTitle);
		p2.add(btnLogout);
		pNorth.add(p1);
		pNorth.add(p2);
		// center
		pCenter.setLayout(new BorderLayout());
		JPanel ppNorth = new JPanel();
		JPanel ppCenter = new JPanel();
		// center 의 north
		pCenter.add(ppNorth, BorderLayout.NORTH);
		ppNorth.setBackground(Color.GRAY);
		JLabel lblScreen = new JLabel("SCREEN");
		lblScreen.setFont(new Font("Screen", Font.BOLD, 50));
		lblScreen.setForeground(Color.WHITE);
		ppNorth.add(lblScreen);
		// center 의 center
		pCenter.add(ppCenter, BorderLayout.SOUTH);
		ppCenter.setLayout(new GridLayout(20, 10));
		for (int i = 1; i <= 300; i++) {
			JButton btn = null;
			if (i == 5 || i == 20 || i == 35 || i == 50 || i == 65 || i == 80 || i == 95 || i == 110 || i == 125
					|| i == 140 || i == 155 || i == 170 || i == 185 || i == 200 || i == 215 || i == 230 || i == 245
					|| i == 260 || i == 275 || i == 290) {
				btn = new JButton();
				btn.setBackground(Color.GRAY);
				btn.setEnabled(false);
			} else if (i == 11 || i == 26 || i == 41 || i == 56 || i == 71 || i == 86 || i == 101 || i == 116
					|| i == 131 || i == 146 || i == 161 || i == 176 || i == 191 || i == 206 || i == 221 || i == 236
					|| i == 251 || i == 266 || i == 281 || i == 296) {
				btn = new JButton();
				btn.setBackground(Color.GRAY);
				btn.setEnabled(false);
			} else {
				btn = new JButton(sitArr[i - 1]);
				btn.setBackground(Color.WHITE);
				for (int j = 0; j < reservationArr.size(); j++) {
					 
					if (sitArr[i - 1].equals(reservationArr.get(j).getResSit())) {
						btn = new JButton(sitArr[i - 1]);
						btn.setBackground(Color.LIGHT_GRAY);
						btn.setEnabled(false);
					}
				}
			}
			btnArr.add(btn);
			ppCenter.add(btn);
			btn.addActionListener(this);
			payment.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent ae) {
					PaymentPanel pp = new PaymentPanel();

					
				}
			});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNext) {
			int val = JOptionPane.showConfirmDialog(this, "예매하시겠습니까?");
			if (val == 0) {
				MemberDAO memberDAO = new MemberDAO();
				member.setSit(sitSelectArr);
				memberDAO.reservateMember(member);
				JOptionPane.showMessageDialog(this, "예매하였습니다.");
				mainFrame.remove(this);
				MainPanel p = new MainPanel(mainFrame, member);
				mainFrame.getContentPane().add(p);
				mainFrame.invalidate();
				mainFrame.validate();
				mainFrame.repaint();
			}
		} else if (e.getSource() == btnPrev) {
			mainFrame.remove(this);
			MoviePanel p = new MoviePanel(mainFrame, member, 0);
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
		for (int i = 0; i < 300; i++) {
			if (e.getSource() == btnArr.get(i)) {
				if (btnArr.get(i).getBackground() == Color.WHITE) {
					if (sitSelectArr.size() < 5) {
						sitSelectArr.add(sitArr[i]);
						btnArr.get(i).setBackground(Color.YELLOW);
					}
				} else {
					sitSelectArr.remove(sitArr[i]);
					btnArr.get(i).setBackground(Color.WHITE);
				}
			}
		}
	}
	public JButton getPayment() {
		return payment;
	}
}
