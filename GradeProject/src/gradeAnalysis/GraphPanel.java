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
	static JPanel emptypanel = new JPanel(); // ���� ��ư�� �ƹ��͵� ���õ��� �ʾ��� ��
	static Color[] colors = { new Color(0x9c88ff), new Color(0x00cec9), new Color(0xfdcb6e), new Color(0xff7675),
			new Color(0xfd79a8) };
	static int index;
	static int[] count = new int[5];
	static double average[];

	public GraphPanel() {
		setLayout(new BorderLayout(5, 0));
		setBorder(new TitledBorder(new LineBorder(Color.GRAY, 2),"���� �׷���")); // �׵θ�
		JLabel label = new JLabel("<���� ���� ������>", SwingConstants.CENTER);
		label.setFont(new Font("���ʷҵ���", Font.ITALIC, 30));
		add(label, BorderLayout.NORTH);
		
		JPanel graph = new JPanel(); // �׷������� ���� �г� ����
		graph.setLayout(new CardLayout()); // ī�巹�̾ƿ����� �����Ѵ�.
		
		//emtyPanel
		emptypanel.setLayout(new BorderLayout());
		JLabel msg = new JLabel("������ �����Ͽ� �ּ���!", SwingConstants.CENTER);
		msg.setForeground(Color.GRAY);
		msg.setFont(new Font("���ʷҵ���", Font.PLAIN, 30));
		emptypanel.add(msg, BorderLayout.CENTER);
		graph.add(emptypanel);
		graph.add(subjectgraph); // ���� �׷����� ���δ�.
		graph.add(totalgraph); // ��ü �׷����� ���δ�.

		
		add(graph, BorderLayout.CENTER); // �׷������� ���� �г��� ū �г��� ��� ���δ�.

	}

	public static void paintGraph(Object[][] rowData, int n) {
		index = n;
		if(index==5) { //��ü ���� ��ư�� ���õǾ��� ��
			average = CalculateGrade.getAverageBySubject();
			totalgraph.repaint();
			emptypanel.setVisible(false);
			totalgraph.setVisible(true);
			subjectgraph.setVisible(false);
		} else { //���� ���� ��ư�� ���õǾ��� ��
			countScore(rowData);
			subjectgraph.repaint();
			subjectgraph.repaint();
			emptypanel.setVisible(false);
			subjectgraph.setVisible(true);
			totalgraph.setVisible(false);
		}
		
	}

	public static void countScore(Object[][] rowData) {
		// ���������� ���� �л����� ī��Ʈ�Ѵ�.
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

// ������ư�� ������ �� ���� �׷��� ǥ��
class SubjectGraph extends JPanel {
	JLabel[] studentCount = new JLabel[5];

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// ��ü ��
		g.drawLine(80, 800, 900, 800);
		g.drawLine(80, 800, 80, 80);

		// x��
		int width = 0;
		g.setFont(new Font("���ʷҵ���", Font.PLAIN,12));
		g.drawString("�л���", 65, 70);
		for (int x = 0; x <= 100; x += 20) {
			g.drawLine(80 + width, 800, 80 + width, 820);
			g.drawString("" + x, 75 + width, 840);
			width += 160;
		}

		// y��
		int height = 0;
		g.drawString("����", 912, 805);
		for (int y = 0; y < 800; y += 160) {
			g.drawLine(80, 800 - y, 55, 800 - y);
			g.drawString("" + height, 30, 805 - y);
			height += 5;
		}


		// �׷��� ���� �л��� ǥ���ϱ�
		int interval = 0;
		for (int i = 0; i < GraphPanel.count.length; i++) {
			g.setColor(Color.BLACK);
			g.drawString("" + GraphPanel.count[i], 157 + interval, (800 - GraphPanel.count[i] * 32) - 5);
			interval += 160;
		}

		// ���� �׷��� �׸��� 
		g.setColor(GraphPanel.colors[GraphPanel.index]);
		for (int i = 0; i < GraphPanel.count.length; i++) {
			g.fillRect(120 + 160*i, 800 - GraphPanel.count[i] * 32, 80, GraphPanel.count[i] * 32);
		}
		
	}
}

// ���� ��ư �� ��ü�� ������ �� ��Ÿ���� �׷���
class TotalGraph extends JPanel {

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// ��ü ��
		g.drawLine(80, 800, 900, 800);
		g.drawLine(80, 800, 80, 80);
		g.setFont(new Font("���ʷҵ���", Font.PLAIN,12));
		int width = 80;
		g.drawString("����", 65, 70);
		for (int x = 0; x <= 90; x += 20) {
			g.drawLine(80 + width, 800, 80 + width, 820);
			// g.drawString("" + x, 75 + width, 840);
			width += 160;
		}
		g.drawString("����", 150, 838);
		g.drawString("����", 310, 838);
		g.drawString("����", 470, 838);
		g.drawString("��ȸ", 630, 838);
		g.drawString("����", 790, 838);

		// y��
		int height = 0;
		g.drawString("����", 912, 805);
		for (int y = 0; y < 650; y += 64) {
			g.drawLine(80, 800 - y, 60, 800 - y);
			g.drawString("" + height, 35, 805 - y);
			height += 10;
		}


		// �׷��� ���� ���� ǥ���ϱ�
		int interval = 0;
		for (int i = 0; i < GraphPanel.count.length; i++) {
			g.setColor(Color.BLACK);
			g.drawString("" + GraphPanel.average[i], 148 + interval, (800 - (int)(GraphPanel.average[i] * 6.4)) - 5);
			interval += 160;
		}
		
		//g.setColor(new Color(0x74b9ff));
		// ���� �׷��� �׸��� 
		for (int i = 0; i < GraphPanel.average.length; i++) {
			g.setColor(GraphPanel.colors[i]);
			g.fillRect(120 + 160*i, 800 - (int)(GraphPanel.average[i] * 6.4), 80, (int)(GraphPanel.average[i] * 6.4));
		}
	}
}
