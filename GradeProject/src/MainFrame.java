import java.awt.*;
import javax.swing.*;

import studentSearch.*;
import gradeInput.*;
import gradeAnalysis.*;


public class MainFrame extends JFrame{
	
	JTabbedPane tabPane = new JTabbedPane();  //JTabbedPane생성
	JPanel gradeInputPanel = new GradeInputPanel(); //성적입력탭
	JPanel gradeAnalysisPanel = new GradeAnalysisPanel(); //성적분석탭
	JPanel studentSearchPanel = new StudentSearchPanel(); //학생검색탭 
	
	//생성자
	public MainFrame() {
		super("학생 성적 관리 프로그램"); //타이틀 지정
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//탭에 세 가지 메뉴탭 추가
		tabPane.addTab("성적입력", gradeInputPanel);
		tabPane.addTab("성적분석", gradeAnalysisPanel);
		tabPane.addTab("학생검색", studentSearchPanel);

		//탭제목 크기 키우기
		JLabel label1 = new JLabel("성적입력");
		label1.setPreferredSize(new Dimension(60,30));
		tabPane.setTabComponentAt(0, label1);
		JLabel label2 = new JLabel("성적분석");
		label2.setPreferredSize(new Dimension(60,30));
		tabPane.setTabComponentAt(1, label2);
		JLabel label3 = new JLabel("학생검색");
		label3.setPreferredSize(new Dimension(60,30));
		tabPane.setTabComponentAt(2, label3);
		
		
		add(tabPane);
		
        //setSize(1920, 1080);
		setExtendedState(JFrame.MAXIMIZED_BOTH);//전체화면으로 출력하기
		setVisible(true);
	}
}
