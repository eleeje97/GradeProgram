package gradeAnalysis;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

import studentPackage.Student;
import studentPackage.StudentsDatabase;

import java.util.*;

public class SubjectPanel extends JPanel{
	JRadioButton[] b;
	String[] column = {"학번", "이름", "점수", "학점"};
	Object[][] rowData = new Object[studentDatabase.size()][column.length]; //테이블의 데이터배열;
	JTable table = new JTable(rowData,column); //JTable 추가
	JLabel sum = new JLabel();
	JLabel average = new JLabel();
	//불러온 점수의 합과 평균을 저장한다.
	int[] totalSum;
	double[] totalAverage;
	
	private static Vector<Student> studentDatabase = StudentsDatabase.getStudentsDatabase();//학생데이터베이스 정보를 가지고왔다
	
	public SubjectPanel() {
		//과목
		String[] subject = {"국어", "영어", "수학", "사회", "과학", "전체"};
		//panel1의 레이아웃
		setLayout(new BorderLayout());
		setBorder(new TitledBorder(new LineBorder(Color.darkGray,2),"과목별")); //테두리
		//RadioBoxChecked MyIL = new RadioBoxChecked();
		
		//과목선택- 라디오버튼 을 담을 패널 = p1
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		//p1.setBackground(Color.red);
		JLabel selectedSubject = new JLabel("과목선택");
		selectedSubject.setFont(new Font("궁서체", Font.PLAIN, 30 ));
		//selectedSubject.setOpaque(true);
		//selectedSubject.setBackground(Color.WHITE); 
		p1.add(selectedSubject); //라디오 그릅 부착
		ButtonGroup subjects = new ButtonGroup();
		
		b = new JRadioButton[subject.length];
		//라디오 버튼 추가하기
		for(int i = 0; i< subject.length; i++) {
			b[i] = new JRadioButton(subject[i]);
			b[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			subjects.add(b[i]); //라디오그룹에 부착
			b[i].addItemListener(new RadioButtonListener());
			p1.add(b[i]); 
		}
		b[0].setSelected(true);
		add(p1, BorderLayout.NORTH); //panel1의 상단에 위치
		
		
		
		//테이블과 p3패널을 담을 패널 = p2
		JPanel p2 = new JPanel();
		JPanel p5 = new JPanel(); //콤보박스를 붙이기 위한 패널
		p2.setLayout(new BorderLayout());
		//p2.setBackground(Color.blue);
		p5.setLayout(new BorderLayout());
		
		
		//rowData배열에 studentDB의 속성값들을 저장
		for (int i = 0; i < studentDatabase.size(); i++) {
			rowData[i][0] = studentDatabase.get(i).getStudentID();
			rowData[i][1] = studentDatabase.get(i).getName();
			rowData[i][2] = studentDatabase.get(i).koreanGrade;
		}
		
		//table.setBackground(new Color(255,255,204)); //테이블 필드 배경색
		//셀크기 지정
		TableColumnModel tcm = table.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(10); //학번셀 너비 지정
		tcm.getColumn(1).setPreferredWidth(100); //이름셀 너비 지정
		//테이블안 내용을 가운데 정렬하기 위해서 생성
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {

			tcmSchedule.getColumn(i).setCellRenderer(dtcr);

			}

		//테이블헤더 글씨크기
		table.getTableHeader().setFont(new Font("돋움체", Font.BOLD, 20));
		table.setRowHeight(40); //모든 셀의 높이 지정
		table.setFont(new Font("돋움체", Font.PLAIN, 20));//테이블내용글자 크기 변경
		JScrollPane scroll = new JScrollPane(table); //스크롤 바 달기
		p2.add(scroll,BorderLayout.CENTER); //p2패널의 가운데 테이블 추가	
		add(p2, BorderLayout.CENTER); //panel1의 가운데 위치
		
		//콤보박스 이용해서 정렬방법
		String[] sort = {"학번순","이름순","점수순"};
		JComboBox<String> sortbox = new JComboBox<String>(sort);
		sortbox.setFont(new Font("돋움체", Font.BOLD, 20));
		JPanel p6 = new JPanel();
		p6.setLayout(new BorderLayout());
		p6.add(sortbox, BorderLayout.CENTER);
		p6.add(new JLabel("          "), BorderLayout.EAST);
		p5.add(p6, BorderLayout.EAST);
		p5.add(new JLabel("          "), BorderLayout.CENTER);
		//p5.setBackground(Color.green);
		p2.add(p5, BorderLayout.NORTH);
		
		
		//테이블 옆에 빈공간 넣기 위해 라벨 두개 붙임
		p2.add(new JLabel("          "),BorderLayout.WEST); 
		p2.add(new JLabel("          "),BorderLayout.EAST);
		
		
		
		//과목의 총합 과 과목의 평균 & 파일로 저장 버튼을 담는 패널 = p3
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		p3.setLayout(new GridLayout(2,3,0,2));
		p4.setLayout(new BorderLayout());
		//p3.setBackground(Color.YELLOW);
		JLabel subjectSum = new JLabel("과목의 총합",SwingConstants.CENTER);
		subjectSum.setFont(new Font("굴림체",Font.BOLD, 15));
		p3.add(subjectSum);
		sum.setOpaque(true);
		sum.setBackground(Color.LIGHT_GRAY);
		p3.add(sum);
		p3.add(new JLabel("")); //줄 맞추기위해 추가
		
		JLabel subjectAverage = new JLabel("과목의 평균",SwingConstants.CENTER);
		subjectAverage.setFont(new Font("굴림체",Font.BOLD, 15));
		JButton fileStore = new JButton("파일로 저장");
		p3.add(subjectAverage);
		average.setOpaque(true);
		average.setBackground(Color.LIGHT_GRAY);
		p3.add(average);
		p3.add(fileStore);
		
		p4.add(new JLabel("          "),BorderLayout.WEST);
		p4.add(new JLabel("          "),BorderLayout.EAST);
		p4.add(p3, BorderLayout.CENTER);
		p2.add(p4, BorderLayout.SOUTH); //p2패널의 하단에 p3 패널 추가
		
	}

	class RadioButtonListener implements ItemListener{
		
		//getSumSubject와 getAverageSubject 메소드가 int배열을 return함으로 배열로 받는다.
		
		
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			JRadioButton radiobutton = (JRadioButton)e.getSource();
			
			totalSum = CalculateGrade.getSumBySubject();
			totalAverage = CalculateGrade.getAverageBySubject();
			
			if(radiobutton == b[0]) { //국어과목의 라디오버튼이 체크되었을 때
				for (int i = 0; i < studentDatabase.size(); i++) {
					rowData[i][2] = studentDatabase.get(i).koreanGrade;
					rowData[i][3] = studentDatabase.get(i).grade[0];
				}
				sum.setText(Integer.toString(totalSum[0]));
				average.setText(Double.toString(totalAverage[0]));
				//sum.setText(Integer.toString(CalculateGrade.getSumBySubject()[0]));
				//average.setText(Integer.toString(CalculateGrade.getAverageBySubject()[0]));
			}
			else if(radiobutton == b[1]) {
				for (int i = 0; i < studentDatabase.size(); i++) {
					rowData[i][2] = studentDatabase.get(i).englishGrade;
					rowData[i][3] = studentDatabase.get(i).grade[1];
				}
				sum.setText(Integer.toString(totalSum[1]));
				average.setText(Double.toString(totalAverage[1]));
				//sum.setText(Integer.toString(CalculateGrade.getSumBySubject()[1]));
				//average.setText(Integer.toString(CalculateGrade.getAverageBySubject()[1]));
			}
			else if(radiobutton == b[2]) {
				for (int i = 0; i < studentDatabase.size(); i++) {
					rowData[i][2] = studentDatabase.get(i).mathGrade;
					rowData[i][3] = studentDatabase.get(i).grade[2];
				}
				sum.setText(Integer.toString(totalSum[2]));
				average.setText(Double.toString(totalAverage[2]));
				//sum.setText(Integer.toString(CalculateGrade.getSumBySubject()[2]));
				//average.setText(Integer.toString(CalculateGrade.getAverageBySubject()[2]));
			}
			else if(radiobutton == b[3]) {
				for (int i = 0; i < studentDatabase.size(); i++) {
					rowData[i][2] = studentDatabase.get(i).societyGrade;
					rowData[i][3] = studentDatabase.get(i).grade[3];
				}
				sum.setText(Integer.toString(totalSum[3]));
				average.setText(Double.toString(totalAverage[3]));
				//sum.setText(Integer.toString(CalculateGrade.getSumBySubject()[3]));
				//average.setText(Integer.toString(CalculateGrade.getAverageBySubject()[3]));
			}
			else if(radiobutton == b[4]) {
				for (int i = 0; i < studentDatabase.size(); i++) {
					rowData[i][2] = studentDatabase.get(i).scienceGrade;
					rowData[i][3] = studentDatabase.get(i).grade[4];
				}
				sum.setText(Integer.toString(totalSum[4]));
				average.setText(Double.toString(totalAverage[4]));
				//sum.setText(Integer.toString(CalculateGrade.getSumBySubject()[4]));
				//average.setText(Integer.toString(CalculateGrade.getAverageBySubject()[4]));
			}
			else if(radiobutton == b[5]) {
				CalculateGrade.getSum_AverageByStudent();
				String[] column = {"학번", "이름", "학점", "학점"};
				JTable table = new JTable(rowData,column);
				for (int i = 0; i < studentDatabase.size(); i++) 
					rowData[i][2] = studentDatabase.get(i).average;
				//sum.setText(Integer.toString(CalculateGrade.getSumBySubject()[4]));
				//average.setText(Integer.toString(CalculateGrade.getAverageBySubject()[4]));
			}
			
			table.updateUI();
			
		}
		
	}
	
	//콤보박스 리스너
	//학번순 이름순 점수순으로 정렬한다.
	class combBoxListener implements ItemListener{
		@Override
		public void itemStateChanged(ItemEvent e) {
			JComboBox<String> combo = (JComboBox<String>)e.getSource();
			
			int index = combo.getSelectedIndex();
			if(index == 1) { //학번순이 선택된다면
			//그대로 출력하면 됨	
					
			
			}
			else if(index == 2) {//이름순이 선택되었다면
				
			}
			else if(index == 3) {//점수순이 선택되었다면
				
			}
		}
	}
}
