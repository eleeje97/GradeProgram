package studentSearch;

import javax.swing.*;

//�л� �˻� ���� ��ü���� �г�
public class StudentSearchPanel extends JPanel {

	JPanel searchPanel = new SearchPanel(); // �����г� : �л� �˻� �г�
	JPanel piechartPanel = PiechartPanel.singleton ; // �����г� : ���� �������� ������Ʈ �г�

	// ������
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
