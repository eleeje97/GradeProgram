package studentPackage;
//�л� Ŭ����
public class Student {
	
	//�л� ����
	private String studentID; //�й�
	private String name; //�̸�
	private String phoneNumber; //��ȭ��ȣ
	
	//�л� ����
	public int koreaGrade; //���� ����
	public int mathGrade; //���м���
	public int englishGrade; //�����
	public int societyGrade; //��ȸ����
	public int scienceGrade; //���м���
	
	public String grade; //�л� ����
	public double average; //�л� ���
	
	//������
	public Student(String studentID, String name, String phoneNumber) {
		
		this.studentID = studentID;
		this.name = name;
		this.phoneNumber = phoneNumber;
		
	}
	
	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
