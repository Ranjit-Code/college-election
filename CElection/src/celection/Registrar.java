package celection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Registrar {
	private static final String USERNAME = "simrandeep";
	private static final String PASSWORD = "simran@123";
	private static boolean isPublished = false;
	private static ArrayList<Candidate> cand = new ArrayList<Candidate>();

	public void dashboard() throws Exception {
		@SuppressWarnings("resource")
		Scanner cred = new Scanner(System.in);
		System.out.println("Enter your username");
		String inUsername = cred.nextLine();
		
		 String inPassword = PasswordField.readPassword("Enter password:");

		if (inUsername.equalsIgnoreCase(USERNAME) && inPassword.equals(PASSWORD)) {
			System.out.println("Welcome " + USERNAME + " to the college election portal");
			/*
			 * Database call to retrieve all the candidate with respect to their count. Need
			 * to show publish option if not published yet.
			 */

			// get the database connection
			Statement stmt = DatabaseConnection.getConnection().createStatement();

			ResultSet rs = stmt.executeQuery("select * from Candidate");

			System.out.println("Candidates list with vote count");
			System.out.println("================================");
			System.out.println("Name	||    Votes");
			System.out.println("--------------------");
			while (rs.next()) {
				System.out.println(rs.getString(2) + " 	|| 	" + rs.getInt(3));
			}

			System.out.println();

			if (isPublished) {
				System.out.println("You have already published the result.");
				System.out.println("---------------------------------------");
				printResult();
			} else {
				System.out.println("Press P to publish the results Or Press R to return.");
				@SuppressWarnings("resource")
				Scanner in = new Scanner(System.in);
				String ch = in.nextLine();
				if (ch.equalsIgnoreCase("p")) {
					//To update the publish flag
					String queryToUpdatepublish = "Update publish set isPublished = 1 where id = 1";
					//System.out.println(queryToUpdatepublish);
					PreparedStatement preparedToUpdatepublish = DatabaseConnection.getConnection().prepareStatement(queryToUpdatepublish);
					int res = preparedToUpdatepublish.executeUpdate();
					if(res > 0){
						//System.out.println("success");
					}
					setIsPublish(true);
					printResult();
				} else if(ch.equalsIgnoreCase("r")){
					Election.navigation();
				}else {
					System.out.println("Wrong input");
				}
			}

		} else {
			throw new Exception("Credentials mismatch. please try again!");
		}
	}

	public static boolean getIsPublish() {
		return isPublished;
	}

	public static void setIsPublish(boolean flag) {
		isPublished = flag;
	}

	public static ArrayList<Candidate> getResult() {
		return cand;
	}

	public static void printResult() throws Exception {
		// get the database connection
		Statement stmt = DatabaseConnection.getConnection().createStatement();
		ResultSet getMaxVoterDetails = stmt.executeQuery("select * from Candidate where count=(select max(count) from Candidate)");
		while (getMaxVoterDetails.next()) {
			cand.add(new Candidate(getMaxVoterDetails.getInt(1), getMaxVoterDetails.getString(2),
					getMaxVoterDetails.getInt(3)));
		}
		
		if(cand.size() == 1) {
			Iterator<Candidate> itr = cand.iterator();
			Candidate mapElement = itr.next();
			System.out.println("Congratulations!!! " + mapElement.getName()  + " has won the election .");
			System.out.println("Thanks for voting..");
		}else if(cand.size() == 2) {
			System.out.println("Congratulations!!! There is tie up b/w 2 candidates.");
			Iterator<Candidate> itr = cand.iterator();
			while (itr.hasNext()) {
				Candidate mapElement = itr.next();
				System.out.println(mapElement.getName());
			}
			System.out.println("Lets flip the coin.");
			System.out.println("Thanks for voting.");
		} else {
			System.out.println("Congratulations!!! Following are the candidates who have won the election.");
			Iterator<Candidate> itr = cand.iterator();
			while (itr.hasNext()) {
				Candidate mapElement = itr.next();
				System.out.println(mapElement.getName());
			}
			System.out.println("Thanks for voting.");
		}
	}
}