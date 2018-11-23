package gradeInput;

import java.io.File;
import java.io.IOException;
import java.util.*;

import studentPackage.*;

public class ReadGrade {
	Vector<Student> studentsDatabase = StudentsDatabase.getStudentsDatabase();
	
	//������
	private ReadGrade() {
		try {
			this.readGradeInfoFile("grade_info.txt");
		} catch(IOException e) {
			e.printStackTrace(); //�ڼ��� ���� ����� ������ִ� �޼ҵ�
		}
	}
	
	//grade_info.txt������ �о studentsDatabase�� �л� ���� �Ӽ��� ���� �־��ִ� �޼ҵ�
	public void readGradeInfoFile(String fileName) throws IOException{
		File gradeInfoFile = new File(fileName);
		Scanner scanner = new Scanner(gradeInfoFile);
		
		// �Է¹��� ���ڰ� ��� �ִٸ� �ݺ�
		while(scanner.hasNext()) {
			int koreaGrade = scanner.nextInt();
			int mathGrade = scanner.nextInt();
			int societyGrade = scanner.nextInt();
			int scienceGrade = scanner.nextInt();
			int englishGrade = scanner.nextInt();
			
			//���Ͽ��� �о�� ������ �л���ü�� �־��ش�.
			for(Student s : studentsDatabase) {
				s.koreaGrade = koreaGrade;
				s.mathGrade = mathGrade;
				s.societyGrade = societyGrade;
				s.scienceGrade = scienceGrade;
				s.englishGrade = englishGrade;
				
			}
		}
		scanner.close();
	}
}
