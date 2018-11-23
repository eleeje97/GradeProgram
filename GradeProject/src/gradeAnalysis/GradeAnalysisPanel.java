package gradeAnalysis;

import java.awt.*;
import javax.swing.*;

import gradeInput.ReadGrade;

import java.awt.event.*;

public class GradeAnalysisPanel extends JPanel {
	public GradeAnalysisPanel() {
		JPanel panel1 = new SubjectPanel(); //��ü�� ��(���� �г�)
		JPanel panel2 = new GraphPanel(); //��ü�� ��(������ �г�)
		setLayout(new GridLayout(1,2));
		add(panel1);	
		add(panel2);
	}
}
