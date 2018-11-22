package gradeInput;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

import studentPackage.*;


public class TablePanel extends JPanel {
	String columnNames[] = { "학번", "이름", "국어", "영어", "수학", "사회", "과학" }; //JTable의 column이름들
	//Vector<String> columnNames = new Vector(Arrays.asList(columnNamesArray));
	Vector<Student> studentsDB = StudentsDatabase.getStudentsDatabase(); //학생정보 벡터
	Object[][] rowData = new Object[studentsDB.size()][columnNames.length]; //테이블의 데이터배열
	JTable table; //학생정보를 보여줄 테이블
	JScrollPane scrollPane;
	
	public TablePanel() {
		setLayout(null);
		
		//rowData배열에 studentDB의 속성값들을 저장
		for (int i = 0; i < studentsDB.size(); i++) {
			rowData[i][0] = studentsDB.get(i).getStudentID();
			rowData[i][1] = studentsDB.get(i).getName();
			
			/** 이 부분은 성적이 입력되면 **/
			/*
			rowData[i][2] = studentsDB.get(i).koreaGrade;
			rowData[i][3] = studentsDB.get(i).englishGrade;
			rowData[i][4] = studentsDB.get(i).mathGrade;
			rowData[i][5] = studentsDB.get(i).societyGrade;
			rowData[i][6] = studentsDB.get(i).scienceGrade;
			*/
		}
		
		table = new JTable(rowData, columnNames); //테이블 생성
		
		
		scrollPane = new JScrollPane(table); //테이블에 스크롤바 생성
		scrollPane.setSize(1100,940);
		scrollPane.setLocation(0,0);
		
		
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(10); //학번셀 너비 지정
		tcm.getColumn(1).setPreferredWidth(100); //이름셀 너비 지정
		table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(),30)); //헤더 높이 지정
		table.setRowHeight(40); //모든 셀의 높이 지정
		table.setFont(new Font("Gothic",Font.PLAIN,20)); //폰트 지정
		//table.setEnabled(false); //비활성화
		
		
		//셀 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		//전체 셀에 적용
		for(int i = 0 ; i < tcm.getColumnCount() ; i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
		
		
		add(scrollPane);
		
	}
}
