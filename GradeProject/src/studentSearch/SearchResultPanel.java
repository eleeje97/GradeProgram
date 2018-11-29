package studentSearch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import gradeAnalysis.CalculateGrade;
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
	
	static JLabel studentGradeAvailableLabel; //성적입력 탭에서 학생 성적이 입력되었는지 알려주는 라벨
	static JTable studentGradesTable; //검색된 학생 성적 테이블
	static String[] columnNames = { "과  목", "점  수", "학  점" };
	static Object[][] rowData = {
		{ "국 어", 0, "" },
		{ "영 어", 0, "" },
		{ "수 학", 0, "" },
		{ "사 회", 0, "" },
		{ "과 학", 0, "" },
		{ "평 균", 0, "" }
	};
		
	//생성자
	public SearchResultPanel() {
		setLayout(null);
		//setBorder(new TitledBorder(new LineBorder(Color.GRAY,2),"검색 결과"));
		//학생정보들의 제목 배치
		for(int labelCount=0; labelCount<fixedTitleLabel.length; labelCount++) {
			
			fixedTitleLabel[labelCount].setFont(new Font("함초롬돋움",Font.BOLD,20));
			fixedTitleLabel[labelCount].setSize(120,30);
			fixedTitleLabel[labelCount].setLocation(5,30+60*labelCount);
			add(fixedTitleLabel[labelCount]);
		}
		
		/*검색된 학생 정보 라벨 배치*/
		//1)학생 이름라벨
		studentNameLabel = new JLabel("");
		studentNameLabel.setFont(new Font("함초롬돋움",Font.PLAIN,20));
		studentNameLabel.setSize(120,30);
		studentNameLabel.setLocation(150,30);
		add(studentNameLabel);
		//2)학생 학번라벨
		studentIDLabel = new JLabel("");
		studentIDLabel.setFont(new Font("함초롬돋움",Font.PLAIN,20));
		studentIDLabel.setSize(120,30);
		studentIDLabel.setLocation(150,90);
		add(studentIDLabel);	
		//3)학생 번호라벨
		studentPhoneLabel = new JLabel("");
		studentPhoneLabel.setFont(new Font("함초롬돋움",Font.PLAIN,20));
		studentPhoneLabel.setSize(250,30);
		studentPhoneLabel.setLocation(150,150);
		add(studentPhoneLabel);	
		//4)성적이 입력되었는지를 알려주는 라벨
		studentGradeAvailableLabel = new JLabel("성적입력 탭에서 학생의 성적을 먼저 입력하세요.");
		studentGradeAvailableLabel.setFont(new Font("함초롬돋움",Font.BOLD,15));
		studentGradeAvailableLabel.setSize(500,30);
		studentGradeAvailableLabel.setLocation(150,210);
		studentGradeAvailableLabel.setVisible(false); //초기에는 안보이는 상태
		
		add(studentGradeAvailableLabel);

		
		//5)학생 성적 테이블 생성 및 배치
		
		studentGradesTable = new JTable(rowData, columnNames);
		studentGradesTable.setEnabled(false); //각 셀 편집 불가
		studentGradesTable.setRowHeight(50); //각 행들의 높이 조절
		studentGradesTable.getTableHeader().setPreferredSize(new Dimension(50,50)); //헤더의 높이 조절
		//테이블 내 글씨체 설정
		studentGradesTable.getTableHeader().setFont(new Font("함초롬돋움", Font.BOLD, 20)); //헤더글씨체
		studentGradesTable.setFont(new Font("함초롬돋움", Font.PLAIN, 20)); //셀 글씨체

		//각 셀의 글자 가운데 정렬 
		setTextAlignCenter(studentGradesTable);
		
		JPanel tablePanel = new JPanel(new BorderLayout());	//성적 테이블 붙일 패널 -> 없으면 헤더가 안보임
		tablePanel.setSize(601,350);
		tablePanel.setLocation(4,270);
		tablePanel.add(studentGradesTable, BorderLayout.CENTER);
		tablePanel.add(studentGradesTable.getTableHeader(), BorderLayout.NORTH);
		add(tablePanel);
				
	}
	//검색한 학생의 성적이 입력된 상태인지 확인하는 메소드
	//성적이 입력되지 않은 상태라면 false 성적이 입력된 상태라면 true반환
	public static boolean gradeAvailable() {
		boolean isAvailable = false;
		for(int rowCount=0;rowCount<rowData.length;rowCount++) {
			if(Double.parseDouble(""+rowData[rowCount][1]) != 0) { //한 과목이라도 0점이 아니면 입력된 상태로 본다
				isAvailable = true;
				return isAvailable;
			}	
		}
		return isAvailable; //모든 과목이 0 점이라면 입력되지 않은 상태로 본다.
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
		
		//학생의 성적 불러와서 테이블에 넣어주기
		rowData[0][1] = student.koreanGrade;
		rowData[1][1] = student.englishGrade;
		rowData[2][1] = student.mathGrade;
		rowData[3][1] = student.societyGrade;
		rowData[4][1] = student.scienceGrade;
		//학생의 평균 추가
		CalculateGrade.getSum_AverageByStudent(); //평균계산
		rowData[5][1] = student.average;

		
		//학생 학점 불러와서 테이블에 넣어주기
		CalculateGrade.calculateGrade(); //학점 계산해서 db에 저장
		String[] studentGrades = student.grade; //학생의  학점을 배열로 가져옴
		
		for(int subjectCount=0;subjectCount<rowData.length-1;subjectCount++){
			rowData[subjectCount][2] = studentGrades[subjectCount];
		}

		//총 학점평균 테이블에 넣어주기
		rowData[5][2] = student.totalGrade;

		//넣어준 테이블셀 값 업데이트 해주기
		studentGradesTable.updateUI();
		
		if(!gradeAvailable())//성적이 입력되지 않은 상태라면 성적을 먼저 입력하라는 라벨을 보인다.
			studentGradeAvailableLabel.setVisible(true);
		else //성적이 입력된 상태라면 라벨을 보이지 않는다.
			studentGradeAvailableLabel.setVisible(false);

	}
}




