package gradeInput;

import javax.swing.*;


public class GradeInputPanel extends JPanel{
	
	public GradeInputPanel() {
		setLayout(null);
		
		//ReadGrade.readGradeInfoFile(); 
		// -> GradeInputPanel생성자에서/InputPanel의 importFile버튼의 리스너에서?

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
