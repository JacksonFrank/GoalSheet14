/*
 Jackson Frank
 File: Ch11Proj1
 Purpose: To find the edit distance (Levenshtein distance) between 2 words
 */

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
public class Ch11Proj1{
	
	Map<String, Set<String>> map = new HashMap<String, Set<String>>();
	int threads, threadsCompleted = 0;
	int initPrintInc;
	//Set<String> fileSet;

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		Set<String> fileSet = new HashSet<String>();
		Map<String, Set<String>> distanceMap = new HashMap<String, Set<String>>();
		
		String file = "dictionary.txt";
		Scanner dictionary = new Scanner(new File("src/" + file));
		
		Scanner console = new Scanner(System.in);
		System.out.println("Words must be same length");
		System.out.println("First word: ");
		System.out.print(">");
		String word1 = console.next();
		System.out.println("Second word: ");
		System.out.print(">");
		String word2 = console.next();
		console.close();
		
		if(word1.length() == word2.length()) {
			System.out.printf("\nLoading...\n");
			
			while(dictionary.hasNext()) {
				String str = dictionary.next();
				if(str.length() == word1.length()) {
					fileSet.add(str);
				}
			}
			dictionary.close();
			
			distanceMap = getMap(fileSet);
			System.out.println("Map loaded...");
			System.out.println("Edit distance: " + findEditDistance(word1, word2, distanceMap));
		}
		else {
			System.out.println("Error: Words aren't same length.");
		}
		
		//Ch11Proj1 Ch1 = new Ch11Proj1();
		//Ch1.Start();
	}
	
	
	
	public static Map<String, Set<String>> getMap(Set<String> set){
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		
		for(String str : set) {
			Set<String> editSet = new HashSet<String>();
			for(int i = 0; i < str.length(); i++) {
				for(char c = 'a'; c <= 'z'; c++) {
					String s = "";
					for(int g = 0; g < str.length(); g++) {
						if(g != i) {
							s += str.charAt(g);
						}
						else {
							s += c;
						}
					}
					if(set.contains(s)) {
						editSet.add(s);
					}
				}
			}
			map.put(str, editSet);
		}
		
		return map;
	}
	
	public static int findEditDistance(String word, String target, Map<String, Set<String>> map) {
		LinkedList<String> thisList = new LinkedList<String>();
		thisList.add(word);
		for(int i = 0; i <= 100; i++) {
			for(String s : thisList) {
				if(s.equals(target)) {
					return i;
				}
			}
			LinkedList<String> newList = new LinkedList<String>();
			for(String s : thisList) {
				newList.addAll(map.get(s));
			}
			thisList.clear();
			thisList.addAll(newList);
			
		}
		return -1;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public void Start() throws FileNotFoundException, InterruptedException {
		String file = "dictionary.txt";
		fileSet = new HashSet<String>();
		
		String s1, s2;
		Scanner console = new Scanner(System.in);
		System.out.print("First word: ");
		s1 = console.nextLine();
		System.out.print("Second word: ");
		s2 = console.nextLine();
		System.out.println();
		console.close();
		
		
		long startTime = System.currentTimeMillis();
		
		findEditDistance(file);
		
		
		
		
		
		long totalTime = (System.currentTimeMillis() - startTime) / 1000;
		
		//input.close();
		
		System.out.println("Done loading");
		System.out.print("Time to load: " + totalTime + " seconds");
		
	}
	
	public void findEditDistance(String file) throws FileNotFoundException, InterruptedException {
		Scanner input = new Scanner(new File("src/" + file));
		//Set<String> set = new HashSet<String>();
		while(input.hasNextLine()) {
			fileSet.add(input.nextLine());
		}
		threads = fileSet.size();
		initPrintInc = threads / 1000;
		
	
		for(String s : fileSet) {
			Calculate calc = new Calculate(s, set, this);
			calc.start();
			
		}
		
		
	}

	@Override
	public void getSets(Set<String> stringSet, String target) throws FileNotFoundException {
		map.put(target, stringSet);
		threadsCompleted ++;
		if(threadsCompleted % initPrintInc == 0) {
			System.out.printf("Percent Loaded: %.1f\n", ((double)threadsCompleted / (double)threads) * 100f);
			System.out.println("Threads: " + threadsCompleted + " out of " + threads);
			System.out.println();
		}
		if(threadsCompleted >= threads) {
			printToFile();
		}
	}
	
	public void printToFile() throws FileNotFoundException {
		PrintStream p = new PrintStream(new File("src/MapFile"));
		for(String s : fileSet) {
			p.print(s + " ");
			for(String set : map.get(s)) {
				p.print(set + " ");
			}
			p.println();
		}
	}
	*/
	
	
	
	
	
}

