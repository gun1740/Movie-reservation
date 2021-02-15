

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;



public class GuPanel extends JPanel implements MouseListener {
	JTable table;
	JPanel right, dong;

	public GuPanel(JPanel right, DongPanel dong, String city) {
		this.right = right;
		this.dong = dong;
		MovieDAO movieDAO = new MovieDAO();
		setLayout(new BorderLayout());
		// 테이블에 넣을 값
		String[] cols = { "지역구" };
		// String[][] datas = { { "서초구" }, { "강남구" }, { "동작구" }, { "관악구" }, { "영등포구" }
		// };
		String[][] datas = movieDAO.getArea("지역구", city);
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
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		String value = "" + table.getValueAt(row, col);
		MovieDAO movieDAO = new MovieDAO();
		movieDAO.selectArea("지역구", value);
		
		right.removeAll();
		DongPanel dong = new DongPanel(value);
		right.add(dong);
		right.invalidate();
		right.validate();
		right.repaint();
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
