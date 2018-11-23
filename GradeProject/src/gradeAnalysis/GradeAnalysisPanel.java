package gradeAnalysis;

import java.awt.*;
import javax.swing.*;

import gradeInput.ReadGrade;

import java.awt.event.*;

public class GradeAnalysisPanel extends JPanel {
	public GradeAnalysisPanel() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel1 = new SubjectPanel(); //전체의 반(왼쪽 패널)
		JPanel panel2 = new GraphPanel(); //전체의 반(오른쪽 패널)
		setLayout(new GridLayout(1,2));
		add(panel1);	
		add(panel2);
		//setExtendedState(JFrame.MAXIMIZED_BOTH); //화면크기에 맞춰 전체로 출력하기위해 사용
		//setVisible(true);
		
		
	}
}
