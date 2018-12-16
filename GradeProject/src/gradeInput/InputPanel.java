package gradeInput;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
	TextField[] textField = new TextField[6];
	
	JButton inputButton; //"입력"버튼
	
	String[] labelText = { "학번", "국어", "영어", "수학", "사회", "과학" };

	Vector<Student> studentsDB = StudentsDatabase.getStudentsDatabase();
	
	public InputPanel() {
		setLayout(null);
		setBorder(new TitledBorder(new LineBorder(Color.GRAY,2),"학생 성적 입력"));
		
		//각 컴포넌트들 생성
		inputLabel = new JLabel("입력방법", SwingConstants.CENTER);
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
		Font font = new Font("한초롬돋움",Font.PLAIN,20);
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
		TextFieldListener tfListener = new TextFieldListener();
		
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
			textField[i] = new TextField(10);
			
			label[i].setSize(70,50);
			label[i].setLocation(100,350+70*i);
			textField[i].setSize(500,50);
			textField[i].setLocation(200,350+70*i);
			
			label[i].setFont(font);
			textField[i].setFont(font);
			
			textField[i].setEnabled(false); //비활성화 상태로 초기화
			textField[i].setBackground(Color.LIGHT_GRAY); //비활성화 상태일 때 배경색 지정
			
			textField[i].addTextListener(tfListener); //리스너 설정
			
			
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
				ReadGrade.readGradeInfoFile();
				Object[][] rowData = TablePanel.rowData;
				
				for (int i = 0; i < studentsDB.size(); i++) {
					rowData[i][2] = studentsDB.get(i).koreanGrade;
					rowData[i][3] = studentsDB.get(i).englishGrade;
					rowData[i][4] = studentsDB.get(i).mathGrade;
					rowData[i][5] = studentsDB.get(i).societyGrade;
					rowData[i][6] = studentsDB.get(i).scienceGrade;
				}
				
				TablePanel.table.updateUI();

			} else if(e.getSource()==inputButton) {
				if(isInputValid().equals("true")) { //입력한 값이 조건을 만족하면
					//학생성적을 입력한다는 메세지를 띄운다
					int studentId = Integer.parseInt(textField[0].getText()); //학번필드의 값
					String studentIdString = String.format("%03d", studentId); 
					String name = studentsDB.get(studentId-1).getName(); //입력된 학번에 해당하는 학생 이름
					JOptionPane.showMessageDialog(null, studentIdString+" "+name+" 학생의 성적을 입력합니다.");
					
					//학생데이터베이스에 성적 업데이트
					Student s = studentsDB.get(studentId-1);
					s.koreanGrade = Integer.parseInt(textField[1].getText());
					s.englishGrade = Integer.parseInt(textField[2].getText());
					s.mathGrade = Integer.parseInt(textField[3].getText());
					s.societyGrade = Integer.parseInt(textField[4].getText());
					s.scienceGrade = Integer.parseInt(textField[5].getText());
					
					//테이블에 표시
					Object[][] rowData = TablePanel.rowData;
					rowData[studentId-1][2] = s.koreanGrade;
					rowData[studentId-1][3] = s.englishGrade;
					rowData[studentId-1][4] = s.mathGrade;
					rowData[studentId-1][5] = s.societyGrade;
					rowData[studentId-1][6] = s.scienceGrade;
					TablePanel.table.updateUI();
					
					for (int i = 0; i < textField.length; i++) {
						textField[i].setText("");
					}
					
				} else { //입력한 값이 조건을 만족하지 않으면
					//다시 입력하라는 경고메세지
					JOptionPane.showMessageDialog(null, isInputValid());
				}
			}
		}
		
		
		//입력값이 조건을 만족하는지 판단하는 메소드
		//조건1: 모든 필드에 값이 들어있는가
		//조건2: 입력한 학번이 존재하는가
		//조건3: 성적 필드의 입력값이 0~100사이의 값인가
		public String isInputValid() {
			//조건1
			int productOfLength = 1; //각 필드에 입력된 문자열 길이의 곱
			for (int i = 0; i < textField.length; i++)
				productOfLength *= textField[i].getText().length();
			if(productOfLength==0) //문자열의 길이가 0인 필드가 하나라도 존재하면
				return "모든 항목을 입력하세요.";
			
			
			
			//조건2
			int studentId = Integer.parseInt(textField[0].getText()); //학번필드의 값
			if(studentId <= 0 || studentId > 50) //학번이 존재하지 않으면
				return "존재하지 않는 학번입니다.";
			
			
			
			//조건3
			int[] grade = new int[5]; //성적필드의 값
			boolean[] gradeBool = new boolean[5];
			boolean isGradeValid = true;
			for (int i = 0; i < grade.length; i++) {
				grade[i] = Integer.parseInt(textField[i+1].getText());
				if(grade[i] >= 0 && grade[i] <= 100)
					gradeBool[i] = true;
				else
					gradeBool[i] = false;
				isGradeValid = isGradeValid && gradeBool[i];
			}
			if(!isGradeValid)
				return "올바른 성적을 입력하세요";
			
			
			//모든 조건을 만족하면
			return "true";
		}
		
	}
	
	
	//텍스트필드에 숫자가 아닌 값이 입력되면 경고메세지를 띄운다.
	class TextFieldListener implements TextListener {

		@Override
		public void textValueChanged(TextEvent e) {
			// TODO Auto-generated method stub
			String str = ((TextField) e.getSource()).getText();
			if(str.length()>0) {
				if(!Character.isDigit(str.charAt(str.length()-1))) { //마지막에 입력된 문자가 숫자가 아니라면
					JOptionPane.showMessageDialog(null, "숫자만 입력하세요"); //경고메세지
					((TextField)e.getSource()).setText(str.substring(0, str.length()-1)); //마지막에 입력된 문자를 지운다.
					((TextField)e.getSource()).setCaretPosition(str.length()-1); //그 위치부터 다시 입력할 수 있도록 한다.
				}
			}
			
		}
		
	}
	
}
