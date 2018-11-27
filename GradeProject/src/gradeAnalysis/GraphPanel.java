package gradeAnalysis;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import studentPackage.Student;
import studentPackage.StudentsDatabase;

import java.awt.event.*;
import java.util.Vector;

public class GraphPanel extends JPanel{
	static JPanel subjectgraph = new SubjectGraph();
	static JPanel totalgraph = new TotalGraph();
	static Color[] colors = {new Color(0x9c88ff),new Color(0x00cec9),new Color(0xfdcb6e),new Color(0xff7675),new Color(0xfd79a8)};
	static int index;
	static int[] count = new int[5];
	public GraphPanel() {
		setLayout(new BorderLayout(5,0));
		setBorder(new TitledBorder(new LineBorder(Color.darkGray,2),"과목별 점수 분포도")); //테두리
		JLabel label = new JLabel("<과목별 점수 분포도>",SwingConstants.CENTER);
		label.setFont(new Font("궁서체", Font.ITALIC, 30));
		add(label,BorderLayout.NORTH);
		//막대그래프 그리기
		add(subjectgraph, BorderLayout.CENTER); //그래프만을 담을 패널을 큰 패널의 가운데 붙인다.
		
				
		
	}
	
	public static void paintGraph(Object[][] rowData, int n) {
		 index=n;
		 countScore(rowData);
		 subjectgraph.repaint();
	}
	
	public static void countScore(Object[][] rowData) {
		//점수범위에 따른 학생수를 카운트한다.
		int score;
		for (int i = 0; i < count.length; i++)
			count[i] = 0;

		for(int i=0; i< rowData.length; i++) {
			score = (int)rowData[i][2];
			if(score<20) 
				count[0]++;
			else if(score<40) 
				count[1]++;
			else if(score<60) 
				count[2]++;
			else if(score<80) 
				count[3]++;
			else if(score<=100) 
				count[4]++;
		
		}
	}
}
//라디오버튼을 눌렀을 때 과목별 그래프 표현
class SubjectGraph extends JPanel{
	JLabel[] studentCount = new JLabel[5];
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		  super.paintComponent(g);
		 
		  //전체 축
		  g.drawLine(80, 800, 900, 800);
	      g.drawLine(80, 800, 80, 80);
	      
	      
	      //x축
	      int width = 0;
	      g.drawString("학생수", 65, 70);
	      for (int x = 0; x <= 100; x += 20) {
	        g.drawLine(80 + width, 800, 80 + width, 820);
	        g.drawString("" + x, 75 + width, 840);
	        width +=160;
	      }
	     
	      //y축
	      int height=0;
	      g.drawString("점수", 915, 805);
	      for (int y = 0; y < 800; y += 160) {
	        g.drawLine(80, 800 - y, 55, 800 - y);
	        g.drawString("" +height , 30, 805 - y);
	        height += 5;
	      }
	      
	      
	      
	      
	      //막대그래프	      
	      g.drawRect(120, 800-GraphPanel.count[0]*32, 80,GraphPanel.count[0]*32);
	      g.drawRect(280, 800-GraphPanel.count[1]*32, 80,GraphPanel.count[1]*32);
	      g.drawRect(440, 800-GraphPanel.count[2]*32, 80,GraphPanel.count[2]*32);
	      g.drawRect(600, 800-GraphPanel.count[3]*32, 80,GraphPanel.count[3]*32);
	      g.drawRect(760, 800-GraphPanel.count[4]*32, 80,GraphPanel.count[4]*32);
	      
	      
	      //그래프 위에 학생수 표시하기
	      int interval = 0;
	      for (int i = 0; i < GraphPanel.count.length; i++) {
	    	g.setColor(Color.BLACK);
			g.drawString(""+GraphPanel.count[i], 157 + interval, (800-GraphPanel.count[i]*32)-5);
			interval +=160;
	      }
	      
	      //그래프 색채우기
	      g.setColor(GraphPanel.colors[GraphPanel.index]);
	      g.fillRect(120, 800-GraphPanel.count[0]*32, 80,GraphPanel.count[0]*32);
	      g.fillRect(280, 800-GraphPanel.count[1]*32, 80,GraphPanel.count[1]*32);
	      g.fillRect(440, 800-GraphPanel.count[2]*32, 80,GraphPanel.count[2]*32);
	      g.fillRect(600, 800-GraphPanel.count[3]*32, 80,GraphPanel.count[3]*32);
	      g.fillRect(760, 800-GraphPanel.count[4]*32, 80,GraphPanel.count[4]*32);
	}
}
//라디오 버튼 중 전체가 눌렸을 때 나타나는 그래프
class TotalGraph extends JPanel{
	
	@Override
	public void paintComponent(Graphics g) {
		  super.paintComponent(g);
		 
		  //전체 축
		  g.drawLine(80, 800, 900, 800);
	      g.drawLine(80, 800, 80, 80);
	      
	      int width = 80;
	      g.drawString("학생수", 65, 70);
	      for (int x = 0; x <= 90; x += 20) {
	        g.drawLine(80 + width, 800, 80 + width, 820);
	       // g.drawString("" + x, 75 + width, 840);
	        width +=160;
	      }
	      g.drawString("국어", 150,  838);
	      g.drawString("영어", 310,  838);
	      g.drawString("수학", 470,  838);
	      g.drawString("사회", 630,  838);
	      g.drawString("과학", 790,  838);
	     
	      //y축
	      int height=0;
	      g.drawString("점수", 915, 805);
	      for (int y = 0; y < 650; y += 64) {
	        g.drawLine(80, 800 - y, 60, 800 - y);
	        g.drawString("" +height , 35, 805 - y);
	        height += 10;
	      }
	      
	      
	      //막대그래프	      
	      g.drawRect(120, 800-GraphPanel.count[0]*32, 80,GraphPanel.count[0]*32);
	      g.drawRect(280, 800-GraphPanel.count[1]*32, 80,GraphPanel.count[1]*32);
	      g.drawRect(440, 800-GraphPanel.count[2]*32, 80,GraphPanel.count[2]*32);
	      g.drawRect(600, 800-GraphPanel.count[3]*32, 80,GraphPanel.count[3]*32);
	      g.drawRect(760, 800-GraphPanel.count[4]*32, 80,GraphPanel.count[4]*32);
	      
	    //그래프 위에 학생수 표시하기
	      int interval = 0;
	      for (int i = 0; i < GraphPanel.count.length; i++) {
	    	g.setColor(Color.BLACK);
			g.drawString(""+GraphPanel.count[i], 157 + interval, (800-GraphPanel.count[i]*32)-5);
			interval +=160;
	      }
	      //그래프 색
	      g.setColor(new Color(0x74b9ff));
	      g.fillRect(120, 800-GraphPanel.count[0]*32, 80,GraphPanel.count[0]*32);
	      g.fillRect(280, 800-GraphPanel.count[1]*32, 80,GraphPanel.count[1]*32);
	      g.fillRect(440, 800-GraphPanel.count[2]*32, 80,GraphPanel.count[2]*32);
	      g.fillRect(600, 800-GraphPanel.count[3]*32, 80,GraphPanel.count[3]*32);
	      g.fillRect(760, 800-GraphPanel.count[4]*32, 80,GraphPanel.count[4]*32);
	}
}



