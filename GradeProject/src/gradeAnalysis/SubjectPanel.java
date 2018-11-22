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
import java.util.*;

public class SubjectPanel extends JPanel{
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
		p1.setBackground(Color.red);
		JLabel selectedSubject = new JLabel("과목선택");
		selectedSubject.setFont(new Font("궁서체", Font.PLAIN, 30 ));
		selectedSubject.setOpaque(true);
		selectedSubject.setBackground(Color.WHITE); 
		p1.add(selectedSubject); //라디오 그릅 부착
		ButtonGroup subjects = new ButtonGroup();
		
		JRadioButton[] b = new JRadioButton[subject.length];
		//라디오 버튼 추가하기
		for(int i = 0; i< subject.length; i++) {
			b[i] = new JRadioButton(subject[i]);
			b[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			subjects.add(b[i]); //라디오그룹에 부착
			//b[i].addItemListener(MyIL);
			p1.add(b[i]); 
		}
		b[0].setSelected(true);
		add(p1, BorderLayout.NORTH); //panel1의 상단에 위치
		
		
		
		//테이블과 p3패널을 담을 패널 = p2
		JPanel p2 = new JPanel();
		JPanel p5 = new JPanel(); //콤보박스를 붙이기 위한 패널
		p2.setLayout(new BorderLayout());
		p2.setBackground(Color.blue);
		p5.setLayout(new BorderLayout());
		String[] colum = {"학번", "이름", "점수", "학점"};
		String[][] data = {{"A104596", "김덕성", "92", "A"},
							{"A104216", "박덕성", "87", "B"},
							{"A102296", "이덕성", "82", "B-"}};
		JTable table = new JTable(data,colum); //JTable 추가
		table.setBackground(new Color(255,255,204)); //테이블 필드 배경색

		//테이블안 내용을 가운데 정렬하기 위해서 생성
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = table.getColumnModel();
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {

			tcmSchedule.getColumn(i).setCellRenderer(dtcr);

			}

		//테이블헤더 글씨크기
		table.getTableHeader().setFont(new Font("돋움체", Font.BOLD, 20));
		table.setFont(new Font("돋움체", Font.PLAIN, 15));//테이블내용글자 크기 변경
		JScrollPane scroll = new JScrollPane(table); //스크롤 바 달기
		p2.add(scroll,BorderLayout.CENTER); //p2패널의 가운데 테이블 추가	
		add(p2, BorderLayout.CENTER); //panel1의 가운데 위치
		
		//콤보박스 이용해서 정렬방법
		String[] sort = {"학번순","오름차순","내림차순"};
		JComboBox sortbox = new JComboBox(sort);
		p5.add(sortbox, BorderLayout.CENTER);
		p5.add(new JLabel("     "), BorderLayout.EAST);
		p5.setBackground(Color.green);
		p2.add(p5, BorderLayout.NORTH);
		//테이블 옆에 빈공간 넣기 위해 라벨 두개 붙임
		
		p2.add(new JLabel("          "),BorderLayout.WEST); 
		p2.add(new JLabel("          "),BorderLayout.EAST);
		
		
		
		//과목의 총합 과 과목의 평균 & 파일로 저장 버튼을 담는 패널 = p3
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		p3.setLayout(new GridLayout(2,3,0,2));
		p4.setLayout(new BorderLayout());
		p3.setBackground(Color.YELLOW);
		JLabel subjectSum = new JLabel("과목의 총합",SwingConstants.CENTER);
		subjectSum.setFont(new Font("굴림체",Font.BOLD, 15));
		JLabel sum = new JLabel();
		p3.add(subjectSum);
		sum.setOpaque(true);
		sum.setBackground(Color.WHITE);
		p3.add(sum);
		p3.add(new JLabel("")); //줄 맞추기위해 추가
		
		JLabel subjectAverage = new JLabel("과목의 평균",SwingConstants.CENTER);
		subjectAverage.setFont(new Font("굴림체",Font.BOLD, 15));
		JLabel average = new JLabel();
		JButton fileStore = new JButton("파일로 저장");
		p3.add(subjectAverage);
		average.setOpaque(true);
		average.setBackground(Color.WHITE);
		p3.add(average);
		p3.add(fileStore);
		
		p4.add(new JLabel("          "),BorderLayout.WEST);
		p4.add(new JLabel("          "),BorderLayout.EAST);
		p4.add(p3, BorderLayout.CENTER);
		p2.add(p4, BorderLayout.SOUTH); //p2패널의 하단에 p3 패널 추가
		
	}
}
