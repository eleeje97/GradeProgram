package gradeInput;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GradeInputPanel extends JPanel{
	JPanel panel1 = new MyPanel1(); //���� �г�
	JPanel panel2 = new MyPanel2(); //������ �г�
	
	public GradeInputPanel() {
		setLayout(null);
		
		panel1.setSize(800,950);
		panel1.setLocation(0,0);
		panel2.setSize(1100,950);
		panel2.setLocation(800,0);
		
		add(panel1);
		add(panel2);
	}
}
