 

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
 


public class DongPanel extends JPanel implements MouseListener {
	JTable table; 

	public DongPanel(String local) { 
		MovieDAO movieDAO = new MovieDAO();
		setLayout(new BorderLayout());
		// 테이블에 넣을 값
		String[] cols = { "영업점" };
		// String[][] datas = { { "서초동점" }, { "강남역점" }, { "고속터미널점" }, { "남부터미널점" }, {
		// "교대역점" } };
		String[][] datas = movieDAO.getArea("영업점", local);
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
		movieDAO.selectArea("영업점", value);
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
