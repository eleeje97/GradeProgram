package gradeInput;

import javax.swing.*;


public class GradeInputPanel extends JPanel{
	
	public GradeInputPanel() {
		setLayout(null);
		
		//ReadGrade.readGradeInfoFile(); 
		// -> GradeInputPanel�����ڿ���/InputPanel�� importFile��ư�� �����ʿ���?

		JPanel inputPanel = new InputPanel(); //���� �г�
		JPanel tablePanel = new TablePanel(); //������ �г�
		
		inputPanel.setSize(810,971);
		inputPanel.setLocation(0,0);
		tablePanel.setSize(1099,960);
		tablePanel.setLocation(813,8);
		

		
		add(inputPanel);
		add(tablePanel);
	}
}
