import java.awt.*;
import javax.swing.*;

import studentSearch.*;
import gradeInput.*;
import gradeAnalysis.*;


public class MainFrame extends JFrame{
	
	JTabbedPane tabPane = new JTabbedPane();  //JTabbedPane����
	JPanel gradeInputPanel = new GradeInputPanel(); //�����Է���
	JPanel gradeAnalysisPanel = new GradeAnalysisPanel(); //�����м���
	JPanel studentSearchPanel = new StudentSearchPanel(); //�л��˻��� 
	
	//������
	public MainFrame() {
		super("�л� ���� ���� ���α׷�"); //Ÿ��Ʋ ����
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�ǿ� �� ���� �޴��� �߰�
		tabPane.addTab("�����Է�", gradeInputPanel);
		tabPane.addTab("�����м�", gradeAnalysisPanel);
		tabPane.addTab("�л��˻�", studentSearchPanel);

		//������ ũ�� Ű���
		JLabel label1 = new JLabel("�����Է�");
		label1.setPreferredSize(new Dimension(60,30));
		tabPane.setTabComponentAt(0, label1);
		JLabel label2 = new JLabel("�����м�");
		label2.setPreferredSize(new Dimension(60,30));
		tabPane.setTabComponentAt(1, label2);
		JLabel label3 = new JLabel("�л��˻�");
		label3.setPreferredSize(new Dimension(60,30));
		tabPane.setTabComponentAt(2, label3);
		
		
		add(tabPane);
		
        //setSize(1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);//��üȭ������ ����ϱ�
		setVisible(true);
	}
}
