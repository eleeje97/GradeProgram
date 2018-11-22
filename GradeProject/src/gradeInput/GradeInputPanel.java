package gradeInput;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import studentPackage.*;
import java.util.*;


public class GradeInputPanel extends JPanel{
	JPanel inputPanel = new InputPanel(); //���� �г�
	JPanel tablePanel = new TablePanel(); //������ �г�
	
	Vector<Student> studentsDatabase = StudentsDatabase.getStudentsDatabase(); //�л���������
	
	public GradeInputPanel() {
		setLayout(null);
		
		inputPanel.setSize(800,950);
		inputPanel.setLocation(0,0);
		tablePanel.setSize(1100,950);
		tablePanel.setLocation(800,0);
		
		/**test**/
		for(Student s : studentsDatabase) {
			System.out.print(s.getName()+"/"+s.getStudentID()+"/"+s.getPhoneNumber());
			System.out.println("");
		}
		
		add(inputPanel);
		add(tablePanel);
	}
}
