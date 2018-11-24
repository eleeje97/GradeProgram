package studentPackage;
//학생 클래스
public class Student {
	
	//학생 정보
	private String studentID; //학번
	private String name; //이름
	private String phoneNumber; //전화번호
	
	//학생 성적
	public int koreaGrade; //국어 성적
	public int mathGrade; //수학성적
	public int englishGrade; //영어성적
	public int societyGrade; //사회성적
	public int scienceGrade; //과학성적
	
	public String grade; //학생 학점
	public double average; //학생 평균
	
	//생성자
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
