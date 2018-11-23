package gradeInput;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
	JTextField[] textField = new JTextField[6];
	
	JButton inputButton; //"�Է�"��ư
	
	String[] labelText = { "�й�", "����", "����", "����", "��ȸ", "����" };
	
	public InputPanel() {
		setLayout(null);
		//setBackground(Color.WHITE); //test��
		
		//�� ������Ʈ�� ����
		inputLabel = new JLabel("�Է¹��");
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
		Font font = new Font("Gothic",Font.PLAIN,25);
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
			textField[i] = new JTextField(10);
			
			label[i].setSize(70,50);
			label[i].setLocation(100,350+70*i);
			textField[i].setSize(500,50);
			textField[i].setLocation(200,350+70*i);
			
			label[i].setFont(font);
			textField[i].setFont(font);
			
			textField[i].setEnabled(false); //��Ȱ��ȭ ���·� �ʱ�ȭ
			textField[i].setBackground(Color.LIGHT_GRAY); //��Ȱ��ȭ ������ �� ���� ����
			
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
