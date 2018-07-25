import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

/*
    Development Notes
    
    Distinct set of Strings (no duplicates supported).
    Output is ordered based on the occurrence.
    all lower case letters
    
    know that if num > 26 then there is no possilbe answer since no english 
    lower case substring due to exhaustion of the alphabet.
    
    dont know what to do for num == 0, could do each character, but that would 
    be the same as 1.
    
    will go ahead and if num = 0 or num > inputString.length() will just return
    zero then.
    
    - iterate through the window of num.
    - while iterating populate a map of character counts. 
    - record the occurrence of when a count is greater than one.
    - if the count for greater than one is zero then the word *is a candidate
      for being added*
    - added candidates will be a HashSet of the substring.  if the string does 
      not already exist then add it to the hashset and then add to the result.
    - will cost O(N) where N is the lenght of the string.
    - will cost O(26 + M) for memory footprint. M = inputString - num + 1
*/

public class Challenge7
{
    public static List<String> solution(String inputString, int num)
	{
	    // Oredered
	    ArrayList<String> result = new ArrayList<String>();
	    // Non-ordered
	    HashSet<String> acceptedResults = new HashSet<String>();
	    int [] charCounts = new int [26];
	    
        if (inputString == null || inputString.isEmpty() || num <= 0 || num > inputString.length()) {
            return result;
        }
	    
	    int moreThanOneCount = 0;
	    // Populate the initial window.
	    for (int index = 0; index < inputString.length() && index < num; index++) {
	        char character = inputString.charAt(index);
	        int count = charCounts[character - 'a'];
	            
	        if (count == 1) {
	            // only want to increment moreThanOneCount on the transistion 
	            // from one to two only.
	            moreThanOneCount++;
	        }
	        
	        // put back into the counts.
	        charCounts[character - 'a'] = count + 1;
	    }
        
        /*
        int temp = 0;
        for (int val : charCounts) {
            System.out.println(((char)(temp + 'a')) + " " + val);
            temp++;
        }
        */

	    if (moreThanOneCount == 0) {
	        addToResult(result, acceptedResults, inputString.substring(0, num));
	    }
	    
	    // Initial window is now setup.
	    for (int index = 0; (index + num) < inputString.length(); index++) {
	        char leftCharacter = inputString.charAt(index);
	        char rightCharacter = inputString.charAt(num + index);
	        int count = 0;
            
            // System.out.println(leftCharacter + " " + rightCharacter);

	        // remove the left most in the window.
	        count = charCounts[leftCharacter - 'a'];
	        
	        if (count == 2) {
	            // only want to decrement moreThanOneCount on the transition 
	            // from two to one only
	            moreThanOneCount--;
	        }
	        
	        charCounts[leftCharacter - 'a'] = count - 1;
	        
	        // add the right most in the window.
	        count = charCounts[rightCharacter - 'a'];
	        
	        if (count == 1) {
	            // only want to increment moreThanOneCount on the transistion 
	            // from one to two only.
	            moreThanOneCount++;
	        }
	        
	        charCounts[rightCharacter - 'a'] = count + 1;
	        
	        // Now if the substring is a candidate perform the addToResult.
	        if (moreThanOneCount == 0) {
	           addToResult(result, acceptedResults, inputString.substring(index + 1, num + index + 1)); 
	        }
	    }
	    
	    return result;
	}
	
	/**
	 * logic to determine if the substring should be added to the list.
	 */ 
	private static void addToResult(ArrayList<String> result, 
	    HashSet<String> acceptedResults, 
	    String substring) 
	{
	    // 1. see if the substring already exists within the hash set or not.
	    if (!acceptedResults.contains(substring)) {
	        acceptedResults.add(substring);
	        result.add(substring);
	    }
	    
	    return;
	}

    public static void main(String [] args) {
        String string = "acrazystringthisisgoingtobe";
        for (String word : solution(string, 4)) {
            System.out.print(word + " ");
        }
        System.out.println("");
    }
}