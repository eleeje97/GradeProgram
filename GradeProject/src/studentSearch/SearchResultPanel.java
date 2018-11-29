package studentSearch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import gradeAnalysis.CalculateGrade;
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
	
	static JLabel studentGradeAvailableLabel; //�����Է� �ǿ��� �л� ������ �ԷµǾ����� �˷��ִ� ��
	static JTable studentGradesTable; //�˻��� �л� ���� ���̺�
	static String[] columnNames = { "��  ��", "��  ��", "��  ��" };
	static Object[][] rowData = {
		{ "�� ��", 0, "" },
		{ "�� ��", 0, "" },
		{ "�� ��", 0, "" },
		{ "�� ȸ", 0, "" },
		{ "�� ��", 0, "" },
		{ "�� ��", 0, "" }
	};
		
	//������
	public SearchResultPanel() {
		setLayout(null);
		//setBorder(new TitledBorder(new LineBorder(Color.GRAY,2),"�˻� ���"));
		//�л��������� ���� ��ġ
		for(int labelCount=0; labelCount<fixedTitleLabel.length; labelCount++) {
			
			fixedTitleLabel[labelCount].setFont(new Font("���ʷҵ���",Font.BOLD,20));
			fixedTitleLabel[labelCount].setSize(120,30);
			fixedTitleLabel[labelCount].setLocation(5,30+60*labelCount);
			add(fixedTitleLabel[labelCount]);
		}
		
		/*�˻��� �л� ���� �� ��ġ*/
		//1)�л� �̸���
		studentNameLabel = new JLabel("");
		studentNameLabel.setFont(new Font("���ʷҵ���",Font.PLAIN,20));
		studentNameLabel.setSize(120,30);
		studentNameLabel.setLocation(150,30);
		add(studentNameLabel);
		//2)�л� �й���
		studentIDLabel = new JLabel("");
		studentIDLabel.setFont(new Font("���ʷҵ���",Font.PLAIN,20));
		studentIDLabel.setSize(120,30);
		studentIDLabel.setLocation(150,90);
		add(studentIDLabel);	
		//3)�л� ��ȣ��
		studentPhoneLabel = new JLabel("");
		studentPhoneLabel.setFont(new Font("���ʷҵ���",Font.PLAIN,20));
		studentPhoneLabel.setSize(250,30);
		studentPhoneLabel.setLocation(150,150);
		add(studentPhoneLabel);	
		//4)������ �ԷµǾ������� �˷��ִ� ��
		studentGradeAvailableLabel = new JLabel("�����Է� �ǿ��� �л��� ������ ���� �Է��ϼ���.");
		studentGradeAvailableLabel.setFont(new Font("���ʷҵ���",Font.BOLD,15));
		studentGradeAvailableLabel.setSize(500,30);
		studentGradeAvailableLabel.setLocation(150,210);
		studentGradeAvailableLabel.setVisible(false); //�ʱ⿡�� �Ⱥ��̴� ����
		
		add(studentGradeAvailableLabel);

		
		//5)�л� ���� ���̺� ���� �� ��ġ
		
		studentGradesTable = new JTable(rowData, columnNames);
		studentGradesTable.setEnabled(false); //�� �� ���� �Ұ�
		studentGradesTable.setRowHeight(50); //�� ����� ���� ����
		studentGradesTable.getTableHeader().setPreferredSize(new Dimension(50,50)); //����� ���� ����
		//���̺� �� �۾�ü ����
		studentGradesTable.getTableHeader().setFont(new Font("���ʷҵ���", Font.BOLD, 20)); //����۾�ü
		studentGradesTable.setFont(new Font("���ʷҵ���", Font.PLAIN, 20)); //�� �۾�ü

		//�� ���� ���� ��� ���� 
		setTextAlignCenter(studentGradesTable);
		
		JPanel tablePanel = new JPanel(new BorderLayout());	//���� ���̺� ���� �г� -> ������ ����� �Ⱥ���
		tablePanel.setSize(601,350);
		tablePanel.setLocation(4,270);
		tablePanel.add(studentGradesTable, BorderLayout.CENTER);
		tablePanel.add(studentGradesTable.getTableHeader(), BorderLayout.NORTH);
		add(tablePanel);
				
	}
	//�˻��� �л��� ������ �Էµ� �������� Ȯ���ϴ� �޼ҵ�
	//������ �Էµ��� ���� ���¶�� false ������ �Էµ� ���¶�� true��ȯ
	public static boolean gradeAvailable() {
		boolean isAvailable = false;
		for(int rowCount=0;rowCount<rowData.length;rowCount++) {
			if(Double.parseDouble(""+rowData[rowCount][1]) != 0) { //�� �����̶� 0���� �ƴϸ� �Էµ� ���·� ����
				isAvailable = true;
				return isAvailable;
			}	
		}
		return isAvailable; //��� ������ 0 ���̶�� �Էµ��� ���� ���·� ����.
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
		
		//�л��� ���� �ҷ��ͼ� ���̺� �־��ֱ�
		rowData[0][1] = student.koreanGrade;
		rowData[1][1] = student.englishGrade;
		rowData[2][1] = student.mathGrade;
		rowData[3][1] = student.societyGrade;
		rowData[4][1] = student.scienceGrade;
		//�л��� ��� �߰�
		CalculateGrade.getSum_AverageByStudent(); //��հ��
		rowData[5][1] = student.average;

		
		//�л� ���� �ҷ��ͼ� ���̺� �־��ֱ�
		CalculateGrade.calculateGrade(); //���� ����ؼ� db�� ����
		String[] studentGrades = student.grade; //�л���  ������ �迭�� ������
		
		for(int subjectCount=0;subjectCount<rowData.length-1;subjectCount++){
			rowData[subjectCount][2] = studentGrades[subjectCount];
		}

		//�� ������� ���̺� �־��ֱ�
		rowData[5][2] = student.totalGrade;

		//�־��� ���̺� �� ������Ʈ ���ֱ�
		studentGradesTable.updateUI();
		
		if(!gradeAvailable())//������ �Էµ��� ���� ���¶�� ������ ���� �Է��϶�� ���� ���δ�.
			studentGradeAvailableLabel.setVisible(true);
		else //������ �Էµ� ���¶�� ���� ������ �ʴ´�.
			studentGradeAvailableLabel.setVisible(false);

	}
}




