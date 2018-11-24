package gradeAnalysis;
import java.io.File;
import java.io.IOException;
import java.util.*;
import studentPackage.*;
public class CalculateGrade {
	//�̹� ������� �л������ͺ��̽� ���� �����ͼ� studentDatabase�� ������ �ִ´�.
	private static Vector<Student> studentDatabase = StudentsDatabase.getStudentsDatabase();
	private static int[] sum = {0,0,0,0,0};
	
	private static double[] average = {0,0,0,0,0};
	
	
	//�ټ����� ������ ���� ���Ͽ� ��ȯ�ϴ� �޼ҵ�
	public static int[] getSumBySubject(){	
		for(int i = 0; i < studentDatabase.size(); i++) {
			Student s = studentDatabase.get(i);
			//�� ���񺰷� �л����� ������ ���Ѵ�.  
			sum[0] += s.koreanGrade; //��� �л��� ������� ��
			sum[1] += s.englishGrade;//��� �л��� ������� ��
			sum[2] += s.mathGrade;//��� �л��� ���м�������
			sum[3] += s.societyGrade;//��� �л��� ��ȸ������ ��
			sum[4] += s.scienceGrade;//��� �л��� ���м����� ��
		}
		return sum;
	}
	//�ټ����� ������ ����� ���Ͽ� ��ȯ�ϴ� �޼ҵ�
	public static double[] getAverageBySubject() {
		for(int j = 0; j < sum.length; j++) 
			average[j] = (double)sum[j]/studentDatabase.size();
		return average;	
	}
	
	
	//�л��� ���� ������ �հ� ����� ���ؼ� studentsDatabase�� 
	public static void getSum_AverageByStudent(){
		int[] sumByStudent = new int[50];
		int[] averageByStudent = new int [50];
		
		for(int i = 0; i < studentDatabase.size(); i++) {
			Student s = studentDatabase.get(i);
			sumByStudent[i] = s.koreanGrade + s.mathGrade + s.societyGrade + s.scienceGrade + s.englishGrade; 
			averageByStudent[i] = sumByStudent[i]/sum.length;
			s.average = averageByStudent[i];	
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