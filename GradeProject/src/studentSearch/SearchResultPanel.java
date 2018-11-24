package studentSearch;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import studentPackage.Student;
import studentPackage.StudentsDatabase;
//�л��˻� �� �����г� �� �л� �˻� ����� �����ִ� �г�
public class SearchResultPanel extends JPanel {
	
	JLabel[] fixedTitleLabel = { new JLabel("��    �� :"),new JLabel("��    �� :"), 
			new JLabel("��ȭ��ȣ :"), new JLabel("��    �� :")
	}; //�̸�,�а�,�й�,��ȭ��ȣ,���� ������ ǥ�����ִ� ��
	static JLabel studentNameLabel; //�˻��� �л� �̸�
	static JLabel studentIDLabel; //�˻��� �л� �й�
	static JLabel studentPhoneLabel; //�˻��� �л� ��ȭ��ȣ
	
	static JTable studentGradesTable; //�˻��� �л� ���� ���̺�
	
	//������
	public SearchResultPanel() {
		setLayout(null);
		
		//�л��������� ���� ��ġ
		for(int labelCount=0; labelCount<fixedTitleLabel.length; labelCount++) {
			
			fixedTitleLabel[labelCount].setFont(new Font("����ü",Font.BOLD,20));
			fixedTitleLabel[labelCount].setSize(120,30);
			fixedTitleLabel[labelCount].setLocation(0,30+60*labelCount);
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
		studentPhoneLabel.setSize(250,30);
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
		//���̺� �� �۾�ü
		studentGradesTable.getTableHeader().setFont(new Font("����ü", Font.BOLD, 20)); //����۾�ü
		studentGradesTable.setFont(new Font("����ü", Font.PLAIN, 20)); //�� �۾�ü

		//�� ���� ���� ��� ���� 
		this.setTextAlignCenter(studentGradesTable);
		
		JPanel tablePanel = new JPanel(new BorderLayout());	//���� ���̺� ���� �г� -> ������ ����� �Ⱥ���
		tablePanel.setSize(610,350);
		tablePanel.setLocation(0,270);
		tablePanel.add(studentGradesTable, BorderLayout.CENTER);
		tablePanel.add(studentGradesTable.getTableHeader(), BorderLayout.NORTH);
		add(tablePanel);
		
	}
	
	//���̺��� �� ���� ��� �������ִ� �޼ҵ�
	public void setTextAlignCenter(JTable table) {
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
	

	//ã�� �л������� ������Ʈ�鿡 ����ִ� �޼ҵ�
	public static void showSearchedResult(Student student) {
		studentNameLabel.setText(student.getName()); //�̸� ����ֱ�
		studentIDLabel.setText(student.getStudentID()); //�й� ����ֱ�
		studentPhoneLabel.setText(student.getPhoneNumber()); //��ȣ ����ֱ�
	}
}



// '�˻�'��ư�� ������ �˻��� �л������� �����ִ� ������
class showSearchResultListener implements ActionListener {
	
	Vector<Student> studentsDatabase = StudentsDatabase.getStudentsDatabase();
	
	//�˻� ��ư�� ������ �ش� �л��� �ִ��� �˻��Ѵ�.
	@Override
	public void actionPerformed(ActionEvent e) {
		String searchTarget = SearchPanel.searchField.getText(); // �˻��ʵ忡 �˻��� �ؽ�Ʈ�� �����ͼ�
		if (searchTarget.length() == 0) { // �ƹ��͵� �Է����� �ʾҴٸ� ���â����
			JOptionPane.showMessageDialog(null, "�˻��ϰ� ���� �л� �̸� �Ǵ� �й��� �Է��ϼ���.", "���",
					JOptionPane.WARNING_MESSAGE);
		} 
		else {
			// Java8 ��Ʈ��,���� ���
			Optional<Student> find = studentsDatabase.stream()
					.filter(s -> s.getName().equals(searchTarget) || s.getStudentID().equals(searchTarget))
					.findAny();
			if (find.isPresent()) {// �˻��� �л��� database�� ������
				SearchResultPanel.showSearchedResult(find.get()); // ã�� �л��� �ѱ��.
			} else {// �˻��� �л��� database�� ������
				JOptionPane.showMessageDialog(null, "�л� �����ͺ��̽��� �˻��� �л��� �������� �ʽ��ϴ�.", "���",
						JOptionPane.WARNING_MESSAGE);
				SearchPanel.searchField.setText(""); //�˻�â�� �ʱ�ȭ ���ش�.
			}
		}
	}
}


