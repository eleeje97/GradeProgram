package gradeInput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import studentPackage.*;

public class ReadGrade {
	static Vector<Student> studentsDatabase = StudentsDatabase.getStudentsDatabase();
	
	
	//grade_info.txt������ �о studentsDatabase�� �л� ���� �Ӽ��� ���� �־��ִ� �޼ҵ�
	public static void readGradeInfoFile() {
		File gradeInfoFile = new File("grade_info.txt");
		Scanner scanner;
		int index = 0;
		try {
			scanner = new Scanner(gradeInfoFile);
			// �Է¹��� ���ڰ� ��� �ִٸ� �ݺ�
			while(scanner.hasNext()) {
				Student s = studentsDatabase.get(index);
				scanner.nextInt(); //�й�
				s.koreaGrade = scanner.nextInt(); //�����
				s.englishGrade = scanner.nextInt(); //�����
				s.mathGrade = scanner.nextInt(); //���м���
				s.societyGrade = scanner.nextInt(); //��ȸ����
				s.scienceGrade = scanner.nextInt(); //���м���
				
				index++;
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
