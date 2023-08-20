import java.lang.Math;
import java.util.Scanner;

public class NumberGame{
	public static void main(String args[]) {
		int count=0;
		int RandomValue= (int)(Math.random()*((100-1)+1)+1);
		
		System.out.println("Enter a number(1-100):");
		Scanner sc=new Scanner(System.in);
		int num= sc.nextInt();
		while(num!=RandomValue) {
			if(num<RandomValue)
				System.out.println("You have Entered Smaller number!!\n");
			else
				System.out.println("You have Entered Higher number!!\n");
			
			System.out.println("Please re-enter a number:");
			num=sc.nextInt();
			count++;
		}
		System.out.println("\n\nCongratulation!! You have Guessed the number correctly in "+count+" guesses!");
	}
}

