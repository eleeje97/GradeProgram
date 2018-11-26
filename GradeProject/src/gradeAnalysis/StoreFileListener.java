package gradeAnalysis;

import java.awt.event.*;
import java.util.*;
import studentPackage.*;
import java.io.*;

public class StoreFileListener implements ActionListener {
	Vector<Student> db = StudentsDatabase.getStudentsDatabase();
	public void actionPerformed(ActionEvent e) {
		try {
			PrintWriter pw = new PrintWriter("grade.dat.txt");
			pw.println("�й� �̸� ���� ���� ���� ��ȸ ���� ��� ����");
			for (int i = 0; i < db.size(); i++) {
				Student s = db.get(i);
				pw.println(s.getStudentID()+" "+s.getName()+" "+s.koreanGrade+" "+s.englishGrade+" "+s.mathGrade+" "+s.societyGrade+" "+s.scienceGrade+" "+s.average+" "+s.totalGrade);
				
			}
			pw.close();
		} catch(IOException ex) {
			System.out.println("Error!");
		}
		
	}
}
