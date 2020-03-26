/**
* @author ${Ranjit Singh}
*/

package celection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

public class Profile {
	public void dashboard(String collegeId){
		/* Here a database call to retrieve the profile on the basis of college id
		 * and store it in the username */
		String username = "test";
		System.out.println("Welcome " + collegeId  +  " to the college election portal");
		System.out.println("Please select the candidate from the list");
		
		/* first we check whether this user did vote or not. for this we have to create a column 
		 * in the user table to check whether he has voted or not. It may be a column where we
		 * put the assigned candidate and check if that column is empty then that user is first 
		 * time otherwise we have to show the assigned candidate. 
		 * */
		
		/* below lines are only if login candidate is not chosen their candidate
		 * from line 28 to 43 is skipped if the candidate already chosen. We just show the last
		 * line.*/
		HashMap<Integer,String> candidates = new HashMap<Integer, String>();
		candidates.put(1, "Robin");
		candidates.put(2, "Berney");
		candidates.put(3, "Marshal");
		candidates.put(4, "Ted");
		candidates.put(5, "Tony");
		
		Iterator<Entry<Integer, String>> itr = candidates.entrySet().iterator();
		while(itr.hasNext()){
			Entry<Integer, String> mapElement = itr.next();
		    System.out.println(mapElement.getKey() + "    " + mapElement.getValue());
		}
		
		@SuppressWarnings("resource")
		Scanner select = new Scanner(System.in);
		System.out.println("Select your candidate");
		int selectedCand = select.nextInt();
		
		/*in case of check the candidate here we need to do the db call 
		 * and display the candidate selection*/
		System.out.println("Thank you for voting. You have selected " + candidates.get(selectedCand));
		
		/* database call to save the selected candidate corresponding to the user
		 * */
	}
}
