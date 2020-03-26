package celection;

import java.util.Scanner;

public class SignIn {
	boolean isValidated = false;
	public void callToSignIn() throws Exception{
		System.out.println("Enter your credentials");
		Credentials cred = new Credentials();
		System.out.println("Enter your credentials");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your collegeId: ");
		cred.setId(sc.nextLine());
		System.out.println("Enter your password: ");
		cred.setPassword(sc.nextLine());
		Signup sigUp = new Signup();
		isValidated = sigUp.validateCredentails(cred);
		if(!isValidated){
			throw new Exception();
		}
		
		/* to do later
		 * database call first to retrieve the data on the basis of collegeId
		 * check if the record exits, throws the message that use is already exists.
		 * otherwise fire a database query to put the record in the database 
		 * */
		
		//redirect code to profile class.
	}
}