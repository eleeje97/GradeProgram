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

//학생 검색 탭의 우측 패널
public class PiechartPanel extends JPanel {
	
	public static PiechartPanel singleton = new PiechartPanel(); //싱글턴 적용
	JLabel titleLabel; //상단 제목 라벨

	Student student = null; //검색된 학생
	static List<Integer> gradeArcAngleList;// = new ArrayList<>(); //학생의 과목별 비율에 대한 ArcAngle을 저장해놓는 리스트
	static List<Integer> gradeRatioList;// = new ArrayList<>(); //학생의 과목별 비율을 저장해놓는 리스트
	
	Color[] colors = {
		new Color(0x9c88ff),new Color(0x00cec9),new Color(0xfdcb6e),new Color(0xff7675),new Color(0xfd79a8)
	};
	//생성자
	private PiechartPanel() {
		setLayout(null);
		 
		//상단 제목라벨 배치
		titleLabel = new JLabel("< 과목별 성적 비율 Chart >",SwingConstants.CENTER);
		titleLabel.setFont(new Font("함초롬돋움", Font.ITALIC, 30));
		//setBorder(new TitledBorder(null,titleLabel.getText(), TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		setBorder(new TitledBorder(new LineBorder(Color.GRAY,2)));
		titleLabel.setSize(800,50);
		titleLabel.setLocation(this.getWidth()+150,10);
		add(titleLabel);
		
		//초기 파이차트를 그리기 위한 코드
		gradeArcAngleList = new ArrayList<>(); 
		gradeRatioList = new ArrayList<>();
		for(int i=0;i<5;i++) {
			gradeArcAngleList.add(0);
			gradeRatioList.add(0);
		}
		
		//파이차트 그리기
		drawSearchedPiechart(student);
		
	}
	
	//검색된 학생에 대한 파이차트를 그리는 메소드
	public static void drawSearchedPiechart(Student student) {
		
		if(student == null) //초기에 그려질땐 아무것도 그리지 않는다.
			return;
		else { //리스너에 의해서 검색된 학생을 파라미터로 받으면 그 학생의 성적을 파이차트로 그려준다.
			
			//학생의 성적들을 리스트에 넣어놓고 사용
			List<Integer> gradeList = new ArrayList<>();
			gradeList.add(student.koreanGrade);
			gradeList.add(student.englishGrade);
			gradeList.add(student.mathGrade);
			gradeList.add(student.societyGrade);
			gradeList.add(student.scienceGrade);
			
			//학생의 과목성적 총점 계산
			double totalGrades =gradeList.stream()
										.mapToInt(Integer :: intValue)
										.sum();
			//학생의 과목성적이 입력이 안된경우에는 아무것도 그리지 않는다.
			if(totalGrades == 0) { 
				JOptionPane.showMessageDialog(null, "성적입력 탭에서 먼저 성적을 입력하세요.", "경고",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			//학생의 과목성적이 입력된 경우에는 과목당 파이차트의 중심각을 계산한다.
			gradeArcAngleList.clear();
			gradeRatioList.clear();
			
			gradeList.stream()
					 .map(score -> (int)Math.round((double)score/totalGrades*360))
					 .forEach(score -> gradeArcAngleList.add(score)); //과목당 arc angle 계산해서 저장
			gradeArcAngleList.stream()
							.map(arc -> Math.round(arc*100/360))
							.forEach(rate -> gradeRatioList.add(rate)); //과목당 비율 계산해서 저장
		    singleton.repaint(); // 파이차트를 페인트한다. 
		}

	}
	
	//파이차트를 직접 paint하는 메소드
	public void paintComponent(Graphics g){
		 
		super.paintComponent(g);//부모 페인트호출

		int startAngle = 0;
		//piechart그리기
		for(int index=0;index<gradeArcAngleList.size();index++){
			g.setColor(colors[index]);
			//마지막 과목까지 색칠했지만 360도까지 꽉 안차는 경우 남은 곳까지 색칠한다.
			if(index == gradeArcAngleList.size()-1) {
				g.fillArc(225,100,650,650,startAngle,360-startAngle);
			}
			else {
				g.fillArc(225,100,650,650,startAngle,gradeArcAngleList.get(index));
			}
			startAngle = startAngle + gradeArcAngleList.get(index);
			
		}
		
		//하단의 각 과목별 비율을 보여주는 문자열
		String[] subjectNames = { "국 어","영 어","수 학","사 회","과 학"};
		for(int index=0;index<gradeRatioList.size();index++){ 
			g.setColor(colors[index]);
			g.setFont(new Font("함초롬돋움",Font.BOLD,25));
			g.drawString(subjectNames[index]+" : "+gradeRatioList.get(index)+"%",150+index*180,900); 
		}
	}
}
