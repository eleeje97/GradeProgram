package studentSearch;

import java.awt.Color;

import javax.swing.*;

//�л� �˻� ���� ��ü���� �г�
public class StudentSearchPanel extends JPanel {

	JPanel searchPanel = new SearchPanel(); // �����г� : �л� �˻� �г�
	JPanel piechartPanel = PiechartPanel.singleton ; // �����г� : ���� �������� ������Ʈ �г�

	// ������
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
