package gradeAnalysis;
import java.util.Random;
public class RandomGrage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int r ;
		for(int i=0; i<5; i++)
		{	
			r= (int)(Math.random()*100)+15;
			System.out.print(r+" ");
		}
	}

}
