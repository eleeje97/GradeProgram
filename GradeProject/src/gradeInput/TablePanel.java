package gradeInput;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

import studentPackage.*;


public class TablePanel extends JPanel {
	static String columnNames[] = { "�й�", "�̸�", "����", "����", "����", "��ȸ", "����" }; //JTable�� ����̸���
	static Vector<Student> studentsDB = StudentsDatabase.getStudentsDatabase(); //�л����� ����
	static Object[][] rowData = new Object[studentsDB.size()][columnNames.length]; //���̺��� �����͹迭
	static JTable table; //�л������� ������ ���̺�
	JScrollPane scrollPane;
	
	public TablePanel() {
		setLayout(null);
		
		//rowData�迭�� studentDB�� �Ӽ���(�й�, �̸�)���� ����
		for (int i = 0; i < studentsDB.size(); i++) {
			rowData[i][0] = studentsDB.get(i).getStudentID();
			rowData[i][1] = studentsDB.get(i).getName();
		}
		
		table = new JTable(rowData, columnNames); //���̺� ����
				
		
		scrollPane = new JScrollPane(table); //���̺� ��ũ�ѹ� ����
		scrollPane.setSize(1100,940);
		scrollPane.setLocation(0,0);
		
		
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(10); //�й��� �ʺ� ����
		tcm.getColumn(1).setPreferredWidth(100); //�̸��� �ʺ� ����
		table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(),30)); //��� ���� ����
		table.setRowHeight(40); //��� ���� ���� ����
		table.setFont(new Font("Gothic",Font.PLAIN,20)); //��Ʈ ����
		table.setEnabled(false); //��Ȱ��ȭ
		
		
		//�� ��� ����
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		//��ü ���� ����
		for(int i = 0 ; i < tcm.getColumnCount() ; i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		
		
		add(scrollPane);
		
	}
}
