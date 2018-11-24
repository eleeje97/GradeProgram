package studentSearch;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import studentPackage.Student;
import studentPackage.StudentsDatabase;
//학생검색 탭 좌측패널 중 학생 검색 결과를 보여주는 패널
public class SearchResultPanel extends JPanel {
	
	JLabel[] fixedTitleLabel = { new JLabel("이    름 :"),new JLabel("학    번 :"), 
			new JLabel("전화번호 :"), new JLabel("성    적 :")
	}; //이름,학과,학번,전화번호,성적 제목을 표시해주는 라벨
	static JLabel studentNameLabel; //검색된 학생 이름
	static JLabel studentIDLabel; //검색된 학생 학번
	static JLabel studentPhoneLabel; //검색된 학생 전화번호
	
	static JTable studentGradesTable; //검색된 학생 성적 테이블
	
	//생성자
	public SearchResultPanel() {
		setLayout(null);
		
		//학생정보들의 제목 배치
		for(int labelCount=0; labelCount<fixedTitleLabel.length; labelCount++) {
			
			fixedTitleLabel[labelCount].setFont(new Font("굴림체",Font.BOLD,20));
			fixedTitleLabel[labelCount].setSize(120,30);
			fixedTitleLabel[labelCount].setLocation(0,30+60*labelCount);
			add(fixedTitleLabel[labelCount]);
		}
		
		/*검색된 학생 정보 라벨 배치*/
		//1)학생이름라벨
		studentNameLabel = new JLabel("이름 초기값");
		studentNameLabel.setFont(new Font("굴림체",Font.PLAIN,20));
		studentNameLabel.setSize(120,30);
		studentNameLabel.setLocation(180,30);
		add(studentNameLabel);
		//2)학생학번라벨
		studentIDLabel = new JLabel("학번 초기값");
		studentIDLabel.setFont(new Font("굴림체",Font.PLAIN,20));
		studentIDLabel.setSize(120,30);
		studentIDLabel.setLocation(180,90);
		add(studentIDLabel);	
		//3)학생 번호라벨
		studentPhoneLabel = new JLabel("번호 초기값");
		studentPhoneLabel.setFont(new Font("굴림체",Font.PLAIN,20));
		studentPhoneLabel.setSize(250,30);
		studentPhoneLabel.setLocation(180,150);
		add(studentPhoneLabel);	
		
		//4)학생 성적 테이블 생성 및 배치
		String[] columnNames = { "과  목", "점  수", "학  점" };
		Object[][] rowData = {
			{ "국 어", 100 , "A" },
			{ "영 어", 100, "A" },
			{ "수 학", 100, "B" },
			{ "사 회", 100, "C" },
			{ "과 학", 100, "D" },
			{ "평 균", 100, "D"}
		};
		studentGradesTable = new JTable(rowData, columnNames);
		studentGradesTable.setEnabled(false); //각 셀 편집 불가
		studentGradesTable.setRowHeight(50); //각 행들의 높이 조절
		studentGradesTable.getTableHeader().setPreferredSize(new Dimension(50,50)); //헤더의 높이 조절
		//테이블 내 글씨체
		studentGradesTable.getTableHeader().setFont(new Font("돋움체", Font.BOLD, 20)); //헤더글씨체
		studentGradesTable.setFont(new Font("돋움체", Font.PLAIN, 20)); //셀 글씨체

		//각 셀의 글자 가운데 정렬 
		this.setTextAlignCenter(studentGradesTable);
		
		JPanel tablePanel = new JPanel(new BorderLayout());	//성적 테이블 붙일 패널 -> 없으면 헤더가 안보임
		tablePanel.setSize(610,350);
		tablePanel.setLocation(0,270);
		tablePanel.add(studentGradesTable, BorderLayout.CENTER);
		tablePanel.add(studentGradesTable.getTableHeader(), BorderLayout.NORTH);
		add(tablePanel);
		
	}
	
	//테이블의 각 글자 가운데 정렬해주는 메소드
	public void setTextAlignCenter(JTable table) {
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel columnModel = table.getColumnModel();
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int colCount = 0; colCount < columnModel.getColumnCount(); colCount++) {
			columnModel.getColumn(colCount).setCellRenderer(renderer);
		}
	}
	

	//찾은 학생정보를 컴포넌트들에 띄워주는 메소드
	public static void showSearchedResult(Student student) {
		studentNameLabel.setText(student.getName()); //이름 띄워주기
		studentIDLabel.setText(student.getStudentID()); //학번 띄워주기
		studentPhoneLabel.setText(student.getPhoneNumber()); //번호 띄워주기
	}
}



// '검색'버튼이 눌리면 검색된 학생정보를 보여주는 리스너
class showSearchResultListener implements ActionListener {
	
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
					.findAny();
			if (find.isPresent()) {// 검색한 학생이 database에 있으면
				SearchResultPanel.showSearchedResult(find.get()); // 찾은 학생을 넘긴다.
			} else {// 검색한 학생이 database에 없으면
				JOptionPane.showMessageDialog(null, "학생 데이터베이스에 검색한 학생이 존재하지 않습니다.", "경고",
						JOptionPane.WARNING_MESSAGE);
				SearchPanel.searchField.setText(""); //검색창을 초기화 해준다.
			}
		}
	}
}


