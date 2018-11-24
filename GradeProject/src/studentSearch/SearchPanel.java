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
//�л� �˻� ���� ���� �г�
public class SearchPanel extends JPanel {
		
	public static JTextField searchField; //�л� �˻� �ؽ�Ʈ�ʵ�
	JButton searchButton; //�˻� ��ư
	
	JPanel searchResultPanel = new SearchResultPanel(); //�л� �˻� ����� �����ִ� �г�
	//������
	public SearchPanel() {
		setLayout(null);
		//�л� �˻� �ؽ�Ʈ�ʵ� ��ġ
		searchField = new JTextField("�л��̸� �Ǵ� �й��� �Է����ּ���.");
		searchField.setFont(new Font("����ü",Font.PLAIN,15));
		searchField.setSize(500,50);
		searchField.setLocation(90,100);
		searchField.addActionListener(new showSearchResultListener()); //���͸� ������ '�˻�'��ư �����Ͱ� ������ ȿ��
		searchField.addMouseListener(new setFieldClearListener()); //�ʱ� �ؽ�Ʈ�ʵ带 ����� �˻����� �غ� �Ѵ�.
		add(searchField);
		
		//�л� �˻� ��ư ��ġ
		searchButton = new JButton("�� ��");
		searchButton.setFont(new Font("����ü",Font.BOLD,15));
		searchButton.setSize(80,50);
		searchButton.setLocation(620,100);
		searchButton.addActionListener(new showSearchResultListener()); //�˻��� ������ �˻� ��������ֱ�
		//showSearchResultListener()�� SearchResultPanel.java �� ����
		add(searchButton);
		
		//�л� �˻� ����� �����ִ� �г� ��ġ
		searchResultPanel = new SearchResultPanel();
		searchResultPanel.setSize(610,680);
		searchResultPanel.setLocation(90,200);
		add(searchResultPanel);
		
	}
	
}

//�л��˻��ʵ带 Ŭ���ϸ� �ؽ�Ʈ�� �ʱ�ȭ��Ű�� ����� �˻��� ���� �غ� �Ѵ�.
class setFieldClearListener extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		JTextField searchField=(JTextField)e.getSource();
		searchField.setText("");
	}
}

