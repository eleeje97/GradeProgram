package gradeAnalysis;

import java.awt.*;
import javax.swing.*;

import gradeInput.ReadGrade;

import java.awt.event.*;

public class GradeAnalysisPanel extends JPanel {
	public GradeAnalysisPanel() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel1 = new SubjectPanel(); //��ü�� ��(���� �г�)
		JPanel panel2 = new GraphPanel(); //��ü�� ��(������ �г�)
		setLayout(new GridLayout(1,2));
		add(panel1);	
		add(panel2);
		//setExtendedState(JFrame.MAXIMIZED_BOTH); //ȭ��ũ�⿡ ���� ��ü�� ����ϱ����� ���
		//setVisible(true);
		
		
	}
}
