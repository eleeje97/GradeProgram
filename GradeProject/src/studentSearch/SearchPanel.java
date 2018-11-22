package studentSearch;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import studentPackage.Student;
import studentPackage.StudentsDatabase;
//�л� �˻� ���� ���� �г�
public class SearchPanel extends JPanel {
	
	//�л� ���� �����ͺ��̽�
	Vector<Student> studentsDatabase = StudentsDatabase.getStudentsDatabase(); 
	
	JTextField searchField; //�л� �˻� �ؽ�Ʈ�ʵ�
	JButton searchButton; //�˻� ��ư
	
	JPanel searchResultPanel = new SearchResultPanel(); //�л� �˻� ����� �����ִ� �г�
	//������
	public SearchPanel() {
		setLayout(null);
		setBackground(Color.black);
		//�л� �˻� �ؽ�Ʈ�ʵ� ��ġ
		searchField = new JTextField("�л��̸� �Ǵ� �й��� �Է����ּ���.");
		searchField.setSize(500,50);
		searchField.setLocation(90,100);
		add(searchField);
		
		//�л� �˻� ��ư ��ġ
		searchButton = new JButton("�� ��");
		searchButton.setSize(80,50);
		searchButton.setLocation(620,100);
		add(searchButton);
		
		//�л� �˻� ����� �����ִ� �г� ��ġ
		searchResultPanel = new SearchResultPanel();
		searchResultPanel.setSize(610,660);
		searchResultPanel.setLocation(90,200);
		add(searchResultPanel);
		
	}
}
