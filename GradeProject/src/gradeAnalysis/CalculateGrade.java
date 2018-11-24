package gradeAnalysis;
import java.io.File;
import java.io.IOException;
import java.util.*;
import studentPackage.*;
public class CalculateGrade {
	//이미 만들어진 학생데이터베이스 값을 가져와서 studentDatabase에 내용을 넣는다.
	private static Vector<Student> studentDatabase = StudentsDatabase.getStudentsDatabase();
	private static int[] sum = {0,0,0,0,0};
	
	private static double[] average = {0,0,0,0,0};
	
	
	//다섯개의 과목의 합을 구하여 반환하는 메소드
	public static int[] getSumBySubject(){	
		for(int i = 0; i < studentDatabase.size(); i++) {
			Student s = studentDatabase.get(i);
			//각 과목별로 학생들의 점수를 더한다.  
			sum[0] += s.koreanGrade; //모든 학생의 국어성적의 합
			sum[1] += s.englishGrade;//모든 학생의 영어성적의 합
			sum[2] += s.mathGrade;//모든 학생의 수학성적의합
			sum[3] += s.societyGrade;//모든 학생의 사회성적의 합
			sum[4] += s.scienceGrade;//모든 학생의 과학성적의 합
		}
		return sum;
	}
	//다섯개의 괌고의 평균을 구하여 반환하는 메소드
	public static double[] getAverageBySubject() {
		for(int j = 0; j < sum.length; j++) 
			average[j] = (double)sum[j]/studentDatabase.size();
		return average;	
	}
	
	
	//학생별 과목 성적의 합과 평균을 구해서 studentsDatabase에 
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
	
	//학점을 구하는 메소드
	public String getGrade(int score) {
	
		switch(score) {
		
		}
		return "";
		
	}
	
	//학생의 총학점을 구하여 데이터베이스에 저장해주는 메소드
	public void studentTotalGrade() {
		//학생의 평균을 가져와서 getGrade()
		
	}
	

}