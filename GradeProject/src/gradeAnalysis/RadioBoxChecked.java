package gradeAnalysis;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioBoxChecked implements ItemListener{
	GradeAnalysisPanel frame = new GradeAnalysisPanel();
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		JRadioButton button = (JRadioButton)e.getSource();
		
	}
	
}
