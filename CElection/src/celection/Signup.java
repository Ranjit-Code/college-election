/**
* @author ${Ranjit Singh}
*/

package celection;

import java.sql.*;
import java.util.Scanner;

public class Signup {
	private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
	boolean isValidated = false;

	public void callToSignup() throws Exception {
		Credentials cred = new Credentials();

		System.out.println("Enter your credentials");

		System.out.println("Enter name: ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		cred.setName(sc.nextLine());

		String password = PasswordField.readPassword("Enter password:");
		// String password = "password";
		// System.out.println("Password entered was:" + password);
		cred.setPassword(password);

		isValidated = validateCredentails(cred);
		if (!isValidated) {
			throw new Exception("Password doesnot meet the requirements. It must have minimum of 8 characters including numeric and special characters.");
		}

		System.out.println("Enter collegeId: ");
		cred.setId(sc.nextLine());

		/*
		 * database call first to retrieve the data on the basis of collegeId
		 * check if the record exits, throws the message that use is already
		 * exists. otherwise fire a database query to put the record in the
		 * database
		 */

		// get the database connection
		Statement stmt = DatabaseConnection.getConnection().createStatement();

		/* retrieving the record on the basis of college id */
		ResultSet rs = stmt.executeQuery("select * from User where collegeId = '" + cred.getId() + "'");
		if (rs.next()) {
			System.out.println("User is already exist. Please press R to return to the signIn page.");
			Scanner r = new Scanner(System.in);
			String sign = r.nextLine();
			if (sign.equalsIgnoreCase("r")) {
				redirectToSignIn();
				System.exit(0);
			}
		}

		String sqlString = "Insert into User (collegeId, name, password, hasVoted, assigned_candidate) values";
		String one = "('" + cred.getId() + "', '" + cred.getName() + "', '" + cred.getPassword() + "', 0 , null );";

		// System.out.println(sqlString.concat(one));

		int rinsert = stmt.executeUpdate(sqlString.concat(one));

		if (rinsert < 0) {
			throw new Exception("Something went wrong. Please try again");
		}

		System.out.println("Thank you for signup. Please enter your credentials to login into the system.");
		System.out.println();
		redirectToSignIn();
	}

	public boolean validateCredentails(Credentials cred) {
		String pass = cred.getPassword();
		boolean isPassed = pass.matches(PASSWORD_PATTERN);
		return isPassed;
	}

	public void redirectToSignIn() throws Exception {
		SignIn signin = new SignIn();
		signin.callToSignIn();
	}
}