package gradeAnalysis;
import java.io.File;
import java.io.IOException;
import java.util.*;
import studentPackage.*;
public class CalculateGrade {
	//�̹� ������� �л������ͺ��̽� ���� �����ͼ� studentDatabase�� ������ �ִ´�.
	private static Vector<Student> studentDatabase = StudentsDatabase.getStudentsDatabase();
	private static int[] sum = new int[5];
	private static int[] average = new int[5];
	
	
	//�ټ����� ������ ���� ���Ͽ� ��ȯ�ϴ� �޼ҵ�
	public static int[] getSumBySubject(){	
		for(int i = 0; i < studentDatabase.size(); i++) {
			Student s = studentDatabase.get(i);
			//�� ���񺰷� �л����� ������ ���Ѵ�.  
			sum[0] += s.koreaGrade;
			sum[1] += s.mathGrade;
			sum[2] += s.societyGrade;
			sum[3] += s.scienceGrade;
			sum[4] += s.englishGrade;
		}
		return sum;
	}
	//�ټ����� ������ ����� ���Ͽ� ��ȯ�ϴ� �޼ҵ�
	public static int[] getAverageBySubject() {
		for(int j = 0; j < sum.length; j++) 
			average[j] = sum[j]/studentDatabase.size();
		return average;	
	}
	
	
	//�л��� ���� ������ �հ� ����� ���ؼ� studentsDatabase�� 
	public static void getSum_AverageByStudent(){
		int[] sum = new int[50];
		int[] average = new int [50];
		
		for(int i = 0; i < studentDatabase.size(); i++) {
			Student s = studentDatabase.get(i);
			sum[i] = s.koreaGrade + s.mathGrade + s.societyGrade + s.scienceGrade + s.englishGrade; 
			average[i] = sum[i]/5;
			s.average = average[i];	
		}
	}
	
	//������ ���ϴ� �޼ҵ�
	public String getGrade(int score) {
	
		switch(score) {
		
		}
		return "";
		
	}
	
	//�л��� �������� ���Ͽ� �����ͺ��̽��� �������ִ� �޼ҵ�
	public void studentTotalGrade() {
		//�л��� ����� �����ͼ� getGrade()
		
	}
	

}