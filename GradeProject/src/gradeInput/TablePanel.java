package gradeInput;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;
import java.util.*;

import studentPackage.*;


public class TablePanel extends JPanel {
	static String columnNames[] = { "학번", "이름", "국어", "영어", "수학", "사회", "과학" }; //JTable의 헤더이름들
	static Vector<Student> studentsDB = StudentsDatabase.getStudentsDatabase(); //학생정보 벡터
	static Object[][] rowData = new Object[studentsDB.size()][columnNames.length]; //테이블의 데이터배열
	static JTable table; //학생정보를 보여줄 테이블
	JScrollPane scrollPane;
	
	public TablePanel() {
		setLayout(null);
		setBorder(new TitledBorder(new LineBorder(Color.GRAY,2)));
		//rowData배열에 studentDB의 속성값(학번, 이름)들을 저장
		for (int i = 0; i < studentsDB.size(); i++) {
			rowData[i][0] = studentsDB.get(i).getStudentID();
			rowData[i][1] = studentsDB.get(i).getName();
		}
		
		table = new JTable(rowData, columnNames); //테이블 생성
				
		
		scrollPane = new JScrollPane(table); //테이블에 스크롤바 생성
		scrollPane.setSize(1086,935);
		scrollPane.setLocation(7,12);
		
		
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(10); //학번셀 너비 지정
		tcm.getColumn(1).setPreferredWidth(100); //이름셀 너비 지정
		table.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(),30)); //헤더 높이 지정
		table.setRowHeight(40); //모든 셀의 높이 지정
		table.setFont(new Font("Gothic",Font.PLAIN,20)); //폰트 지정
		table.setEnabled(false); //비활성화
		
		
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
