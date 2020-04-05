/**
* @author ${Ranjit Singh}
*/

package celection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SignIn {
	boolean isValidated = false;
	String collegeId;
	String password;

	public void callToSignIn() throws Exception {
		Credentials cred = new Credentials();
		System.out.println("Enter your credentials");

		System.out.println("Enter collegeId: ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		collegeId = sc.nextLine();

		String password = PasswordField.readPassword("Enter password:");
		cred.setPassword(password);
		// password = "password";
		// System.out.println("Password entered was:" + password);

		Signup sigUp = new Signup();

		isValidated = sigUp.validateCredentails(cred);
		if (!isValidated) {
			throw new Exception("Password doesnot match the requirements.");
		}

		/*
		 * database call first to retrieve the data on the basis of collegeId
		 * check if the record exits, enter into the profile module otherwise
		 * throw the error message that use does not exist.
		 */

		// get the database connection
		Statement stmt = DatabaseConnection.getConnection().createStatement();

		/* retrieving the record on the basis of college id */
		ResultSet rs = stmt.executeQuery("select * from User where collegeId = '" + collegeId + "'");

		if (rs.next()) {
			cred.setId(rs.getString(1));
			cred.setName(rs.getString(2));
			cred.setPassword(rs.getString(3));
			cred.setHasVoted(rs.getBoolean(4));
			cred.setAssignedCandidate(rs.getString(5));
		} else {
			throw new Exception("User does not exist. Please signup first.");
		}

		/*
		 * Redirect to profile class or Registrar class on the basis of input
		 */

		// redirect code to profile class by passing 1 parameter {collegeid}.
		Profile pf = new Profile();
		pf.dashboard(cred);
	}
}