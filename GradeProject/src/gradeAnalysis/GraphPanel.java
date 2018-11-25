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
		setBorder(new TitledBorder(new LineBorder(Color.darkGray,2),"���� ���� ������")); //�׵θ�
		JLabel label = new JLabel("<���� ���� ������>",SwingConstants.CENTER);
		label.setFont(new Font("�ü�ü", Font.ITALIC, 30));
		add(label,BorderLayout.NORTH);
		
		//����׷��� �׸���
		JPanel graph = new MyGraph();
		add(graph, BorderLayout.CENTER); //�׷������� ���� �г��� ū �г��� ��� ���δ�.
		
				
		
	}
	
}
class MyGraph extends JPanel{
	
	@Override
	public void paintComponent(Graphics g) {
		  super.paintComponent(g);
		  //��ü ��
		  g.drawLine(80, 800, 900, 800);
	      g.drawLine(80, 800, 80, 80);
	      
	      //x��
	      int width = 0;
	      g.drawString("�л���", 65, 70);
	      for (int x = 0; x <= 100; x += 20)
	      {
	        g.drawLine(80 + width, 800, 80 + width, 820);
	        g.drawString("" + x, 75 + width, 840);
	        width +=160;
	      }
	      
	      //y��
	      int count=0;
	      g.drawString("����", 915, 805);
	      for (int y = 0; y < 800; y += 160)
	      {
	    	
	        g.drawLine(80, 800 - y, 55, 800 - y);
	        g.drawString("" +count , 30, 805 - y);
	        count += 10;
	      }
	 
	      //����׷���
	      //����x, �л���, 80, x��� �л��� ��
	      for(int i = 0; i < 800; i +=160) {
	    	  g.drawRect(120 + i,400,80,400);
	    	  g.fillRect(120 + i, 400, 80, 400);
	    	 // g.setColor(color[i]);
	      }
	      //����
	      int grade=0;
	      //������ 90���� ũ�� 100���� �۴ٸ�
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