package gradeInput;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.util.*;
import studentPackage.*;

public class InputPanel extends JPanel {
	JLabel inputLabel; //"�Է¹��" ��
	ButtonGroup group; //���� ��ư �׷�
	JRadioButton inputByFile; //"���Ϸ� �Է�" ���� ��ư
	JRadioButton inputDirect; //"���� �Է�" ���� ��ư
	JButton importFile; //"���Ϻҷ�����" ��ư
	
	//������ �Է��ϴ� �󺧰� �ؽ�Ʈ�ʵ�
	JLabel[] label = new JLabel[6];
	TextField[] textField = new TextField[6];
	
	JButton inputButton; //"�Է�"��ư
	
	String[] labelText = { "�й�", "����", "����", "����", "��ȸ", "����" };

	Vector<Student> studentsDB = StudentsDatabase.getStudentsDatabase();
	
	public InputPanel() {
		setLayout(null);
		setBorder(new TitledBorder(new LineBorder(Color.GRAY,2),"�л� ���� �Է�"));
		
		//�� ������Ʈ�� ����
		inputLabel = new JLabel("�Է¹��", SwingConstants.CENTER);
		group = new ButtonGroup();
		inputByFile = new JRadioButton("���Ϸ� �Է�");
		inputDirect = new JRadioButton("���� �Է�");
		importFile = new JButton("���� �ҷ�����");
		inputButton = new JButton("�Է�");

		
		
		//ũ��� ��ġ ����
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
		
		//��Ʈ ����
		Font font = new Font("���ʷҵ���",Font.PLAIN,20);
		inputLabel.setFont(font);
		inputByFile.setFont(font);
		inputDirect.setFont(font);
		importFile.setFont(font);
		inputButton.setFont(font);
		
		//�Ӽ� ����
		inputByFile.setSelected(true); //���õǾ� �ִ� ���·� �ʱ�ȭ
		inputButton.setEnabled(false); //��Ȱ��ȭ ���·� �ʱ�ȭ
		
		//������ ����
		RadioButtonListener radioListener = new RadioButtonListener();
		inputByFile.addActionListener(radioListener);
		inputDirect.addActionListener(radioListener);
		ButtonListener buttonListener = new ButtonListener();
		importFile.addActionListener(buttonListener);
		inputButton.addActionListener(buttonListener);
		TextFieldListener tfListener = new TextFieldListener();
		
		//������Ʈ�� InputPanel�� ���̱�
		add(inputLabel);
		group.add(inputByFile);
		group.add(inputDirect);
		add(inputByFile);
		add(inputDirect);
		add(importFile);
		add(inputButton);
		
		
		//������Ʈ ����, ũ��� ��ġ ����, InputPanel�� ���̱�
		for (int i = 0; i < labelText.length; i++) {
			label[i] = new JLabel(labelText[i]);
			textField[i] = new TextField(10);
			
			label[i].setSize(70,50);
			label[i].setLocation(100,350+70*i);
			textField[i].setSize(500,50);
			textField[i].setLocation(200,350+70*i);
			
			label[i].setFont(font);
			textField[i].setFont(font);
			
			textField[i].setEnabled(false); //��Ȱ��ȭ ���·� �ʱ�ȭ
			textField[i].setBackground(Color.LIGHT_GRAY); //��Ȱ��ȭ ������ �� ���� ����
			
			textField[i].addTextListener(tfListener); //������ ����
			
			
			add(label[i]);
			add(textField[i]);
		}
	}
	
	class RadioButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==inputByFile) { //"���Ϸ��Է�"��ư�� ������
				importFile.setEnabled(true); //"���Ϻҷ�����"��ư Ȱ��ȭ
				for (int i = 0; i < label.length; i++) {
					textField[i].setEnabled(false); //���� �Է��ϴ� �ؽ�Ʈ�ʵ� ��Ȱ��ȭ
					textField[i].setBackground(Color.LIGHT_GRAY);
				}
				inputButton.setEnabled(false); //"�Է�"��ư ��Ȱ��ȭ
			} else if (e.getSource()==inputDirect) { //"�����Է�"��ư�� ������
				importFile.setEnabled(false); //"���Ϻҷ�����"��ư ��Ȱ��ȭ
				for (int i = 0; i < label.length; i++) {
					textField[i].setEnabled(true); //���� �Է��ϴ� �ؽ�Ʈ�ʵ� Ȱ��ȭ
					textField[i].setBackground(Color.WHITE);
				}
				inputButton.setEnabled(true); //"�Է�"��ư Ȱ��ȭ
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
				if(isInputValid().equals("true")) { //�Է��� ���� ������ �����ϸ�
					//�л������� �Է��Ѵٴ� �޼����� ����
					int studentId = Integer.parseInt(textField[0].getText()); //�й��ʵ��� ��
					String studentIdString = String.format("%03d", studentId); 
					String name = studentsDB.get(studentId-1).getName(); //�Էµ� �й��� �ش��ϴ� �л� �̸�
					JOptionPane.showMessageDialog(null, studentIdString+" "+name+" �л��� ������ �Է��մϴ�.");
					
					//�л������ͺ��̽��� ���� ������Ʈ
					Student s = studentsDB.get(studentId-1);
					s.koreanGrade = Integer.parseInt(textField[1].getText());
					s.englishGrade = Integer.parseInt(textField[2].getText());
					s.mathGrade = Integer.parseInt(textField[3].getText());
					s.societyGrade = Integer.parseInt(textField[4].getText());
					s.scienceGrade = Integer.parseInt(textField[5].getText());
					
					//���̺� ǥ��
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
					
				} else { //�Է��� ���� ������ �������� ������
					//�ٽ� �Է��϶�� ���޼���
					JOptionPane.showMessageDialog(null, isInputValid());
				}
			}
		}
		
		
		//�Է°��� ������ �����ϴ��� �Ǵ��ϴ� �޼ҵ�
		//����1: ��� �ʵ忡 ���� ����ִ°�
		//����2: �Է��� �й��� �����ϴ°�
		//����3: ���� �ʵ��� �Է°��� 0~100������ ���ΰ�
		public String isInputValid() {
			//����1
			int productOfLength = 1; //�� �ʵ忡 �Էµ� ���ڿ� ������ ��
			for (int i = 0; i < textField.length; i++)
				productOfLength *= textField[i].getText().length();
			if(productOfLength==0) //���ڿ��� ���̰� 0�� �ʵ尡 �ϳ��� �����ϸ�
				return "��� �׸��� �Է��ϼ���.";
			
			
			
			//����2
			int studentId = Integer.parseInt(textField[0].getText()); //�й��ʵ��� ��
			if(studentId <= 0 || studentId > 50) //�й��� �������� ������
				return "�������� �ʴ� �й��Դϴ�.";
			
			
			
			//����3
			int[] grade = new int[5]; //�����ʵ��� ��
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
				return "�ùٸ� ������ �Է��ϼ���";
			
			
			//��� ������ �����ϸ�
			return "true";
		}
		
	}
	
	
	//�ؽ�Ʈ�ʵ忡 ���ڰ� �ƴ� ���� �ԷµǸ� ���޼����� ����.
	class TextFieldListener implements TextListener {

		@Override
		public void textValueChanged(TextEvent e) {
			// TODO Auto-generated method stub
			String str = ((TextField) e.getSource()).getText();
			if(str.length()>0) {
				if(!Character.isDigit(str.charAt(str.length()-1))) { //�������� �Էµ� ���ڰ� ���ڰ� �ƴ϶��
					JOptionPane.showMessageDialog(null, "���ڸ� �Է��ϼ���"); //���޼���
					((TextField)e.getSource()).setText(str.substring(0, str.length()-1)); //�������� �Էµ� ���ڸ� �����.
					((TextField)e.getSource()).setCaretPosition(str.length()-1); //�� ��ġ���� �ٽ� �Է��� �� �ֵ��� �Ѵ�.
				}
			}
			
		}
		
	}
	
}
