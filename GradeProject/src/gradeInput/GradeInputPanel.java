package gradeInput;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GradeInputPanel extends JPanel{
	JPanel inputPanel = new InputPanel(); //왼쪽 패널
	JPanel tablePanel = new TablePanel(); //오른쪽 패널
	
	public GradeInputPanel() {
		setLayout(null);
		
		inputPanel.setSize(800,950);
		inputPanel.setLocation(0,0);
		tablePanel.setSize(1100,950);
		tablePanel.setLocation(800,0);
		
		add(inputPanel);
		add(tablePanel);
	}
}
