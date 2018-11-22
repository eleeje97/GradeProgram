package studentSearch;

import javax.swing.*;

//학생 검색 탭의 전체적인 패널
public class StudentSearchPanel extends JPanel {

	SearchPanel searchPanel = new SearchPanel(); // 좌측패널 : 학생 검색 패널
	PiechartPanel piechartPanel = new PiechartPanel(); // 우측패널 : 과목별 성적비율 파이차트 패널

	// 생성자
	public StudentSearchPanel() {
		setLayout(null);

		searchPanel.setSize(800,950);
		searchPanel.setLocation(0,0);
		piechartPanel.setSize(1100,950);
		piechartPanel.setLocation(800,0);
		
		add(searchPanel);
		add(piechartPanel);
	}
}
