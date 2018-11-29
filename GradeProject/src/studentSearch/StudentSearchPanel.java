package studentSearch;

import java.awt.Color;

import javax.swing.*;

//학생 검색 탭의 전체적인 패널
public class StudentSearchPanel extends JPanel {

	JPanel searchPanel = new SearchPanel(); // 좌측패널 : 학생 검색 패널
	JPanel piechartPanel = PiechartPanel.singleton ; // 우측패널 : 과목별 성적비율 파이차트 패널

	// 생성자
	public StudentSearchPanel() {
		setLayout(null);
		
		searchPanel.setSize(810,971);
		searchPanel.setLocation(0,0);
		//searchPanel.setLocation(2,8);
		piechartPanel.setSize(1099,962);
		piechartPanel.setLocation(813,8);
		
		add(searchPanel);
		add(piechartPanel);
	}
}
