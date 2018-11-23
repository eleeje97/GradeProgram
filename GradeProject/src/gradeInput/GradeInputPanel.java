package gradeInput;

import javax.swing.*;


public class GradeInputPanel extends JPanel{
	
	public GradeInputPanel() {
		setLayout(null);
		
		ReadGrade.readGradeInfoFile();

		JPanel inputPanel = new InputPanel(); //왼쪽 패널
		JPanel tablePanel = new TablePanel(); //오른쪽 패널
		
		inputPanel.setSize(800,950);
		inputPanel.setLocation(0,0);
		tablePanel.setSize(1100,950);
		tablePanel.setLocation(800,0);
		
		
		add(inputPanel);
		add(tablePanel);
	}
}
