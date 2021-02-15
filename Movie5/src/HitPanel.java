

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;



public class HitPanel extends JPanel implements ActionListener, MouseListener {
	MainFrame mainFrame;
	JPanel pNorth, pSouth, pCenter, pWest, pEast;
	JButton btnPrev, btnLogout, btnDelete;
	JTable table;
	MemberDTO member, delMember;
	String id, title, startTime, business, sit = null;

	public HitPanel(MainFrame mainFrame, MemberDTO member) {
		this.member = member;
		this.mainFrame = mainFrame;
		setLayout(new BorderLayout());
		add(pNorth = new JPanel(), BorderLayout.NORTH);
		add(pSouth = new JPanel(), BorderLayout.SOUTH);
		add(pCenter = new JPanel(), BorderLayout.CENTER);
		add(pWest = new JPanel(), BorderLayout.WEST);
		add(pEast = new JPanel(), BorderLayout.EAST);

		pSouth.setBackground(new Color(0, 0, 0));
		pWest.setBackground(new Color(0, 0, 0));
		pEast.setBackground(new Color(0, 0, 0));
		// south
		pSouth.add(btnPrev = new JButton("처음으로"));
		pSouth.add(btnDelete = new JButton("삭제"));
		btnPrev.addActionListener(this);
		btnDelete.addActionListener(this);
		// north
		pNorth.setLayout(new GridLayout(1, 2));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setLayout(new FlowLayout(3));
		p2.setLayout(new FlowLayout(2));
		p1.setBackground(new Color(0, 0, 0));
		p2.setBackground(new Color(0, 0, 0));
		JLabel lblTitle = new JLabel("SitCheck Page");
		lblTitle.setFont(new Font("SitCheck", Font.BOLD, 40));
		lblTitle.setForeground(Color.WHITE);
		btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(this);
		p1.add(lblTitle);
		p2.add(btnLogout);
		pNorth.add(p1);
		pNorth.add(p2);
		// center
		MovieDAO movieDAO = new MovieDAO();
		String[][] datas = movieDAO.getUserReservation(member);
		String[] cols = { "아이디", "좌석", "상영시간", "영화제목", "영업점" };

		// 모델 설정
		DefaultTableModel model = new DefaultTableModel(datas, cols) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table = new JTable(model);
		// 컨트롤키 누르고 단일선택? 보여줌
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(this);

		// 스크롤 팬에 붙이기
		JScrollPane pane = new JScrollPane(table);
		add(pane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnPrev) {
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
		} else if (e.getSource() == btnDelete) {
			if (id != null) {
				MovieDAO movieDAO = new MovieDAO();
				boolean ok = movieDAO.deleteReservation(id, sit, title, business, startTime);
				if (ok) {
					JOptionPane.showMessageDialog(this, "삭제되었습니다");
					mainFrame.remove(this);
					HitPanel p = new HitPanel(mainFrame, member);
					mainFrame.getContentPane().add(p);
					mainFrame.invalidate();
					mainFrame.validate();
					mainFrame.repaint();
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		id = "" + table.getValueAt(row, 0);
		sit = "" + table.getValueAt(row, 1);
		startTime = "" + table.getValueAt(row, 2);
		title = "" + table.getValueAt(row, 3);
		business = "" + table.getValueAt(row, 4);
		// String value = "" + table.getValueAt(row, col);
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
}
