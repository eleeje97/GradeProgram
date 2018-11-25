package studentSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.Vector;

import javax.swing.JOptionPane;

import studentPackage.Student;
import studentPackage.StudentsDatabase;

//'검색'버튼이 눌리면 검색된 학생정보를 보여주는 리스너
public class ShowSearchResultListener implements ActionListener {
	//학생 데이터베이스
	Vector<Student> studentsDatabase = StudentsDatabase.getStudentsDatabase();
	
	//검색 버튼이 눌리면 해당 학생이 있는지 검사한다.
	@Override
	public void actionPerformed(ActionEvent e) {
		String searchTarget = SearchPanel.searchField.getText(); // 검색필드에 검색한 텍스트를 가져와서
		if (searchTarget.length() == 0) { // 아무것도 입력하지 않았다면 경고창띄우기
			JOptionPane.showMessageDialog(null, "검색하고 싶은 학생 이름 또는 학번을 입력하세요.", "경고",
					JOptionPane.WARNING_MESSAGE);
		} 
		else {
		// Java8 스트림,람다 사용
			Optional<Student> find = studentsDatabase.stream()
					.filter(s -> s.getName().equals(searchTarget) || s.getStudentID().equals(searchTarget))
					.findAny(); //찾은학생을 find에 리턴
			
			if (find.isPresent()) {// 검색한 학생이 database에 있으면
				SearchResultPanel.showSearchedResult(find.get()); // 찾은 학생을 넘겨서 표에  학생정보를 보여준다.
				PiechartPanel.drawSearchedPiechart(find.get()); //찾은 학생을 넘겨서 학생의 파이차트를 그린다.
			} else {// 검색한 학생이 database에 없으면
				JOptionPane.showMessageDialog(null, "학생 데이터베이스에 검색한 학생이 존재하지 않습니다.", "경고",
						JOptionPane.WARNING_MESSAGE);
				SearchPanel.searchField.setText(""); //검색창을 초기화 해준다.
			}
		}
	}
}

