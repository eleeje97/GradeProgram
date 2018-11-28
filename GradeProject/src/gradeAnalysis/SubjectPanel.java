package gradeAnalysis;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import studentPackage.Student;
import studentPackage.StudentsDatabase;

public class SubjectPanel extends JPanel{
	JRadioButton[] b;
	String[] column = {"�й�", "�̸�", "����", "����"};
	Object[][] rowData = new Object[studentDatabase.size()][column.length]; //���̺��� �����͹迭;
	JTable table = new JTable(rowData,column); //JTable �߰�
	JLabel sum = new JLabel();
	JLabel average = new JLabel();
	//�ҷ��� ������ �հ� ����� �����Ѵ�.
	int[] totalSum;
	double[] totalAverage;
	JComboBox<String> sortbox; //�޺��ڽ� �ʱ�ȭ�� ���� ������
	
	private static Vector<Student> studentDatabase = StudentsDatabase.getStudentsDatabase();//�л������ͺ��̽� ������ ������Դ�
	
	public SubjectPanel() {
		//����
		String[] subject = {"����", "����", "����", "��ȸ", "����", "��ü"};
		//panel1�� ���̾ƿ�
		setLayout(new BorderLayout());
		setBorder(new TitledBorder(new LineBorder(Color.darkGray,2),"����")); //�׵θ�
		//RadioBoxChecked MyIL = new RadioBoxChecked();
		
		//������- ������ư �� ���� �г� = p1
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		JLabel selectedSubject = new JLabel("������");
		selectedSubject.setFont(new Font("�ü�ü", Font.PLAIN, 30 ));
		p1.add(selectedSubject); //���� �׸� ����
		ButtonGroup subjects = new ButtonGroup();
		b = new JRadioButton[subject.length];
		
		
		//���� ��ư �߰��ϱ�
		for(int i = 0; i< subject.length; i++) {
			b[i] = new JRadioButton(subject[i]);
			b[i].setFont(new Font("���� ���", Font.PLAIN, 20));
			subjects.add(b[i]); //�����׷쿡 ����
			b[i].addItemListener(new RadioButtonListener());
			p1.add(b[i]); 
		}
		add(p1, BorderLayout.NORTH); //panel1�� ��ܿ� ��ġ
		
		
		//���̺�� p3�г��� ���� �г� = p2
		JPanel p2 = new JPanel();
		JPanel p5 = new JPanel(); //�޺��ڽ��� ���̱� ���� �г�
		p2.setLayout(new BorderLayout());
		p5.setLayout(new BorderLayout());
	
		
		//rowData�迭�� studentDB�� �Ӽ������� �����Ѵ�.
		for (int i = 0; i < studentDatabase.size(); i++) {
			rowData[i][0] = studentDatabase.get(i).getStudentID();
			rowData[i][1] = studentDatabase.get(i).getName();
		}
		
		//��ũ�� �����Ѵ�.
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(10); //�й��� �ʺ� ����
		tcm.getColumn(1).setPreferredWidth(100); //�̸��� �ʺ� ����
		
		
		//���̺�� ������ ��� �����ϱ� ���ؼ� �����Ѵ�.
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {

			tcmSchedule.getColumn(i).setCellRenderer(dtcr);
		}

		//���̺���� �۾�ũ��
		table.getTableHeader().setFont(new Font("����ü", Font.BOLD, 20));
		table.setRowHeight(40); //��� ���� ���� ����
		table.setFont(new Font("����ü", Font.PLAIN, 20));//���̺������ ũ�� ����
		JScrollPane scroll = new JScrollPane(table); //��ũ�� �� �ޱ�
		p2.add(scroll,BorderLayout.CENTER); //p2�г��� ��� ���̺� �߰�	
		add(p2, BorderLayout.CENTER); //panel1�� ��� ��ġ
		
		//�޺��ڽ� �̿��ؼ� ���Ĺ��
		String[] sort = {"�й���","�̸���","������"};
		sortbox = new JComboBox<String>(sort);
		sortbox.setFont(new Font("����ü", Font.BOLD, 20));
		JPanel p6 = new JPanel();
		p6.setLayout(new BorderLayout());
		p6.add(sortbox, BorderLayout.CENTER);
		p6.add(new JLabel("          "), BorderLayout.EAST);
		p5.add(p6, BorderLayout.EAST);
		p5.add(new JLabel("          "), BorderLayout.CENTER);
		//p5.setBackground(Color.green);
		p2.add(p5, BorderLayout.NORTH);
		sortbox.addItemListener(new CombBoxListener()); //������ �߰�
		
		
		//���̺� ���� ����� �ֱ� ���� �� �ΰ� ����
		p2.add(new JLabel("          "),BorderLayout.WEST); 
		p2.add(new JLabel("          "),BorderLayout.EAST);
		
		//������ ���� �� ������ ��� & ���Ϸ� ���� ��ư�� ��� �г� = p3
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		p3.setLayout(new GridLayout(2,3,0,2));
		p4.setLayout(new BorderLayout());
		
		
		//������ ����
		JLabel subjectSum = new JLabel("������ ����",SwingConstants.CENTER);
		subjectSum.setFont(new Font("����ü",Font.BOLD, 15));
		p3.add(subjectSum);
		sum.setOpaque(true);
		sum.setBackground(Color.LIGHT_GRAY);
		p3.add(sum);
		p3.add(new JLabel("")); //�� ���߱����� �߰�
		
		
		//������ ���
		JLabel subjectAverage = new JLabel("������ ���",SwingConstants.CENTER);
		subjectAverage.setFont(new Font("����ü",Font.BOLD, 15));
		JButton fileStore = new JButton("���Ϸ� ����");
		
		//���� �����ϴ� �����ʸ� �߰��Ѵ�.
		fileStore.addActionListener(new StoreFileListener());
		p3.add(subjectAverage);
		average.setOpaque(true);
		average.setBackground(Color.LIGHT_GRAY);
		p3.add(average);
		p3.add(fileStore);
		p4.add(new JLabel("          "),BorderLayout.WEST);
		p4.add(new JLabel("          "),BorderLayout.EAST);
		p4.add(p3, BorderLayout.CENTER);
		p2.add(p4, BorderLayout.SOUTH); //p2�г��� �ϴܿ� p3 �г� �߰�
		
	}

	
	//���� ��ư�� üũ�Ǿ��� �� 
	class RadioButtonListener implements ItemListener{
		//getSumSubject�� getAverageSubject �޼ҵ尡 int�迭�� return������ �迭�� �޴´�.
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			//****������ �ٽ� �����ϸ� ���Ĺ���� �������̴�.
			sortbox.setSelectedIndex(0);
			JRadioButton radiobutton = (JRadioButton)e.getSource();
			totalSum = CalculateGrade.getSumBySubject();
			totalAverage = CalculateGrade.getAverageBySubject();
			CalculateGrade.getSum_AverageByStudent();
			CalculateGrade.calculateGrade();
			
			if(radiobutton == b[0]) { //��������� ������ư�� üũ�Ǿ��� ��
				for (int i = 0; i < studentDatabase.size(); i++) {
					rowData[i][2] = studentDatabase.get(i).koreanGrade;
					rowData[i][3] = studentDatabase.get(i).grade[0];	
					
				}
				sum.setText(Integer.toString(totalSum[0]));
				average.setText(Double.toString(totalAverage[0]));			
				GraphPanel.paintGraph(rowData,0);
				
			}
			else if(radiobutton == b[1]) {//��������� ������ư�� üũ�Ǿ��� ��
				for (int i = 0; i < studentDatabase.size(); i++) {
					rowData[i][2] = studentDatabase.get(i).englishGrade;
					rowData[i][3] = studentDatabase.get(i).grade[1];
				}
				sum.setText(Integer.toString(totalSum[1]));
				average.setText(Double.toString(totalAverage[1]));
				GraphPanel.paintGraph(rowData ,1);
			}
			else if(radiobutton == b[2]) {//���а����� ������ư�� üũ�Ǿ��� ��
				for (int i = 0; i < studentDatabase.size(); i++) {
					rowData[i][2] = studentDatabase.get(i).mathGrade;
					rowData[i][3] = studentDatabase.get(i).grade[2];
				}
				sum.setText(Integer.toString(totalSum[2]));
				average.setText(Double.toString(totalAverage[2]));
				GraphPanel.paintGraph(rowData,2);

			}
			else if(radiobutton == b[3]) {//��ȸ������ ������ư�� üũ�Ǿ��� ��
				for (int i = 0; i < studentDatabase.size(); i++) {
					rowData[i][2] = studentDatabase.get(i).societyGrade;
					rowData[i][3] = studentDatabase.get(i).grade[3];
				}
				sum.setText(Integer.toString(totalSum[3]));
				average.setText(Double.toString(totalAverage[3]));
				GraphPanel.paintGraph(rowData,3);

			}
			else if(radiobutton == b[4]) {//���а����� ������ư�� üũ�Ǿ��� ��
				for (int i = 0; i < studentDatabase.size(); i++) {
					rowData[i][2] = studentDatabase.get(i).scienceGrade;
					rowData[i][3] = studentDatabase.get(i).grade[4];
				}
				sum.setText(Integer.toString(totalSum[4]));
				average.setText(Double.toString(totalAverage[4]));
				GraphPanel.paintGraph(rowData,4);
			}
			else if(radiobutton == b[5]) {//��ü ������ư�� üũ�Ǿ��� ��
				
				for (int i = 0; i < studentDatabase.size(); i++) {
					rowData[i][2] = studentDatabase.get(i).average;
					rowData[i][3] = studentDatabase.get(i).totalGrade;
				}
				GraphPanel.paintGraph(rowData,5);
			}
			
			table.updateUI();
			
		}
		
	}
	
	//�޺��ڽ� ������
	//�й��� �̸��� ���������� �����Ѵ�.
	class CombBoxListener implements ItemListener{
	    
		@Override
		public void itemStateChanged(ItemEvent e) {
			JComboBox<String> combo = (JComboBox)e.getSource();
		    
			//���� rowData �Ѱ��ֱ�
			QuickSorter quickSorter = new QuickSorter(rowData);

			if(combo.getSelectedItem().equals("�й���")) {
				quickSorter.sort(0); //���� ������ ���Ĺ���� �Ѱ��ش�.
			}
			else if(combo.getSelectedItem().equals("�̸���")) {
				quickSorter.sort(1);
			}
			else if(combo.getSelectedItem().equals("������")) {
				quickSorter.sort(2);

			}
			
			table.updateUI();
		}
		
	}
}
