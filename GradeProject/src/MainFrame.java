import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame{
	
	static int screenWidth,screenHeight;
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

		add(tabPane);
		
        setSize(1920, 1080);
		setVisible(true);
	}
}
