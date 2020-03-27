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
		System.out.println("Enter name: ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		cred.setName(sc.nextLine());
		String password = PasswordField.readPassword("Enter password:");
        System.out.println("Password entered was:" + password);
		cred.setPassword(password);
		System.out.println("Enter collegeId: ");
		cred.setId(sc.nextLine());
		isValidated = validateCredentails(cred);
		if(!isValidated){
			throw new Exception("Password doesnot match the requirements.");
		}
		
		/* to do later
		 * database call first to retrieve the data on the basis of collegeId
		 * check if the record exits, throws the message that use is already exists.
		 * otherwise fire a database query to put the record in the database 
		 * */
		
		// this can be changed
		System.out.println("Thank you for signup. Please enter your credentials to login into the system.");
		System.out.println();
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