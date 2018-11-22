package studentSearch;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import studentPackage.Student;
import studentPackage.StudentsDatabase;
//학생 검색 탭의 좌측 패널
public class SearchPanel extends JPanel {
	
	//학생 정보 데이터베이스
	Vector<Student> studentsDatabase = StudentsDatabase.getStudentsDatabase(); 
	
	JTextField searchField; //학생 검색 텍스트필드
	JButton searchButton; //검색 버튼
	
	JPanel searchResultPanel = new SearchResultPanel(); //학생 검색 결과를 보여주는 패널
	//생성자
	public SearchPanel() {
		setLayout(null);
		setBackground(Color.black);
		//학생 검색 텍스트필드 배치
		searchField = new JTextField("학생이름 또는 학번을 입력해주세요.");
		searchField.setSize(500,50);
		searchField.setLocation(90,100);
		add(searchField);
		
		//학생 검색 버튼 배치
		searchButton = new JButton("검 색");
		searchButton.setSize(80,50);
		searchButton.setLocation(620,100);
		add(searchButton);
		
		//학생 검색 결과를 보여주는 패널 배치
		searchResultPanel = new SearchResultPanel();
		searchResultPanel.setSize(610,660);
		searchResultPanel.setLocation(90,200);
		add(searchResultPanel);
		
	}
}
