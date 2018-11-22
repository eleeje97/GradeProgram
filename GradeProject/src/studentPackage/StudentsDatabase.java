package studentPackage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

//�л������ͺ��̽��� ������ �ִ� Ŭ����
public class StudentsDatabase {
	//�ڱ��ڽ��� �Ӽ����� ����
	private static StudentsDatabase singleton = new StudentsDatabase();
	
	//�л���ü���� ����Ǿ��ִ� �л� �����ͺ��̽� ����
	private Vector<Student> studentsDatabase;
	
	//�л� �����ͺ��̽��� �̱��������� ����
	public static Vector<Student> getStudentsDatabase() {
		return singleton.studentsDatabase;
	}
	
	private StudentsDatabase(){
		try {
			this.readStudentsInfoFile("students_info.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//student_info.txt ������ �о �л� �����ͺ��̽��� �����ϴ� �޼ҵ�
	public void readStudentsInfoFile(String fileName) throws IOException{
		studentsDatabase = new Vector<>();
		
		File studentsInfoFile = new File(fileName);
		Scanner scanner = new Scanner(studentsInfoFile);
		
		while(scanner.hasNext()) {
			String studentID = scanner.next();
			String name = scanner.next();
			String phoneNumber = scanner.next();
			
			Student student = new Student(studentID, name, phoneNumber);
			//�л� �����ͺ��̽��� ����
			singleton.studentsDatabase.add(student);
		}
		scanner.close();
	}
	
	//test case
	//�л��������� �б�(�ʱ� �ѹ��� ����)
	public static void main(String[] args) throws IOException {
		
		//singleton.studentsDatabase.clear(); //���� ��� �ʱ�ȭ
		
		//singleton.readStudentsInfoFile("students_info.txt");
		
		for(Student s : singleton.studentsDatabase) {
			System.out.print(s.getName()+"/"+s.getStudentID()+"/"+s.getPhoneNumber());
			System.out.println("");
		}
	}
	
}
