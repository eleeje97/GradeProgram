package gradeAnalysis;
import java.io.File;
import java.io.IOException;
import java.util.*;
import studentPackage.*;
public class CalculateGrade {
	
	//이미 만들어진 학생데이터베이스 값을 가져와서 studentDatabase에 내용을 넣는다.
	private Vector<Student> studentDatabase = StudentsDatabase.getStudentsDatabase();
	
	//생성자
	private CalculateGrade() {
		try {
			this.readGradeInfoFile("grade_info.txt");
		}catch(IOException e) {
			e.printStackTrace(); //자세한 예외 결과를 출력해주는 메소드
		}
	}
	
	//메소드1 : grade_info.txt파일을 읽어서 studentsDatabase에 학생 성적 속성에 값을 넣어주는 메소드
	public void readGradeInfoFile(String fileName) throws IOException{
		File GradeInfoFile = new File(fileName);
		Scanner scanner = new Scanner(GradeInfoFile);
		
		// 입력받을 인자가 계속 있다면 반복
		while(scanner.hasNext()) {
			int koreaGrade = scanner.nextInt();
			int mathGrade = scanner.nextInt();
			int societyGrade = scanner.nextInt();
			int scienceGrade = scanner.nextInt();
			int englishGrade = scanner.nextInt();
			
			//파일에서 읽어온 성적을 학생객체에 넣어준다.
			for(Student s : studentDatabase) {
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
	//메소드2: studentsDatabase 벡터의 모든 점수를 가져와 평균과 학점을 계산하고 다시 studentsDatabase에 학생 성적 속성에 값을 넣어주는 메소드

