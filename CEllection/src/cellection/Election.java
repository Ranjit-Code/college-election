package cellection;

import java.util.Scanner;

public class Election {

	public static void main(String[] args) {
		System.out.println("Welcome to the college-election portal");
		System.out.println("**************************************");
		System.out.println("Are you a registrar Y or N?");
		
		@SuppressWarnings("resource")
		Scanner regOrN = new Scanner(System.in);
		String reg = regOrN.nextLine();
		
		if(reg.equalsIgnoreCase("Y")){
			
		}else if(reg.equalsIgnoreCase("N")){
			
		}else{
			
		}
	}
}
