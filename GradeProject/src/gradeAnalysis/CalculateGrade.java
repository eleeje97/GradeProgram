package gradeAnalysis;
import java.io.File;
import java.io.IOException;
import java.util.*;
import studentPackage.*;
public class CalculateGrade {
	//이미 만들어진 학생데이터베이스 값을 가져와서 studentDatabase에 내용을 넣는다.
	private static Vector<Student> studentDatabase = StudentsDatabase.getStudentsDatabase();
	private static int[] sum = new int[5];
	private static int[] average = new int[5];
	
	
	//다섯개의 과목의 합을 구하여 반환하는 메소드
	public static int[] getSumBySubject(){	
		for(int i = 0; i < studentDatabase.size(); i++) {
			Student s = studentDatabase.get(i);
			//각 과목별로 학생들의 점수를 더한다.  
			sum[0] += s.koreaGrade;
			sum[1] += s.mathGrade;
			sum[2] += s.societyGrade;
			sum[3] += s.scienceGrade;
			sum[4] += s.englishGrade;
		}
		return sum;
	}
	//다섯개의 괌고의 평균을 구하여 반환하는 메소드
	public static int[] getAverageBySubject() {
		for(int j = 0; j < sum.length; j++) 
			average[j] = sum[j]/studentDatabase.size();
		return average;	
	}
	
	
	//학생별 과목 성적의 합과 평균을 구해서 studentsDatabase에 
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