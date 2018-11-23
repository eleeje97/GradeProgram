package gradeInput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import studentPackage.*;

public class ReadGrade {
	static Vector<Student> studentsDatabase = StudentsDatabase.getStudentsDatabase();
	
	
	//grade_info.txt파일을 읽어서 studentsDatabase에 학생 성적 속성에 값을 넣어주는 메소드
	public static void readGradeInfoFile() {
		File gradeInfoFile = new File("grade_info.txt");
		Scanner scanner;
		int index = 0;
		try {
			scanner = new Scanner(gradeInfoFile);
			// 입력받을 인자가 계속 있다면 반복
			while(scanner.hasNext()) {
				Student s = studentsDatabase.get(index);
				scanner.nextInt(); //학번
				s.koreaGrade = scanner.nextInt(); //국어성적
				s.englishGrade = scanner.nextInt(); //영어성적
				s.mathGrade = scanner.nextInt(); //수학성적
				s.societyGrade = scanner.nextInt(); //사회성적
				s.scienceGrade = scanner.nextInt(); //과학성적
				
				index++;
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
