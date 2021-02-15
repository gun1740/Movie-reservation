

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;



public class SiPanel extends JPanel implements MouseListener {
	JTable table;
	GuPanel gu;
	DongPanel dong;
	JPanel center, right;

	public SiPanel(JPanel center, JPanel right, GuPanel gu, DongPanel dong) {
		this.center = center;
		this.right = right;
		this.gu = gu;
		this.dong = dong;
		MovieDAO movieDAO = new MovieDAO();
		MovieDTO area = new MovieDTO();
		setLayout(new BorderLayout());
		// ���̺� ���� ��
		String[] cols = { "����" };
		// String[][] datas = { { "����Ư����" }, { "��õ������" }, { "����������" }, { "�λ걤����" }, {
		// "���ֱ�����" } };
		String[][] datas = movieDAO.getArea("����", "");
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
		movieDAO.selectArea("����", value);
		center.removeAll();
		right.removeAll();
		DongPanel dong = new DongPanel("");
		GuPanel gu = new GuPanel(right, dong, value);
		center.add(gu);
		center.invalidate();
		center.validate();
		center.repaint();
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
