package gradeAnalysis;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.*;

public class GraphPanel extends JPanel{
	public GraphPanel() {
		setLayout(new BorderLayout(5,0));
		setBorder(new TitledBorder(new LineBorder(Color.darkGray,2),"���� ���� ������")); //�׵θ�
		JLabel label = new JLabel("<���� ���� ������>",SwingConstants.CENTER);
		label.setFont(new Font("�ü�ü", Font.ITALIC, 30));
		add(label,BorderLayout.NORTH);
		
	}
}
