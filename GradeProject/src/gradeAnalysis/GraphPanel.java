package gradeAnalysis;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import studentPackage.Student;
import studentPackage.StudentsDatabase;

import java.awt.event.*;
import java.util.Vector;

public class GraphPanel extends JPanel {
	static JPanel subjectgraph = new SubjectGraph();
	static JPanel totalgraph = new TotalGraph();
	static JPanel emptypanel = new JPanel(); // 라디오 버튼이 아무것도 선택되지 않았을 때
	static Color[] colors = { new Color(0x9c88ff), new Color(0x00cec9), new Color(0xfdcb6e), new Color(0xff7675),
			new Color(0xfd79a8) };
	static int index;
	static int[] count = new int[5];
	static double average[];

	public GraphPanel() {
		setLayout(new BorderLayout(5, 0));
		setBorder(new TitledBorder(new LineBorder(Color.GRAY, 2),"막대 그래프")); // 테두리
		JLabel label = new JLabel("<과목별 점수 분포도>", SwingConstants.CENTER);
		label.setFont(new Font("한초롬돋움", Font.ITALIC, 30));
		add(label, BorderLayout.NORTH);
		
		JPanel graph = new JPanel(); // 그래프들을 담을 패널 생성
		graph.setLayout(new CardLayout()); // 카드레이아웃으로 지정한다.
		
		//emtyPanel
		emptypanel.setLayout(new BorderLayout());
		JLabel msg = new JLabel("과목을 선택하여 주세요!", SwingConstants.CENTER);
		msg.setForeground(Color.GRAY);
		msg.setFont(new Font("한초롬돋움", Font.PLAIN, 30));
		emptypanel.add(msg, BorderLayout.CENTER);
		graph.add(emptypanel);
		graph.add(subjectgraph); // 과목별 그래프를 붙인다.
		graph.add(totalgraph); // 전체 그래프를 붙인다.

		
		add(graph, BorderLayout.CENTER); // 그래프만을 담을 패널을 큰 패널의 가운데 붙인다.

	}

	public static void paintGraph(Object[][] rowData, int n) {
		index = n;
		if(index==5) { //전체 라디오 버튼이 선택되었을 때
			average = CalculateGrade.getAverageBySubject();
			totalgraph.repaint();
			emptypanel.setVisible(false);
			totalgraph.setVisible(true);
			subjectgraph.setVisible(false);
		} else { //과목별 라디오 버튼이 선택되었을 때
			countScore(rowData);
			subjectgraph.repaint();
			subjectgraph.repaint();
			emptypanel.setVisible(false);
			subjectgraph.setVisible(true);
			totalgraph.setVisible(false);
		}
		
	}

	public static void countScore(Object[][] rowData) {
		// 점수범위에 따른 학생수를 카운트한다.
		int score;
		for (int i = 0; i < count.length; i++)
			count[i] = 0;

		for (int i = 0; i < rowData.length; i++) {
			score = (int) rowData[i][2];
			if (score < 20)
				count[0]++;
			else if (score < 40)
				count[1]++;
			else if (score < 60)
				count[2]++;
			else if (score < 80)
				count[3]++;
			else if (score <= 100)
				count[4]++;

		}
	}
}

// 라디오버튼을 눌렀을 때 과목별 그래프 표현
class SubjectGraph extends JPanel {
	JLabel[] studentCount = new JLabel[5];

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 전체 축
		g.drawLine(80, 800, 900, 800);
		g.drawLine(80, 800, 80, 80);

		// x축
		int width = 0;
		g.setFont(new Font("한초롬돋움", Font.PLAIN,12));
		g.drawString("학생수", 65, 70);
		for (int x = 0; x <= 100; x += 20) {
			g.drawLine(80 + width, 800, 80 + width, 820);
			g.drawString("" + x, 75 + width, 840);
			width += 160;
		}

		// y축
		int height = 0;
		g.drawString("점수", 912, 805);
		for (int y = 0; y < 800; y += 160) {
			g.drawLine(80, 800 - y, 55, 800 - y);
			g.drawString("" + height, 30, 805 - y);
			height += 5;
		}


		// 그래프 위에 학생수 표시하기
		int interval = 0;
		for (int i = 0; i < GraphPanel.count.length; i++) {
			g.setColor(Color.BLACK);
			g.drawString("" + GraphPanel.count[i], 157 + interval, (800 - GraphPanel.count[i] * 32) - 5);
			interval += 160;
		}

		// 막대 그래프 그리기 
		g.setColor(GraphPanel.colors[GraphPanel.index]);
		for (int i = 0; i < GraphPanel.count.length; i++) {
			g.fillRect(120 + 160*i, 800 - GraphPanel.count[i] * 32, 80, GraphPanel.count[i] * 32);
		}
		
	}
}

// 라디오 버튼 중 전체가 눌렸을 때 나타나는 그래프
class TotalGraph extends JPanel {

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 전체 축
		g.drawLine(80, 800, 900, 800);
		g.drawLine(80, 800, 80, 80);
		g.setFont(new Font("한초롬돋움", Font.PLAIN,12));
		int width = 80;
		g.drawString("점수", 65, 70);
		for (int x = 0; x <= 90; x += 20) {
			g.drawLine(80 + width, 800, 80 + width, 820);
			// g.drawString("" + x, 75 + width, 840);
			width += 160;
		}
		g.drawString("국어", 150, 838);
		g.drawString("영어", 310, 838);
		g.drawString("수학", 470, 838);
		g.drawString("사회", 630, 838);
		g.drawString("과학", 790, 838);

		// y축
		int height = 0;
		g.drawString("과목", 912, 805);
		for (int y = 0; y < 650; y += 64) {
			g.drawLine(80, 800 - y, 60, 800 - y);
			g.drawString("" + height, 35, 805 - y);
			height += 10;
		}


		// 그래프 위에 점수 표시하기
		int interval = 0;
		for (int i = 0; i < GraphPanel.count.length; i++) {
			g.setColor(Color.BLACK);
			g.drawString("" + GraphPanel.average[i], 148 + interval, (800 - (int)(GraphPanel.average[i] * 6.4)) - 5);
			interval += 160;
		}
		
		//g.setColor(new Color(0x74b9ff));
		// 막대 그래프 그리기 
		for (int i = 0; i < GraphPanel.average.length; i++) {
			g.setColor(GraphPanel.colors[i]);
			g.fillRect(120 + 160*i, 800 - (int)(GraphPanel.average[i] * 6.4), 80, (int)(GraphPanel.average[i] * 6.4));
		}
	}
}
