/**
* @author ${Ranjit Singh}
*/

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
				Registrar rg = new Registrar();
				rg.dashboard();	
			}else if(reg.equalsIgnoreCase("N")){
				DecideForSignupOrSignIn decide = new DecideForSignupOrSignIn();
				decide.decide();
			}else{
				throw new Exception("Wrong input. Please choose the valid option");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
