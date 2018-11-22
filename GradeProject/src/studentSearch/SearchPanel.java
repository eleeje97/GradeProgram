package studentSearch;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JPanel;

import studentPackage.StudentsDatabase;

public class SearchPanel extends JPanel {
	
	Vector db = StudentsDatabase.getStudentsDatabase(); 
	//»ý¼ºÀÚ
	public SearchPanel() {
		setBackground(Color.black);
		System.out.println(db);
	}
}
