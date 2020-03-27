package celection;

import java.util.Scanner;

public class Registrar {
	private static final String USERNAME = "Simrandeep Kaur";
	private static final String PASSWORD = "kaur@1234";
	
	public void dashboard() throws Exception{
		@SuppressWarnings("resource")
		Scanner cred = new Scanner(System.in);
		System.out.println("Enter your username");
		String inUsername = cred.nextLine();
		System.out.println("Enter your passowrd");
		String inPassword = cred.nextLine();
		
		if(inUsername.equalsIgnoreCase(USERNAME) && inPassword.equals(PASSWORD)){
			System.out.println("Welcome " + USERNAME + " to the college election portal");
			/*
			 * Database call to retrieve all the candidate with respect to their count.
			 * Need to show publish option if not published yet.
			 * */
		}else{
			throw new Exception("Credentials mismatch. please try again!");
		}
	}
}
