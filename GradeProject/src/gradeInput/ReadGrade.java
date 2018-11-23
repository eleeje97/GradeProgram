package gradeInput;

import java.io.File;
import java.io.IOException;
import java.util.*;

import studentPackage.*;

public class ReadGrade {
	Vector<Student> studentsDatabase = StudentsDatabase.getStudentsDatabase();
	
	//생성자
	private ReadGrade() {
		try {
			this.readGradeInfoFile("grade_info.txt");
		} catch(IOException e) {
			e.printStackTrace(); //자세한 예외 결과를 출력해주는 메소드
		}
	}
	
	//grade_info.txt파일을 읽어서 studentsDatabase에 학생 성적 속성에 값을 넣어주는 메소드
	public void readGradeInfoFile(String fileName) throws IOException{
		File gradeInfoFile = new File(fileName);
		Scanner scanner = new Scanner(gradeInfoFile);
		
		// 입력받을 인자가 계속 있다면 반복
		while(scanner.hasNext()) {
			int koreaGrade = scanner.nextInt();
			int mathGrade = scanner.nextInt();
			int societyGrade = scanner.nextInt();
			int scienceGrade = scanner.nextInt();
			int englishGrade = scanner.nextInt();
			
			//파일에서 읽어온 성적을 학생객체에 넣어준다.
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
