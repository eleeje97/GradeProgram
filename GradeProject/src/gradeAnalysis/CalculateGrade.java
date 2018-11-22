package gradeAnalysis;
import java.io.File;
import java.io.IOException;
import java.util.*;
import studentPackage.*;
public class CalculateGrade {
	
	//�̹� ������� �л������ͺ��̽� ���� �����ͼ� studentDatabase�� ������ �ִ´�.
	private Vector<Student> studentDatabase = StudentsDatabase.getStudentsDatabase();
	
	//������
	private CalculateGrade() {
		try {
			this.readGradeInfoFile("grade_info.txt");
		}catch(IOException e) {
			e.printStackTrace(); //�ڼ��� ���� ����� ������ִ� �޼ҵ�
		}
	}
	
	//�޼ҵ�1 : grade_info.txt������ �о studentsDatabase�� �л� ���� �Ӽ��� ���� �־��ִ� �޼ҵ�
	public void readGradeInfoFile(String fileName) throws IOException{
		File GradeInfoFile = new File(fileName);
		Scanner scanner = new Scanner(GradeInfoFile);
		
		// �Է¹��� ���ڰ� ��� �ִٸ� �ݺ�
		while(scanner.hasNext()) {
			int koreaGrade = scanner.nextInt();
			int mathGrade = scanner.nextInt();
			int societyGrade = scanner.nextInt();
			int scienceGrade = scanner.nextInt();
			int englishGrade = scanner.nextInt();
			
			//���Ͽ��� �о�� ������ �л���ü�� �־��ش�.
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
	//�޼ҵ�2: studentsDatabase ������ ��� ������ ������ ��հ� ������ ����ϰ� �ٽ� studentsDatabase�� �л� ���� �Ӽ��� ���� �־��ִ� �޼ҵ�

