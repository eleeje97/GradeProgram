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
	Color[] color = {Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.PINK, Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.PINK};
	public GraphPanel() {
		setLayout(new BorderLayout(5,0));
		setBorder(new TitledBorder(new LineBorder(Color.darkGray,2),"과목별 점수 분포도")); //테두리
		JLabel label = new JLabel("<과목별 점수 분포도>",SwingConstants.CENTER);
		label.setFont(new Font("궁서체", Font.ITALIC, 30));
		add(label,BorderLayout.NORTH);
		
		//막대그래프 그리기
		JPanel graph = new MyGraph();
		add(graph, BorderLayout.CENTER); //그래프만을 담을 패널을 큰 패널의 가운데 붙인다.
		
				
		
	}
	
}
class MyGraph extends JPanel{
	
	@Override
	public void paintComponent(Graphics g) {
		  super.paintComponent(g);
		  //전체 축
		  g.drawLine(80, 800, 900, 800);
	      g.drawLine(80, 800, 80, 80);
	      
	      //x축
	      int width = 0;
	      g.drawString("학생수", 65, 70);
	      for (int x = 0; x <= 100; x += 20)
	      {
	        g.drawLine(80 + width, 800, 80 + width, 820);
	        g.drawString("" + x, 75 + width, 840);
	        width +=160;
	      }
	      
	      //y축
	      int count=0;
	      g.drawString("점수", 915, 805);
	      for (int y = 0; y < 800; y += 160)
	      {
	    	
	        g.drawLine(80, 800 - y, 55, 800 - y);
	        g.drawString("" +count , 30, 805 - y);
	        count += 10;
	      }
	 
	      //막대그래프
	      //시작x, 학생수, 80, x축과 학생수 차
	      for(int i = 0; i < 800; i +=160) {
	    	  g.drawRect(120 + i,400,80,400);
	    	  g.fillRect(120 + i, 400, 80, 400);
	    	 // g.setColor(color[i]);
	      }
	      //점수
	      int grade=0;
	      //점수가 90보다 크고 100보다 작다면
	      if(grade>90 && grade<=100) {
	    	  
	      }
	      else if(grade>80 && grade<=90) {
	    	  
	      }
	      else if(grade>70 && grade<=80) {
	    	  
	      }
	      else if(grade>60 && grade<=70) {
	    	  
	      }
	      else if(grade>50 && grade<=60) {
	    	  
	      }
	      else if(grade>40 && grade<=50) {
	    	  
	      }
	      else if(grade>30 && grade<=40) {
	    	  
	      }
	      else if(grade>20 && grade<=30) {
	    	  
	      }
	      else if(grade>10 && grade<=20) {
	    	  
	      }
	      else if(grade>=0 && grade<=10) {
	    	  
	      }
	      

	}
}