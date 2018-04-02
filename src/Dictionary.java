/*
 Jackson Frank
 File: Dictionary.java
 Purpose: To find how many words in the dictionary fall between two given words
 Pseudocode: Gets the input for the two words, then uses the words.txt file which is read into
 			 a list to find the indexes of the 2 words using a binary search to find the distance 
 			 between the words
 */

import java.util.*;
import java.io.*;

public class Dictionary {

	public static void main(String[] args) throws FileNotFoundException {
		
		//gets input from user for the 2 words 
		Scanner console = new Scanner(System.in);
		System.out.print("Two words: ");
		String word1 = console.next().toLowerCase();
		String word2 = console.next().toLowerCase();
		
		//prints out how many words are between the 2 words
		findDistance(word1, word2);
		
		console.close();
	}
	
	public static void findDistance(String word1, String word2)  throws FileNotFoundException {
		
		//accesses the dictionary text file
		Scanner input = new Scanner(new File("src/words.txt"));
		
		//stores the words from the text file into a list
		List<String> words = new ArrayList<String>();
		//words.txt is already sorted
		while(input.hasNext()) {
			words.add(input.nextLine().toLowerCase());
		}
		
		/* uses a binary search method in order to find the indexes of both words within
		   	the list, and uses those indexes in order to find how many words are inbetween
		   	the 2 words given */ 
		if(word1.equals(word2) == false) {
			int index1 = binSearch(word1, words, 0, words.size());
			int index2 = binSearch(word2, words, 0, words.size());
			int distance = Math.abs(index1 - index2) - 1;
			System.out.print("Words inbetween: " + distance);
		}
		//prints if the 2 words the user gave are the same word
		else {
			System.out.print("Those are the same word");
		}
	}
	
	public static int binSearch(String word, List<String> words, int start, int end) {
		//finds the index that exists halfway in the list
		int index = (((end - 1) - start) / 2) + start;
		
		//gets the element halfway in the list
		String compare = words.get(index);
		
		/* if the word is alphabetically before the halfway point, keep searching
		   	using the subList of the first half of the current list, and if the 
		   	word is alphabetically after the halfway point, then continue searching
		   	using the subList of the second half of the current list*/ 
		if(word.compareTo(compare) < 0) {
			return binSearch(word, words, start, index);
		}
		else if(word.compareTo(compare) > 0) {
			return binSearch(word, words, index + 1, end);
		}
		//base case is if the halfway element and the search word are the same
		else {
			return index;
		}
	}

}
