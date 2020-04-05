/**
* @author ${Ranjit Singh}
*/

package celection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Election {

	public static void main(String[] args) {
		initialisePublish();
		navigation();
	}
	
	public static void initialisePublish() {
		Statement stmt;
		try {
			stmt = DatabaseConnection.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select * from publish");
			if (rs.next()) {
				Registrar.setIsPublish(rs.getBoolean(1));
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	public static void navigation() {
		System.out.println("Welcome to the college-election portal");
		System.out.println("**************************************");
		System.out.println("Are you a registrar Y or N?");
		@SuppressWarnings("resource")
		Scanner regOrN = new Scanner(System.in);
		String reg = regOrN.nextLine();

		try {
			if (reg.equalsIgnoreCase("Y")) {
				Registrar rg = new Registrar();
				rg.dashboard();
			} else if (reg.equalsIgnoreCase("N")) {
				DecideForSignupOrSignIn decide = new DecideForSignupOrSignIn();
				decide.decide();
			} else {
				throw new Exception("Wrong input. Please choose the valid option");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
