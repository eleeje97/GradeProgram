package gradeAnalysis;
import java.util.*;
import studentPackage.*;
public class CalculateGrade {
	//이미 만들어진 학생데이터베이스 값을 가져와서 studentDatabase에 내용을 넣는다.
	private static Vector<Student> studentDatabase = StudentsDatabase.getStudentsDatabase();
	
	
	//다섯개의 과목의 합을 구하여 반환하는 메소드
	public static int[] getSumBySubject(){	
		int[] sum = {0,0,0,0,0};
		
		for(int i = 0; i < studentDatabase.size(); i++) {
			Student s = studentDatabase.get(i);
			//각 과목별로 학생들의 점수를 더한다.  
			sum[0] += s.koreanGrade; //모든 학생의 국어성적의 합
			sum[1] += s.englishGrade; //모든 학생의 영어성적의 합
			sum[2] += s.mathGrade; //모든 학생의 수학성적의합
			sum[3] += s.societyGrade; //모든 학생의 사회성적의 합
			sum[4] += s.scienceGrade; //모든 학생의 과학성적의 합
		}
		return sum;
	}
	
	//다섯개의 과목의 평균을 구하여 반환하는 메소드
	public static double[] getAverageBySubject() {
		int[] sum = getSumBySubject();
		double[] average = {0,0,0,0,0};
		
		for(int j = 0; j < sum.length; j++) 
			average[j] = (double)sum[j]/studentDatabase.size();
		return average;	
	}
	
	
	//학생별 과목 성적의 합과 평균을 구해서 studentDatabase에 저장하는 메소드
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
	
	
	//과목별 학점과 총점을 구하여 데이터베이스에 저장해주는 메소드
	public static void calculateGrade() {
		int[] scoreArray = new int[studentDatabase.size()];
		double[] averageArray = new double[studentDatabase.size()];
		String[] gradeArray = new String[studentDatabase.size()];
		int[] rank;
		
		//국어학점계산
		for (int i = 0; i < scoreArray.length; i++)
			scoreArray[i] = studentDatabase.get(i).koreanGrade;
		rank = getRank(scoreArray);
		gradeArray = getGrade(rank);
		for (int i = 0; i < gradeArray.length; i++) 
			studentDatabase.get(i).grade[0] = gradeArray[i];
		
		//영어학점계산
		for (int i = 0; i < scoreArray.length; i++)
			scoreArray[i] = studentDatabase.get(i).englishGrade;
		rank = getRank(scoreArray);
		gradeArray = getGrade(rank);
		for (int i = 0; i < gradeArray.length; i++) 
			studentDatabase.get(i).grade[1] = gradeArray[i];
		
		//수학학점계산
		for (int i = 0; i < scoreArray.length; i++)
			scoreArray[i] = studentDatabase.get(i).mathGrade;
		rank = getRank(scoreArray);
		gradeArray = getGrade(rank);
		for (int i = 0; i < gradeArray.length; i++) 
			studentDatabase.get(i).grade[2] = gradeArray[i];
		
		//사회학점계산
		for (int i = 0; i < scoreArray.length; i++)
			scoreArray[i] = studentDatabase.get(i).societyGrade;
		rank = getRank(scoreArray);
		gradeArray = getGrade(rank);
		for (int i = 0; i < gradeArray.length; i++) 
			studentDatabase.get(i).grade[3] = gradeArray[i];
		
		//과학학점계산
		for (int i = 0; i < scoreArray.length; i++)
			scoreArray[i] = studentDatabase.get(i).scienceGrade;
		rank = getRank(scoreArray);
		gradeArray = getGrade(rank);
		for (int i = 0; i < gradeArray.length; i++) 
			studentDatabase.get(i).grade[4] = gradeArray[i];
		
		//총점계산
		for (int i = 0; i < averageArray.length; i++)
			averageArray[i] = studentDatabase.get(i).average;
		rank = getAverageRank(averageArray);
		gradeArray = getGrade(rank);
		for (int i = 0; i < gradeArray.length; i++) 
			studentDatabase.get(i).totalGrade = gradeArray[i];
	}
	
	
	//학점을 구하는 메소드
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
	
	
	//점수별 순위를 매겨주는 메소드
	public static int[] getRank(int[] scoreArray) {
		int[] rank = new int[studentDatabase.size()];
		
		//모든 순위를 1로 초기화
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
	
	
	//평균점수에 순위를 매겨주는 메소드
	public static int[] getAverageRank(double[] averageArray) {
		int[] rank = new int[studentDatabase.size()];
		
		//모든 순위를 1로 초기화
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