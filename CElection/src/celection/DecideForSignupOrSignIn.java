package celection;

import java.util.Scanner;

public class DecideForSignupOrSignIn {
	
	public void decide() throws Exception{
		System.out.println("You want to vote Y or N?");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String vote = in.nextLine();
		
		if(vote.equalsIgnoreCase("Y")){
			System.out.println("Choose 1 for signup or 2 or signIn");
			int option = in.nextInt();
			if(option == 1){
				Signup sn = new Signup();
				sn.callToSignup();
			}else if(option == 2){
				SignIn signin = new SignIn();
				signin.callToSignIn();
			}else {
				throw new Exception();
			}
		}else if(vote.equalsIgnoreCase("N")){
			//to do
		}else{
			throw new Exception();
		}
	}
	
}