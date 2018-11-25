package studentSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import java.util.Vector;

import javax.swing.JOptionPane;

import studentPackage.Student;
import studentPackage.StudentsDatabase;

//'�˻�'��ư�� ������ �˻��� �л������� �����ִ� ������
public class ShowSearchResultListener implements ActionListener {
	//�л� �����ͺ��̽�
	Vector<Student> studentsDatabase = StudentsDatabase.getStudentsDatabase();
	
	//�˻� ��ư�� ������ �ش� �л��� �ִ��� �˻��Ѵ�.
	@Override
	public void actionPerformed(ActionEvent e) {
		String searchTarget = SearchPanel.searchField.getText(); // �˻��ʵ忡 �˻��� �ؽ�Ʈ�� �����ͼ�
		if (searchTarget.length() == 0) { // �ƹ��͵� �Է����� �ʾҴٸ� ���â����
			JOptionPane.showMessageDialog(null, "�˻��ϰ� ���� �л� �̸� �Ǵ� �й��� �Է��ϼ���.", "���",
					JOptionPane.WARNING_MESSAGE);
		} 
		else {
		// Java8 ��Ʈ��,���� ���
			Optional<Student> find = studentsDatabase.stream()
					.filter(s -> s.getName().equals(searchTarget) || s.getStudentID().equals(searchTarget))
					.findAny(); //ã���л��� find�� ����
			
			if (find.isPresent()) {// �˻��� �л��� database�� ������
				SearchResultPanel.showSearchedResult(find.get()); // ã�� �л��� �Ѱܼ� ǥ��  �л������� �����ش�.
				PiechartPanel.drawSearchedPiechart(find.get()); //ã�� �л��� �Ѱܼ� �л��� ������Ʈ�� �׸���.
			} else {// �˻��� �л��� database�� ������
				JOptionPane.showMessageDialog(null, "�л� �����ͺ��̽��� �˻��� �л��� �������� �ʽ��ϴ�.", "���",
						JOptionPane.WARNING_MESSAGE);
				SearchPanel.searchField.setText(""); //�˻�â�� �ʱ�ȭ ���ش�.
			}
		}
	}
}

