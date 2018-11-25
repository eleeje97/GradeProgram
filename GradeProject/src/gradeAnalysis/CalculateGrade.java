package gradeAnalysis;
import java.util.*;
import studentPackage.*;
public class CalculateGrade {
	//�̹� ������� �л������ͺ��̽� ���� �����ͼ� studentDatabase�� ������ �ִ´�.
	private static Vector<Student> studentDatabase = StudentsDatabase.getStudentsDatabase();
	
	
	//�ټ����� ������ ���� ���Ͽ� ��ȯ�ϴ� �޼ҵ�
	public static int[] getSumBySubject(){	
		int[] sum = {0,0,0,0,0};
		
		for(int i = 0; i < studentDatabase.size(); i++) {
			Student s = studentDatabase.get(i);
			//�� ���񺰷� �л����� ������ ���Ѵ�.  
			sum[0] += s.koreanGrade; //��� �л��� ������� ��
			sum[1] += s.englishGrade; //��� �л��� ������� ��
			sum[2] += s.mathGrade; //��� �л��� ���м�������
			sum[3] += s.societyGrade; //��� �л��� ��ȸ������ ��
			sum[4] += s.scienceGrade; //��� �л��� ���м����� ��
		}
		return sum;
	}
	
	//�ټ����� ������ ����� ���Ͽ� ��ȯ�ϴ� �޼ҵ�
	public static double[] getAverageBySubject() {
		int[] sum = getSumBySubject();
		double[] average = {0,0,0,0,0};
		
		for(int j = 0; j < sum.length; j++) 
			average[j] = (double)sum[j]/studentDatabase.size();
		return average;	
	}
	
	
	//�л��� ���� ������ �հ� ����� ���ؼ� studentDatabase�� �����ϴ� �޼ҵ�
	public static void getSum_AverageByStudent(){
		int[] sum = new int[studentDatabase.size()];
		int[] average = new int [studentDatabase.size()];
		
		for(int i = 0; i < studentDatabase.size(); i++) {
			Student s = studentDatabase.get(i);
			sum[i] = s.koreanGrade + s.englishGrade + s.mathGrade + s.societyGrade + s.scienceGrade; 
			average[i] = sum[i]/5;
			s.average = average[i];	
		}
	}
	
	
	//���� ������ ������ ���Ͽ� �����ͺ��̽��� �������ִ� �޼ҵ�
	public static void calculateGrade() {
		int[] scoreArray = new int[studentDatabase.size()];
		double[] averageArray = new double[studentDatabase.size()];
		String[] gradeArray = new String[studentDatabase.size()];
		int[] rank;
		
		//�����������
		for (int i = 0; i < scoreArray.length; i++)
			scoreArray[i] = studentDatabase.get(i).koreanGrade;
		rank = getRank(scoreArray);
		gradeArray = getGrade(rank);
		for (int i = 0; i < gradeArray.length; i++) 
			studentDatabase.get(i).grade[0] = gradeArray[i];
		
		//�����������
		for (int i = 0; i < scoreArray.length; i++)
			scoreArray[i] = studentDatabase.get(i).englishGrade;
		rank = getRank(scoreArray);
		gradeArray = getGrade(rank);
		for (int i = 0; i < gradeArray.length; i++) 
			studentDatabase.get(i).grade[1] = gradeArray[i];
		
		//�����������
		for (int i = 0; i < scoreArray.length; i++)
			scoreArray[i] = studentDatabase.get(i).mathGrade;
		rank = getRank(scoreArray);
		gradeArray = getGrade(rank);
		for (int i = 0; i < gradeArray.length; i++) 
			studentDatabase.get(i).grade[2] = gradeArray[i];
		
		//��ȸ�������
		for (int i = 0; i < scoreArray.length; i++)
			scoreArray[i] = studentDatabase.get(i).societyGrade;
		rank = getRank(scoreArray);
		gradeArray = getGrade(rank);
		for (int i = 0; i < gradeArray.length; i++) 
			studentDatabase.get(i).grade[3] = gradeArray[i];
		
		//�����������
		for (int i = 0; i < scoreArray.length; i++)
			scoreArray[i] = studentDatabase.get(i).scienceGrade;
		rank = getRank(scoreArray);
		gradeArray = getGrade(rank);
		for (int i = 0; i < gradeArray.length; i++) 
			studentDatabase.get(i).grade[4] = gradeArray[i];
		
		//�������
		for (int i = 0; i < averageArray.length; i++)
			averageArray[i] = studentDatabase.get(i).average;
		rank = getAverageRank(averageArray);
		gradeArray = getGrade(rank);
		for (int i = 0; i < gradeArray.length; i++) 
			studentDatabase.get(i).totalGrade = gradeArray[i];
	}
	
	
	//������ ���ϴ� �޼ҵ�
	public static String[] getGrade(int[] rank) {
		String[] gradeArray = new String[studentDatabase.size()];
		
		for (int i = 0; i < gradeArray.length; i++) {
			if(rank[i]<=studentDatabase.size()*0.15)
				gradeArray[i] = "A+";
			else if(rank[i]<=studentDatabase.size()*0.3)
				gradeArray[i] = "A0";
			else if(rank[i]<=studentDatabase.size()*0.5)
				gradeArray[i] = "B+";
			else if(rank[i]<=studentDatabase.size()*0.7)
				gradeArray[i] = "B0";
			else if(rank[i]<=studentDatabase.size()*0.78)
				gradeArray[i] = "C+";
			else if(rank[i]<=studentDatabase.size()*0.85)
				gradeArray[i] = "C0";
			else if(rank[i]<=studentDatabase.size()*0.9)
				gradeArray[i] = "D+";
			else if(rank[i]<=studentDatabase.size()*0.95)
				gradeArray[i] = "D0";
			else
				gradeArray[i] = "F";
		}
		
		
		return gradeArray;
	}
	
	
	//������ ������ �Ű��ִ� �޼ҵ�
	public static int[] getRank(int[] scoreArray) {
		int[] rank = new int[studentDatabase.size()];
		
		//��� ������ 1�� �ʱ�ȭ
		for (int i = 0; i < rank.length; i++)
			rank[i] = 1;
		
		
		for (int i = 0; i < scoreArray.length; i++) {
			for (int j = 0; j < scoreArray.length; j++) {
				if((int)scoreArray[i] < (int)scoreArray[j])
					rank[i]++;
			}
		}
		
		return rank;
		
	}
	
	
	//��������� ������ �Ű��ִ� �޼ҵ�
	public static int[] getAverageRank(double[] averageArray) {
		int[] rank = new int[studentDatabase.size()];
		
		//��� ������ 1�� �ʱ�ȭ
		for (int i = 0; i < rank.length; i++)
			rank[i] = 1;
		
		for (int i = 0; i < averageArray.length; i++) {
			for (int j = 0; j < averageArray.length; j++) {
				if((double)averageArray[i] < (double)averageArray[j])
					rank[i]++;
			}
		}
		
		return rank;
		
	}
		
	
}