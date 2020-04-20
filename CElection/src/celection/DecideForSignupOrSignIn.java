/**
* @author ${Ranjit Singh}
*/

package celection;

import java.util.Scanner;

public class DecideForSignupOrSignIn {
	
	public void decide() throws Exception{
		/*System.out.println("Press Y to create account or N for signIn?");
		@SuppressWarnings("resource")*/
		Scanner in = new Scanner(System.in);
		//String vote = in.nextLine();
		
		//if(vote.equalsIgnoreCase("Y")){
			System.out.println("Choose 1 for signup or 2 for signIn");
			int option = in.nextInt();
			if(option == 1){
				if(Registrar.getIsPublish()){
					throw new Exception("Voting is closed.");
				}
				Signup sn = new Signup();
				sn.callToSignup();
			}else if(option == 2){
				SignIn signin = new SignIn();
				signin.callToSignIn();
			}else {
				in.close();
				throw new Exception("Wrong input. Please choose the valid option");
			}
			
			in.close();
		/*}else if(vote.equalsIgnoreCase("N")){
			SignIn signin = new SignIn();
			signin.callToSignIn();
		}else{
			throw new Exception("Wrong input. Please choose the valid option");
		}*/
	}
}
