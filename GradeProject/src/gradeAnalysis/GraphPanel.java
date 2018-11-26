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
	static JPanel graph = new MyGraph();
	static Color[] colors = {new Color(0x9c88ff),new Color(0x00cec9),new Color(0xfdcb6e),new Color(0xff7675),new Color(0xfd79a8)};
	static int index;
	static int[] count = new int[5];
	public GraphPanel() {
		setLayout(new BorderLayout(5,0));
		setBorder(new TitledBorder(new LineBorder(Color.darkGray,2),"���� ���� ������")); //�׵θ�
		JLabel label = new JLabel("<���� ���� ������>",SwingConstants.CENTER);
		label.setFont(new Font("�ü�ü", Font.ITALIC, 30));
		add(label,BorderLayout.NORTH);
		
		//����׷��� �׸���
		
		add(graph, BorderLayout.CENTER); //�׷������� ���� �г��� ū �г��� ��� ���δ�.
		
				
		
	}
	
	public static void paintGraph(Object[][] rowData, int n) {
		 index=n;
		 countScore(rowData);
		 graph.repaint();
	}
	
	public static void countScore(Object[][] rowData) {
		//���������� ���� �л����� ī��Ʈ�Ѵ�.
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
	      for (int x = 0; x <= 100; x += 20) {
	        g.drawLine(80 + width, 800, 80 + width, 820);
	        g.drawString("" + x, 75 + width, 840);
	        width +=160;
	      }
	      
	      //y��
	      int height=0;
	      g.drawString("����", 915, 805);
	      for (int y = 0; y < 800; y += 160) {
	        g.drawLine(80, 800 - y, 55, 800 - y);
	        g.drawString("" +height , 30, 805 - y);
	        height += 5;
	      }
	      
	      
	      //����׷���	      
	      g.drawRect(120, 800-GraphPanel.count[0]*32, 80,GraphPanel.count[0]*32);
	      g.drawRect(280, 800-GraphPanel.count[1]*32, 80,GraphPanel.count[1]*32);
	      g.drawRect(440, 800-GraphPanel.count[2]*32, 80,GraphPanel.count[2]*32);
	      g.drawRect(600, 800-GraphPanel.count[3]*32, 80,GraphPanel.count[3]*32);
	      g.drawRect(760, 800-GraphPanel.count[4]*32, 80,GraphPanel.count[4]*32);
	      
	      //�׷��� ��
	      g.setColor(GraphPanel.colors[GraphPanel.index]);
	      g.fillRect(120, 800-GraphPanel.count[0]*32, 80,GraphPanel.count[0]*32);
	      g.fillRect(280, 800-GraphPanel.count[1]*32, 80,GraphPanel.count[1]*32);
	      g.fillRect(440, 800-GraphPanel.count[2]*32, 80,GraphPanel.count[2]*32);
	      g.fillRect(600, 800-GraphPanel.count[3]*32, 80,GraphPanel.count[3]*32);
	      g.fillRect(760, 800-GraphPanel.count[4]*32, 80,GraphPanel.count[4]*32);
	}
}


