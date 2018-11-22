package gradeAnalysis;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.util.*;

public class SubjectPanel extends JPanel{
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
		p1.setBackground(Color.red);
		JLabel selectedSubject = new JLabel("������");
		selectedSubject.setFont(new Font("�ü�ü", Font.PLAIN, 30 ));
		selectedSubject.setOpaque(true);
		selectedSubject.setBackground(Color.WHITE); 
		p1.add(selectedSubject); //���� �׸� ����
		ButtonGroup subjects = new ButtonGroup();
		
		JRadioButton[] b = new JRadioButton[subject.length];
		//���� ��ư �߰��ϱ�
		for(int i = 0; i< subject.length; i++) {
			b[i] = new JRadioButton(subject[i]);
			b[i].setFont(new Font("���� ���", Font.PLAIN, 20));
			subjects.add(b[i]); //�����׷쿡 ����
			//b[i].addItemListener(MyIL);
			p1.add(b[i]); 
		}
		b[0].setSelected(true);
		add(p1, BorderLayout.NORTH); //panel1�� ��ܿ� ��ġ
		
		
		
		//���̺�� p3�г��� ���� �г� = p2
		JPanel p2 = new JPanel();
		JPanel p5 = new JPanel(); //�޺��ڽ��� ���̱� ���� �г�
		p2.setLayout(new BorderLayout());
		p2.setBackground(Color.blue);
		p5.setLayout(new BorderLayout());
		String[] colum = {"�й�", "�̸�", "����", "����"};
		String[][] data = {{"A104596", "�����", "92", "A"},
							{"A104216", "�ڴ���", "87", "B"},
							{"A102296", "�̴���", "82", "B-"}};
		JTable table = new JTable(data,colum); //JTable �߰�
		table.setBackground(new Color(255,255,204)); //���̺� �ʵ� ����

		//���̺�� ������ ��� �����ϱ� ���ؼ� ����
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {

			tcmSchedule.getColumn(i).setCellRenderer(dtcr);

			}

		//���̺���� �۾�ũ��
		table.getTableHeader().setFont(new Font("����ü", Font.BOLD, 20));
		table.setFont(new Font("����ü", Font.PLAIN, 15));//���̺������ ũ�� ����
		JScrollPane scroll = new JScrollPane(table); //��ũ�� �� �ޱ�
		p2.add(scroll,BorderLayout.CENTER); //p2�г��� ��� ���̺� �߰�	
		add(p2, BorderLayout.CENTER); //panel1�� ��� ��ġ
		
		//�޺��ڽ� �̿��ؼ� ���Ĺ��
		String[] sort = {"�й���","��������","��������"};
		JComboBox sortbox = new JComboBox(sort);
		p5.add(sortbox, BorderLayout.CENTER);
		p5.add(new JLabel("     "), BorderLayout.EAST);
		p5.setBackground(Color.green);
		p2.add(p5, BorderLayout.NORTH);
		//���̺� ���� ����� �ֱ� ���� �� �ΰ� ����
		
		p2.add(new JLabel("          "),BorderLayout.WEST); 
		p2.add(new JLabel("          "),BorderLayout.EAST);
		
		
		
		//������ ���� �� ������ ��� & ���Ϸ� ���� ��ư�� ��� �г� = p3
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		p3.setLayout(new GridLayout(2,3,0,2));
		p4.setLayout(new BorderLayout());
		p3.setBackground(Color.YELLOW);
		JLabel subjectSum = new JLabel("������ ����",SwingConstants.CENTER);
		subjectSum.setFont(new Font("����ü",Font.BOLD, 15));
		JLabel sum = new JLabel();
		p3.add(subjectSum);
		sum.setOpaque(true);
		sum.setBackground(Color.WHITE);
		p3.add(sum);
		p3.add(new JLabel("")); //�� ���߱����� �߰�
		
		JLabel subjectAverage = new JLabel("������ ���",SwingConstants.CENTER);
		subjectAverage.setFont(new Font("����ü",Font.BOLD, 15));
		JLabel average = new JLabel();
		JButton fileStore = new JButton("���Ϸ� ����");
		p3.add(subjectAverage);
		average.setOpaque(true);
		average.setBackground(Color.WHITE);
		p3.add(average);
		p3.add(fileStore);
		
		p4.add(new JLabel("          "),BorderLayout.WEST);
		p4.add(new JLabel("          "),BorderLayout.EAST);
		p4.add(p3, BorderLayout.CENTER);
		p2.add(p4, BorderLayout.SOUTH); //p2�г��� �ϴܿ� p3 �г� �߰�
		
	}
}
