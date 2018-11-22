package gradeAnalysis;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.*;

public class GraphPanel extends JPanel{
	public GraphPanel() {
		setLayout(new BorderLayout(5,0));
		setBorder(new TitledBorder(new LineBorder(Color.darkGray,2),"과목별 점수 분포도")); //테두리
		JLabel label = new JLabel("<과목별 점수 분포도>",SwingConstants.CENTER);
		label.setFont(new Font("궁서체", Font.ITALIC, 30));
		add(label,BorderLayout.NORTH);
		
	}
}
