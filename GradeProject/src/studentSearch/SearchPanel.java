package studentSearch;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//학생 검색 탭의 좌측 패널
public class SearchPanel extends JPanel {
		
	public static JTextField searchField; //학생 검색 텍스트필드
	JButton searchButton; //검색 버튼
	
	JPanel searchResultPanel = new SearchResultPanel(); //학생 검색 결과를 보여주는 패널
	//생성자
	public SearchPanel() {
		setLayout(null);
		//학생 검색 텍스트필드 배치
		searchField = new JTextField("학생이름 또는 학번을 입력해주세요.");
		searchField.setFont(new Font("굴림체",Font.PLAIN,15));
		searchField.setSize(500,50);
		searchField.setLocation(90,100);
		searchField.addActionListener(new showSearchResultListener()); //엔터를 누르면 '검색'버튼 누른것과 동일한 효과
		searchField.addMouseListener(new setFieldClearListener()); //초기 텍스트필드를 지우고 검색받을 준비를 한다.
		add(searchField);
		
		//학생 검색 버튼 배치
		searchButton = new JButton("검 색");
		searchButton.setFont(new Font("굴림체",Font.BOLD,15));
		searchButton.setSize(80,50);
		searchButton.setLocation(620,100);
		searchButton.addActionListener(new showSearchResultListener()); //검색을 누르면 검색 결과보여주기
		//showSearchResultListener()는 SearchResultPanel.java 에 있음
		add(searchButton);
		
		//학생 검색 결과를 보여주는 패널 배치
		searchResultPanel = new SearchResultPanel();
		searchResultPanel.setSize(610,680);
		searchResultPanel.setLocation(90,200);
		add(searchResultPanel);
		
	}
	
}

//학생검색필드를 클릭하면 텍스트를 초기화시키고 사용자 검색을 받을 준비를 한다.
class setFieldClearListener extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		JTextField searchField=(JTextField)e.getSource();
		searchField.setText("");
	}
}

