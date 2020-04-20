/**
* @author ${Ranjit Singh}
*/

package celection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

public class Profile {
	public void dashboard(Credentials cred) throws Exception {
		System.out.println("Welcome " + cred.getName() + " to the college election portal");

		/*
		 * first we check whether this user did vote or not. for this we have to create
		 * a column in the user table to check whether he has voted or not. It may be a
		 * column where we put the assigned candidate and check if that column is empty
		 * then that user is first time otherwise we have to show the assigned
		 * candidate.
		 */

		// get the database connection
		Connection conn = DatabaseConnection.getConnection();
		Statement stmt = conn.createStatement();
		Boolean hasVoted = cred.isHasVoted();

		if (hasVoted) {

			/*
			 * Retrieving a candidate on the basis of assigned candidate.
			 */

			/* retrieving the record on the basis of college id */
			ResultSet rs = stmt.executeQuery("select * from Candidate where id = " + cred.getAssignedCandidate());
			if (rs.next()) {
				System.out.println("You have chosen " + rs.getString(2)
						+ " as your candidate. You cannot make the second selection.");
			} else {
				throw new Exception("Something went wrong. Please try again");
			}
			
			if(Registrar.getIsPublish()) {
				System.out.println("Results has been published. Please find below.");
				System.out.println("----------------------------------------------");
				Registrar.printResult();
			}else {
				System.out.println("Result will be published here. Good Luck.");
			}

		} else {
			System.out.println("Please select the candidate from the list");

			ResultSet rs = stmt.executeQuery("select * from Candidate");

			HashMap<Integer, String> candidates = new HashMap<Integer, String>();
			while (rs.next()) {
				candidates.put(rs.getInt(1), rs.getString(2));
			}

			Iterator<Entry<Integer, String>> itr = candidates.entrySet().iterator();
			while (itr.hasNext()) {
				Entry<Integer, String> mapElement = itr.next();
				System.out.println(mapElement.getKey() + "    " + mapElement.getValue());
			}

			@SuppressWarnings("resource")
			Scanner select = new Scanner(System.in);
			int selectedCand = select.nextInt();

			/*
			 * in case of check the candidate here we need to do the db call and display the
			 * candidate selection
			 */

			if (candidates.containsKey(selectedCand)) {
				/*
				 * database call to save the selected candidate corresponding to the user
				 */
				String queryToSelectFromCandidate = "select * from Candidate where id = " + selectedCand;
				PreparedStatement preparedStmtFromCandidate = conn.prepareStatement(queryToSelectFromCandidate);
				ResultSet rsSelectFromCandidate = preparedStmtFromCandidate.executeQuery();
				int voteCount=0;
				if(rsSelectFromCandidate.next()) {
					voteCount = rsSelectFromCandidate.getInt(3);
				}
				
				int totalCount = voteCount + 1;
				String queryToUpdateCandidate = "Update Candidate set count = " + totalCount + " where id = " + selectedCand;
				PreparedStatement preparedStmtUpdateCandidate = conn.prepareStatement(queryToUpdateCandidate);
				preparedStmtUpdateCandidate.executeUpdate();
				
				String query = "Update User set assigned_candidate = " + selectedCand + ", hasVoted = " + 1
						+ " where collegeId = '" + cred.getId() + "';";
				PreparedStatement preparedStmt = conn.prepareStatement(query);

				//System.out.println(query);

				// execute the java prepared statement
				int count = preparedStmt.executeUpdate();

				if (count > 0) {
					System.out.println("Thank you for voting. You have selected " + candidates.get(selectedCand) + " as your candidate.");
				} else {
					throw new Exception("Something went wrong. Please try again");
				}
			} else {
				throw new Exception("Candidate does not exists. Please choose again.");
			}
		}
	}
}
