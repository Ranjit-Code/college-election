/**
* @author ${Ranjit Singh}
*/

package celection;

import java.util.Scanner;

public class Signup {
	private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
	boolean isValidated = false;
	 
	public void callToSignup() throws Exception{
		Credentials cred = new Credentials();
		System.out.println("Enter your credentials");
		System.out.println("Enter your name: ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		cred.setName(sc.nextLine());
		System.out.println("Enter your password: ");
		cred.setPassword(sc.nextLine());
		System.out.println("Enter your collegeId: ");
		cred.setId(sc.nextLine());
		isValidated = validateCredentails(cred);
		if(!isValidated){
			throw new Exception();
		}
		
		/* to do later
		 * database call first to retrieve the data on the basis of collegeId
		 * check if the record exits, throws the message that use is already exists.
		 * otherwise fire a database query to put the record in the database 
		 * */
		
		// this can be changed
		redirectToSignIn();
	}
	
	public boolean validateCredentails(Credentials cred){
		String pass = cred.getPassword();
		boolean isPassed = pass.matches(PASSWORD_PATTERN);
		return isPassed;
	}
	
	public void redirectToSignIn() throws Exception{
		SignIn signin = new SignIn();
		signin.callToSignIn();
	}
}