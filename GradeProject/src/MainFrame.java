import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame{
	
	static int screenWidth,screenHeight;
	JTabbedPane tabPane = new JTabbedPane();  //JTabbedPane����
	JPanel gradeInputPanel = new GradeInputPanel(); //�����Է���
	JPanel gradeAnalysisPanel = new GradeAnalysisPanel(); //�����м���
	JPanel studentSearchPanel = new StudentSearchPanel(); //�л��˻��� 
	//������
	public MainFrame() {
		super("�л� ���� ���� ���α׷�"); //Ÿ��Ʋ ����
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�ǿ� �� ���� �޴��� �߰�
		tabPane.addTab("�����Է�", gradeInputPanel);
		tabPane.addTab("�����м�", gradeAnalysisPanel);
		tabPane.addTab("�л��˻�", studentSearchPanel);

		add(tabPane);
		
        setSize(1920, 1080);
		setVisible(true);
	}
}
