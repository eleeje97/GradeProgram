package gradeInput;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import studentPackage.*;

public class InputPanel extends JPanel {
	JLabel inputLabel; //"입력방법" 라벨
	ButtonGroup group; //라디오 버튼 그룹
	JRadioButton inputByFile; //"파일로 입력" 라디오 버튼
	JRadioButton inputDirect; //"직접 입력" 라디오 버튼
	JButton importFile; //"파일불러오기" 버튼
	
	//성적을 입력하는 라벨과 텍스트필드
	JLabel[] label = new JLabel[6];
	JTextField[] textField = new JTextField[6];
	
	JButton inputButton; //"입력"버튼
	
	String[] labelText = { "학번", "국어", "영어", "수학", "사회", "과학" };
	
	public InputPanel() {
		setLayout(null);
		//setBackground(Color.WHITE); //test용
		
		//각 컴포넌트들 생성
		inputLabel = new JLabel("입력방법");
		group = new ButtonGroup();
		inputByFile = new JRadioButton("파일로 입력");
		inputDirect = new JRadioButton("직접 입력");
		importFile = new JButton("파일 불러오기");
		inputButton = new JButton("입력");
		
		
		//크기와 위치 조정
		inputLabel.setSize(120,50);
		inputLabel.setLocation(100,80);
		inputByFile.setSize(160,50);
		inputByFile.setLocation(300,80);
		inputDirect.setSize(150,50);
		inputDirect.setLocation(500,80);
		importFile.setSize(300,70);
		importFile.setLocation(250,200);
		inputButton.setSize(200,70);
		inputButton.setLocation(500,800);
		
		//폰트 조정
		Font font = new Font("Gothic",Font.PLAIN,25);
		inputLabel.setFont(font);
		inputByFile.setFont(font);
		inputDirect.setFont(font);
		importFile.setFont(font);
		inputButton.setFont(font);
		
		//속성 지정
		inputByFile.setSelected(true); //선택되어 있는 상태로 초기화
		inputButton.setEnabled(false); //비활성화 상태로 초기화
		
		//리스너 설정
		RadioButtonListener radioListener = new RadioButtonListener();
		inputByFile.addActionListener(radioListener);
		inputDirect.addActionListener(radioListener);
		ButtonListener buttonListener = new ButtonListener();
		importFile.addActionListener(buttonListener);
		inputButton.addActionListener(buttonListener);
		
		//컴포넌트들 InputPanel에 붙이기
		add(inputLabel);
		group.add(inputByFile);
		group.add(inputDirect);
		add(inputByFile);
		add(inputDirect);
		add(importFile);
		add(inputButton);
		
		
		//컴포넌트 생성, 크기와 위치 조정, InputPanel에 붙이기
		for (int i = 0; i < labelText.length; i++) {
			label[i] = new JLabel(labelText[i]);
			textField[i] = new JTextField(10);
			
			label[i].setSize(70,50);
			label[i].setLocation(100,350+70*i);
			textField[i].setSize(500,50);
			textField[i].setLocation(200,350+70*i);
			
			label[i].setFont(font);
			textField[i].setFont(font);
			
			textField[i].setEnabled(false); //비활성화 상태로 초기화
			textField[i].setBackground(Color.LIGHT_GRAY); //비활성화 상태일 때 배경색 지정
			
			add(label[i]);
			add(textField[i]);
		}
	}
	
	class RadioButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==inputByFile) { //"파일로입력"버튼이 눌리면
				importFile.setEnabled(true); //"파일불러오기"버튼 활성화
				for (int i = 0; i < label.length; i++) {
					textField[i].setEnabled(false); //성적 입력하는 텍스트필드 비활성화
					textField[i].setBackground(Color.LIGHT_GRAY);
				}
				inputButton.setEnabled(false); //"입력"버튼 비활성화
			} else if (e.getSource()==inputDirect) { //"직접입력"버튼이 눌리면
				importFile.setEnabled(false); //"파일불러오기"버튼 비활성화
				for (int i = 0; i < label.length; i++) {
					textField[i].setEnabled(true); //성적 입력하는 텍스트필드 활성화
					textField[i].setBackground(Color.WHITE);
				}
				inputButton.setEnabled(true); //"입력"버튼 활성화
			}
		}
		
	}
	
	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==importFile) {
				Vector<Student> studentsDB = TablePanel.studentsDB;
				Object[][] rowData = TablePanel.rowData;
				
				for (int i = 0; i < studentsDB.size(); i++) {
					rowData[i][2] = studentsDB.get(i).koreaGrade;
					rowData[i][3] = studentsDB.get(i).englishGrade;
					rowData[i][4] = studentsDB.get(i).mathGrade;
					rowData[i][5] = studentsDB.get(i).societyGrade;
					rowData[i][6] = studentsDB.get(i).scienceGrade;
				}
				
				TablePanel.table.updateUI();

			} else if(e.getSource()==inputButton) {
				
			}
		}
		
	}
}
