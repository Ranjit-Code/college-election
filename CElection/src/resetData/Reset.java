package resetData;


import java.sql.Statement;

import celection.DatabaseConnection;

public class Reset {

	public static void main(String[] args) throws Exception {
		// get the database connection
		Statement stmt = DatabaseConnection.getConnection().createStatement();

		/* reset all the tables */
		stmt.executeUpdate("Delete from User;");
		stmt.executeUpdate("update publish set isPublished = 0;");
		stmt.executeUpdate("update Candidate set count = 0 where id in (1,2,3,4,5);");
		
		System.out.println("Reset successfully");
	}

}
