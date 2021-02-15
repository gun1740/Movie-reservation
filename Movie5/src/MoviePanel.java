

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;



public class MoviePanel extends JPanel implements ActionListener, MouseListener {
	MainFrame mainFrame;
	JPanel pNorth, pSouth, pCenter, pWest, pEast;
	JButton btnNext, btnPrev, btnMovie1, btnMovie2, btnLogout;
	JTable table;
	MemberDTO member;
	ImageIcon image = null;
	int turn = 0;
	int total = 0;
	String startTime = null;

	public MoviePanel(MainFrame mainFrame, MemberDTO member, int turn) {
		this.mainFrame = mainFrame;
		this.member = member; 
		this.turn = turn;
		MovieDAO movieDAO = new MovieDAO();
		MovieDTO movie = new MovieDTO();
		movie = movieDAO.getMovieInfo();
		total = movieDAO.getMovieTotal();
		ArrayList<MovieDTO> movieArr = new ArrayList<>();
		movieArr = movie.getMovie();
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
		JLabel lblTitle = new JLabel("MovieSelect Page");
		lblTitle.setFont(new Font("Select", Font.BOLD, 40));
		lblTitle.setForeground(Color.WHITE);
		btnLogout = new JButton("로그아웃");
		btnLogout.addActionListener(this);
		p1.add(lblTitle);
		p2.add(btnLogout);
		pNorth.add(p1);
		pNorth.add(p2);
		// center

		pCenter.setLayout(new GridLayout(1, 2));
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		// Center의 오른쪽 화면
		right.setLayout(new GridLayout(2, 1));
		JPanel rSouth = new JPanel();
		JPanel rNorth = new JPanel();
		rSouth.setLayout(new BorderLayout());
		right.add(rNorth);
		right.add(rSouth);
		// north엔 영화정보 label , south엔 시간표 table
		String title = movieArr.get(turn).getTitle();
		String director = movieArr.get(turn).getDirector();
		String actor = movieArr.get(turn).getActor();
		String time = movieArr.get(turn).getPlayTime();
		String genre = movieArr.get(turn).getGenre();
		int age = movieArr.get(turn).getAge();
		String nation = movieArr.get(turn).getNation();
		String open = movieArr.get(turn).getOpenDate();
		rNorth.setLayout(new GridLayout(4, 1));
		JLabel lblMovie = new JLabel(title);
		lblMovie.setFont(new Font("Movie", Font.BOLD, 40));
		lblMovie.setForeground(new Color(0, 0, 0));
		rNorth.add(lblMovie);
		rNorth.add(new JLabel("감독 : " + director + "    주연 : " + actor));
		rNorth.add(new JLabel("장르 : " + genre + "   기본 : " + age + " 세 이용가 : " + "   상영시간  : " + time + "   국가  : " + nation));
		rNorth.add(new JLabel("개봉일 : " + open));
		
		String[] cols = { "시간표" };
		String[][] datas = { { (turn + 1) + "관 AM 8:00~ AM 11:00" }, { (turn + 1) + "관 PM 12:00~ PM 3:00" },
				{ (turn + 1) + "관 PM 4:00~ PM 7:00" }, { (turn + 1) + "관 PM 8:00~ PM 11:00" },
				{ (turn + 1) + "관 AM 12:00~ AM 3:00" } };

		// 모델 설정
		DefaultTableModel model = new DefaultTableModel(datas, cols) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table = new JTable(model);
	
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(this);

	
		JScrollPane pane = new JScrollPane(table);
		rSouth.add(pane, BorderLayout.CENTER);
		// Center의 왼쪽화면 , 이미지 삽입
		String mName = "image/m" + (turn + 1) + ".jpg";
		image = new ImageIcon(mName);
		left.setLayout(new BorderLayout());
		JPanel lp1, lp2, lp3;
		left.add(lp1 = new JPanel(), BorderLayout.WEST);
		left.add(lp2 = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				if(lblMovie.getText().equals("이웃사촌")) {
					ImageIcon image = new ImageIcon("C:\\Users\\GeonSu\\Desktop\\qq\\1.jpg");
					super.paintComponent(g);
					g.drawImage(image.getImage(), 0, 0, 570, 860, this);
				}
				else if(lblMovie.getText().equals("더프롬")) {
					ImageIcon image = new ImageIcon("C:\\Users\\GeonSu\\Desktop\\qq\\2.jpg");
					super.paintComponent(g);
					g.drawImage(image.getImage(), 0, 0, 570, 860, this);
					
				}else if(lblMovie.getText().equals("도굴")) {
					ImageIcon image = new ImageIcon("C:\\Users\\GeonSu\\Desktop\\qq\\3.jpg");
					super.paintComponent(g);
					g.drawImage(image.getImage(), 0, 0, 570, 860, this);
				}
				else if(lblMovie.getText().equals("런")) {
					ImageIcon image = new ImageIcon("C:\\Users\\GeonSu\\Desktop\\qq\\4.jpg");
					super.paintComponent(g);
					g.drawImage(image.getImage(), 0, 0, 570, 860, this);
				}
				else if(lblMovie.getText().equals("삼진그룹영어토익반")) {
					ImageIcon image = new ImageIcon("C:\\Users\\GeonSu\\Desktop\\qq\\5.jpg");
					super.paintComponent(g);
					g.drawImage(image.getImage(), 0, 0, 570, 860, this);
				}
				else if(lblMovie.getText().equals("가나의혼인잔치:언약")) {
					ImageIcon image = new ImageIcon("C:\\Users\\GeonSu\\Desktop\\qq\\9.jpg");
					super.paintComponent(g);
					g.drawImage(image.getImage(), 0, 0, 570, 860, this);
				}
				else if(lblMovie.getText().equals("잔칫날")) {
					ImageIcon image = new ImageIcon("C:\\Users\\GeonSu\\Desktop\\qq\\6.jpg");
					super.paintComponent(g);
					g.drawImage(image.getImage(), 0, 0, 570, 860, this);
				}
				else if(lblMovie.getText().equals("킹덤오브헤븐:디렉터스컷")) {
					ImageIcon image = new ImageIcon("C:\\Users\\GeonSu\\Desktop\\qq\\8.jpg");
					super.paintComponent(g);
					g.drawImage(image.getImage(), 0, 0, 570, 860, this);
				}
				else if(lblMovie.getText().equals("프리키 데스데이")) {
					ImageIcon image = new ImageIcon("C:\\Users\\GeonSu\\Desktop\\qq\\7.jpg");
					super.paintComponent(g);
					g.drawImage(image.getImage(), 0, 0, 570, 860, this);
				}
				
//		
			}
		}, BorderLayout.CENTER);
		left.add(lp3 = new JPanel(), BorderLayout.EAST);
		lp1.setLayout(new BorderLayout());
		lp3.setLayout(new BorderLayout());
		lp1.add(btnMovie1 = new JButton("이전영화"), BorderLayout.CENTER);
		lp3.add(btnMovie2 = new JButton("다음영화"), BorderLayout.CENTER);
		btnMovie1.addActionListener(this);
		btnMovie2.addActionListener(this);
		btnMovie1.setBackground(Color.WHITE);
		btnMovie2.setBackground(Color.WHITE);
		lp2.setBackground(Color.BLACK); // 영화 이미지 들어갈 패널
		pCenter.add(left);
		pCenter.add(right);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNext) {
			if (startTime == null) {
				JOptionPane.showMessageDialog(this, "시간을 선택해주세요.");
			} else {
				member.setStartTime(startTime);
				member.setMovie(""+turn);
				mainFrame.remove(this);
				SitPanel p = new SitPanel(mainFrame, member);
				mainFrame.getContentPane().add(p);
				mainFrame.invalidate();
				mainFrame.validate();
				mainFrame.repaint();
			}
		} else if (e.getSource() == btnPrev) {
			MovieDAO movieDAO = new MovieDAO();
			movieDAO.resetArea();
			mainFrame.remove(this);
			ReservatePanel p = new ReservatePanel(mainFrame, member);
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
		} else if (e.getSource() == btnMovie1) {
			// 이전버튼
			turn--;
			if (turn < 0)
				turn = total - 1;
			mainFrame.remove(this);
			MoviePanel p = new MoviePanel(mainFrame, member, turn);
			mainFrame.getContentPane().add(p);
			mainFrame.invalidate();
			mainFrame.validate();
			mainFrame.repaint();
		} else if (e.getSource() == btnMovie2) {
			// 다음버튼
			turn++;
			if (turn >= total) {
				turn = 0;
			}
			mainFrame.remove(this);
			MoviePanel p = new MoviePanel(mainFrame, member, turn);
			mainFrame.getContentPane().add(p);
			mainFrame.invalidate();
			mainFrame.validate();
			mainFrame.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		startTime = "" + table.getValueAt(row, col);
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
