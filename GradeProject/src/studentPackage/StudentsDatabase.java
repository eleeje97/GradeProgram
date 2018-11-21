package studentPackage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

//학생데이터베이스를 가지고 있는 클래스
public class StudentsDatabase {
	
	//학생객체들이 저장되어있는 학생 데이터베이스 벡터
	private static Vector<Student> studentsDatabase = new Vector<>();
	
	//학생 데이터베이스는 싱글턴패턴을 적용
	public Vector<Student> getStudentsDatabase() {
		return studentsDatabase;
	}
	
	//student_info.txt 파일을 읽어서 학생 데이터베이스에 저장하는 메소드
	public static void readStudentsInfoFile(String fileName) throws IOException{
		File studentsInfoFile = new File(fileName);
		Scanner scanner = new Scanner(studentsInfoFile);
		
		while(scanner.hasNext()) {
			String studentID = scanner.next();
			String name = scanner.next();
			String phoneNumber = scanner.next();
			
			Student student = new Student(studentID, name, phoneNumber);
			//학생 데이터베이스에 저장
			studentsDatabase.add(student);
		}
		
	}
	
	/*test case
	//학생정보파일 읽기(초기 한번만 실행)
	public static void main(String[] args) throws IOException {
		
		studentsDatabase.clear(); //기존 디비 초기화
		
		readStudentsInfoFile("students_info.txt");
		
		for(Student s : studentsDatabase) {
			System.out.print(s.getName()+"/"+s.getStudentID()+"/"+s.getPhoneNumber());
			System.out.println("");
		}
	}
	 */
}
