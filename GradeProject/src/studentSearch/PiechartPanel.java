package studentSearch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import studentPackage.Student;

//�л� �˻� ���� ���� �г�
public class PiechartPanel extends JPanel {
	
	public static PiechartPanel singleton = new PiechartPanel(); //�̱��� ����
	JLabel titleLabel; //��� ���� ��

	Student student = null; //�˻��� �л�
	static List<Integer> gradeArcAngleList;// = new ArrayList<>(); //�л��� ���� ������ ���� ArcAngle�� �����س��� ����Ʈ
	static List<Integer> gradeRatioList;// = new ArrayList<>(); //�л��� ���� ������ �����س��� ����Ʈ
	
	Color[] colors = {
		new Color(0x9c88ff),new Color(0x00cec9),new Color(0xfdcb6e),new Color(0xff7675),new Color(0xfd79a8)
	};
	//������
	private PiechartPanel() {
		setLayout(null);
		 
		//��� ����� ��ġ
		titleLabel = new JLabel("< ���� ���� ���� Chart >",SwingConstants.CENTER);
		titleLabel.setFont(new Font("���ʷҵ���", Font.ITALIC, 30));
		//setBorder(new TitledBorder(null,titleLabel.getText(), TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		setBorder(new TitledBorder(new LineBorder(Color.GRAY,2)));
		titleLabel.setSize(800,50);
		titleLabel.setLocation(this.getWidth()+150,10);
		add(titleLabel);
		
		//�ʱ� ������Ʈ�� �׸��� ���� �ڵ�
		gradeArcAngleList = new ArrayList<>(); 
		gradeRatioList = new ArrayList<>();
		for(int i=0;i<5;i++) {
			gradeArcAngleList.add(0);
			gradeRatioList.add(0);
		}
		
		//������Ʈ �׸���
		drawSearchedPiechart(student);
		
	}
	
	//�˻��� �л��� ���� ������Ʈ�� �׸��� �޼ҵ�
	public static void drawSearchedPiechart(Student student) {
		
		if(student == null) //�ʱ⿡ �׷����� �ƹ��͵� �׸��� �ʴ´�.
			return;
		else { //�����ʿ� ���ؼ� �˻��� �л��� �Ķ���ͷ� ������ �� �л��� ������ ������Ʈ�� �׷��ش�.
			
			//�л��� �������� ����Ʈ�� �־���� ���
			List<Integer> gradeList = new ArrayList<>();
			gradeList.add(student.koreanGrade);
			gradeList.add(student.englishGrade);
			gradeList.add(student.mathGrade);
			gradeList.add(student.societyGrade);
			gradeList.add(student.scienceGrade);
			
			//�л��� ������ ���� ���
			double totalGrades =gradeList.stream()
										.mapToInt(Integer :: intValue)
										.sum();
			//�л��� �������� �Է��� �ȵȰ�쿡�� �ƹ��͵� �׸��� �ʴ´�.
			if(totalGrades == 0) { 
				JOptionPane.showMessageDialog(null, "�����Է� �ǿ��� ���� ������ �Է��ϼ���.", "���",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			//�л��� �������� �Էµ� ��쿡�� ����� ������Ʈ�� �߽ɰ��� ����Ѵ�.
			gradeArcAngleList.clear();
			gradeRatioList.clear();
			
			gradeList.stream()
					 .map(score -> (int)Math.round((double)score/totalGrades*360))
					 .forEach(score -> gradeArcAngleList.add(score)); //����� arc angle ����ؼ� ����
			gradeArcAngleList.stream()
							.map(arc -> Math.round(arc*100/360))
							.forEach(rate -> gradeRatioList.add(rate)); //����� ���� ����ؼ� ����
		    singleton.repaint(); // ������Ʈ�� ����Ʈ�Ѵ�. 
		}

	}
	
	//������Ʈ�� ���� paint�ϴ� �޼ҵ�
	public void paintComponent(Graphics g){
		 
		super.paintComponent(g);//�θ� ����Ʈȣ��

		int startAngle = 0;
		//piechart�׸���
		for(int index=0;index<gradeArcAngleList.size();index++){
			g.setColor(colors[index]);
			//������ ������� ��ĥ������ 360������ �� ������ ��� ���� ������ ��ĥ�Ѵ�.
			if(index == gradeArcAngleList.size()-1) {
				g.fillArc(225,100,650,650,startAngle,360-startAngle);
			}
			else {
				g.fillArc(225,100,650,650,startAngle,gradeArcAngleList.get(index));
			}
			startAngle = startAngle + gradeArcAngleList.get(index);
			
		}
		
		//�ϴ��� �� ���� ������ �����ִ� ���ڿ�
		String[] subjectNames = { "�� ��","�� ��","�� ��","�� ȸ","�� ��"};
		for(int index=0;index<gradeRatioList.size();index++){ 
			g.setColor(colors[index]);
			g.setFont(new Font("���ʷҵ���",Font.BOLD,25));
			g.drawString(subjectNames[index]+" : "+gradeRatioList.get(index)+"%",150+index*180,900); 
		}
	}
}
