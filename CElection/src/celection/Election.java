package celection;

import java.util.Scanner;

public class Election {

	public static void main(String[] args) {
		System.out.println("Welcome to the college-election portal");
		System.out.println("**************************************");
		System.out.println("Are you a registrar Y or N?");
		
		@SuppressWarnings("resource")
		Scanner regOrN = new Scanner(System.in);
		String reg = regOrN.nextLine();
		
		try{
			if(reg.equalsIgnoreCase("Y")){
				//to do
			}else if(reg.equalsIgnoreCase("N")){
				DecideForSignupOrSignIn decide = new DecideForSignupOrSignIn();
				decide.decide();
			}else{
				throw new Exception();
			}
		}catch(Exception e){
			System.out.println("Please enter valid input");
		}
	}
}
