 

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
		// ���̺� ���� ��
		String[] cols = { "������" };
		// String[][] datas = { { "���ʵ���" }, { "��������" }, { "����͹̳���" }, { "�����͹̳���" }, {
		// "���뿪��" } };
		String[][] datas = movieDAO.getArea("������", local);
		// �� ����
		DefaultTableModel model = new DefaultTableModel(datas, cols) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table = new JTable(model);
		// ��Ʈ��Ű ������ ���ϼ���? ������
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(this);
		// ��ũ�� �ҿ� ���̱�
		JScrollPane pane = new JScrollPane(table);
		add(pane);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		String value = "" + table.getValueAt(row, col); 
		MovieDAO movieDAO = new MovieDAO();
		movieDAO.selectArea("������", value);
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
