package studentSearch;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
//�л��˻� �� �����г� �� �л� �˻� ����� �����ִ� �г�
public class SearchResultPanel extends JPanel {
	
	JLabel[] fixedTitleLabel = { new JLabel("��    �� :"),new JLabel("��    �� :"), 
			new JLabel("��ȭ��ȣ :"), new JLabel("��    �� :")
	}; //�̸�,�а�,�й�,��ȭ��ȣ,���� ������ ǥ�����ִ� ��
	JLabel studentNameLabel; //�˻��� �л� �̸�
	JLabel studentIDLabel; //�˻��� �л� �й�
	JLabel studentPhoneLabel; //�˻��� �л� ��ȭ��ȣ
	
	JTable studentGradesTable; //�˻��� �л� ���� ���̺�
	
	//������
	public SearchResultPanel() {
		setLayout(null);
		
		//�л��������� ���� ��ġ
		for(int labelCount=0; labelCount<fixedTitleLabel.length; labelCount++) {
			
			fixedTitleLabel[labelCount].setFont(new Font("����ü",Font.BOLD,20));
			fixedTitleLabel[labelCount].setSize(120,30);
			fixedTitleLabel[labelCount].setLocation(30,30+60*labelCount);
			add(fixedTitleLabel[labelCount]);
		}
		
		/*�˻��� �л� ���� �� ��ġ*/
		//1)�л��̸���
		studentNameLabel = new JLabel("�̸� �ʱⰪ");
		studentNameLabel.setFont(new Font("����ü",Font.PLAIN,20));
		studentNameLabel.setSize(120,30);
		studentNameLabel.setLocation(180,30);
		add(studentNameLabel);
		//2)�л��й���
		studentIDLabel = new JLabel("�й� �ʱⰪ");
		studentIDLabel.setFont(new Font("����ü",Font.PLAIN,20));
		studentIDLabel.setSize(120,30);
		studentIDLabel.setLocation(180,90);
		add(studentIDLabel);	
		//3)�л� ��ȣ��
		studentPhoneLabel = new JLabel("��ȣ �ʱⰪ");
		studentPhoneLabel.setFont(new Font("����ü",Font.PLAIN,20));
		studentPhoneLabel.setSize(120,30);
		studentPhoneLabel.setLocation(180,150);
		add(studentPhoneLabel);	
		
		//4)�л� ���� ���̺� ���� �� ��ġ
		String[] columnNames = { "��  ��", "��  ��", "��  ��" };
		Object[][] rowData = {
			{ "�� ��", 100 , "A" },
			{ "�� ��", 100, "A" },
			{ "�� ��", 100, "B" },
			{ "�� ȸ", 100, "C" },
			{ "�� ��", 100, "D" },
			{ "�� ��", 100, "D"}
		};
		studentGradesTable = new JTable(rowData, columnNames);
		studentGradesTable.setEnabled(false); //�� �� ���� �Ұ�
		studentGradesTable.setRowHeight(50); //�� ����� ���� ����
		studentGradesTable.getTableHeader().setPreferredSize(new Dimension(50,50)); //����� ���� ����
		
		//�� ���� ���� ��� ���� 
		setTextAlignCenter(studentGradesTable);
		
		JPanel tablePanel = new JPanel(new BorderLayout());	//���� ���̺� ���� �г� -> ������ ����� �Ⱥ���
		tablePanel.setSize(550,350);
		tablePanel.setLocation(30,270);
		tablePanel.add(studentGradesTable, BorderLayout.CENTER);
		tablePanel.add(studentGradesTable.getTableHeader(), BorderLayout.NORTH);
		add(tablePanel);
		
	}
	
	//���̺��� �� ���� ��� �������ִ� �޼ҵ�
	public static void setTextAlignCenter(JTable table) {
		// DefaultTableCellHeaderRenderer ���� (��� ������ ����)
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer�� ������ ��� ���ķ� ����
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		// ������ ���̺��� ColumnModel�� ������
		TableColumnModel columnModel = table.getColumnModel();
		// �ݺ����� �̿��Ͽ� ���̺��� ��� ���ķ� ����
		for (int colCount = 0; colCount < columnModel.getColumnCount(); colCount++) {
			columnModel.getColumn(colCount).setCellRenderer(renderer);
		}
	}
}
