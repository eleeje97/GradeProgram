package studentPackage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

//�л������ͺ��̽��� ������ �ִ� Ŭ����
public class StudentsDatabase {
	
	//�л���ü���� ����Ǿ��ִ� �л� �����ͺ��̽� ����
	private static Vector<Student> studentsDatabase = new Vector<>();
	
	//�л� �����ͺ��̽��� �̱��������� ����
	public Vector<Student> getStudentsDatabase() {
		return studentsDatabase;
	}
	
	//student_info.txt ������ �о �л� �����ͺ��̽��� �����ϴ� �޼ҵ�
	public static void readStudentsInfoFile(String fileName) throws IOException{
		File studentsInfoFile = new File(fileName);
		Scanner scanner = new Scanner(studentsInfoFile);
		
		while(scanner.hasNext()) {
			String studentID = scanner.next();
			String name = scanner.next();
			String phoneNumber = scanner.next();
			
			Student student = new Student(studentID, name, phoneNumber);
			//�л� �����ͺ��̽��� ����
			studentsDatabase.add(student);
		}
		
	}
	
	/*test case
	//�л��������� �б�(�ʱ� �ѹ��� ����)
	public static void main(String[] args) throws IOException {
		
		studentsDatabase.clear(); //���� ��� �ʱ�ȭ
		
		readStudentsInfoFile("students_info.txt");
		
		for(Student s : studentsDatabase) {
			System.out.print(s.getName()+"/"+s.getStudentID()+"/"+s.getPhoneNumber());
			System.out.println("");
		}
	}
	 */
}
